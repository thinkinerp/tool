package com.intfocus.hdk.util;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.intfocus.hdk.vo.SalesData;

 
/**
*万年历调用示例代码 － 聚合数据
*在线接口文档：http://www.juhe.cn/docs/177
**/
 
public class JuheDemo {
    public static  String DEF_CHATSET = "GBK";
    public static final int DEF_CONN_TIMEOUT = 30000;
    public static final int DEF_READ_TIMEOUT = 30000;
    public static String userAgent =  "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";
 
    //配置您申请的KEY
    public static final String APPKEY ="*************************";
 
    public static void setCharset(String charset){
    	DEF_CHATSET  = charset;
    }
    
    public static Map<String , String> check(String uuid  , String keyid){
    	String result =null;
    	String url =StaticVariableUtil.CHECK_STORE_URL;//请求接口地址
        Map<String , String> params = new HashMap<String , String>();
        params.put("uuid",uuid);
        params.put("keyid",keyid);
        Map<String , String> map = new HashMap<String,String>();
        try {
            result =net(url, params, "GET",null);
            JSONObject object = JSONObject.parseObject(result);
            
            if(null !=object.getString("message")){
            	map.put("message",object.getString("message"));
            }
            
            if(null != object.getString("result")){
            	map.put("result",object.getString("result"));
            }
            if(null != object.getString("shop")){
            	map.put("shop",object.getString("shop"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map ;
    }
   
    public static Map<String , String> uplaodSalesData( SalesData sd){
    	String result =null;
    	String url = StaticVariableUtil.CHECK_STORE_URL ;
        Map<String , String> params = new HashMap<String , String>();
        params.put("uuid", sd.getStoreUuid());
        params.put("dct", "0004");
        params.put("uptime", sd.getSSCreateAt());
        params.put("fildate", sd.getSalesDate());
        params.put("amt", sd.getSalesAmount().toString());
        Map<String , String> map = new HashMap<String,String>();
        try {
            result =net(url, params, "GET",null);
            JSONObject object = JSONObject.parseObject(result);
            
            if(null !=object.getString("result")){
            	map.put("result",object.getString("result"));
            }
            
            if(null != object.getString("message")){
            	map.put("message",object.getString("message"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map ;

    }
 
    /**
     *
     * @param strUrl 请求地址
     * @param params 请求参数
     * @param method 请求方法
     * @return  网络请求字符串
     * @throws Exception
     */
    public static String net(String strUrl, Map params,String method , String extra) throws Exception {
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        try {
            StringBuffer sb = new StringBuffer();
            if(method==null || method.equals("GET")){
                strUrl = strUrl+"?"+urlencode(params);
                if(null != extra && !"".equalsIgnoreCase(extra)){
                	strUrl = strUrl + extra;
                }
            }
            
            
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            if(method==null || method.equals("GET")){
                conn.setRequestMethod("GET");
            }else{
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
            }
            conn.setRequestProperty("User-agent", userAgent);
            conn.setUseCaches(false);
            conn.setConnectTimeout(DEF_CONN_TIMEOUT);
            conn.setReadTimeout(DEF_READ_TIMEOUT);
            conn.setInstanceFollowRedirects(false);
            conn.setRequestProperty("contentType", "UTF-8"); 
            conn.connect();
            if (params!= null && method.equals("POST")) {
                try {
                    DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                        out.writeBytes(urlencode(params));
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
            }
            rs = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rs;
    }
 
    //将map型转为请求参数型
    public static String urlencode(Map<String,Object>data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue()+"","UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}