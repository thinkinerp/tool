package com.intfocus.hdk.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.intfocus.hdk.dao.JsapiTicketMapper;
import com.intfocus.hdk.dao.JsapiTokenMapper;
import com.intfocus.hdk.vo.JsapiTicket;
import com.intfocus.hdk.vo.JsapiToken;

public class WeiXinUserInfoUtil {
	
    private final static Logger log =  Logger.getLogger(WeiXinUserInfoUtil.class);
    
	public static  JSONObject getUserInfo(String openid , JsapiTokenMapper jtm ){
        Map<String , String> param = new HashMap<String ,String>();
    	JSONObject object = getToken(jtm);
    	String result = "";
		if(null != object.getString("errcode")){
			log.info("通过微信接口获取 jsapi_ticket 的时候出现问题：" + object.getString("errmsg"));
			return object;
		}
        if(null !=object.getString("errcode")){
        	//返回错误的 JSON 格式为 {"errcode":40029,"errmsg":"invalid code"}
        	return object;
        }else{
        	
        	JuheDemo.setCharset("UTF-8");
        	// 获取是否关注微信公众号的接口 https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
            try {
            	param.clear();
            	param.put("access_token", object.getString("access_token"));
            	param.put("openid", openid);
            	param.put("lang", "zh_CN");
				result = JuheDemo.net("https://api.weixin.qq.com/cgi-bin/user/info", param, "GET",null);
			} catch (Exception e) {
				e.printStackTrace();
			}
            JSONObject object1 = JSONObject.parseObject(result);         
            log.info("cgi-bin userInfo:" + result);
			return  object1;
        }	
        
	}
	
	public static String getSign(JsapiTicketMapper jsap , JsapiTokenMapper jtm){
		
		Map<String,String> map = new HashMap<String,String>();
        Map<String , String> param = new HashMap<String ,String>();
        String result = "" ;
		
		List<JsapiTicket> tickets = jsap.selectByWhere(null);
		
		if( 0 == tickets.size() || expired(tickets.get(0))){
            try {
            	
            	JSONObject object = getToken(jtm);
				if(null != object.getString("errcode")){
					log.info("通过微信接口获取 jsapi_ticket 的时候出现问题：" + object.getString("errmsg"));
					return "error";
				}
            	param.clear();
            	param.put("access_token", object.getString("access_token"));
            	param.put("type","jsapi");
		        String requestUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";  
				result = JuheDemo.net(requestUrl, param, "GET",null);
				JSONObject object1 = JSONObject.parseObject(result);      
				
				 log.info("return ticket:"+object1.toJSONString());
				
				if("1".equalsIgnoreCase(object1.getString("errcode"))){
					log.info("通过微信接口获取 jsapi_ticket 的时候出现问题：" + object1.getString("errmsg"));
					return "error";
				}else{
					
					jsap.deleteAll();
					
					JsapiTicket t = new JsapiTicket();

					t.setExpiresIn(Integer.parseInt(object1.getString("expires_in")) - 200);
					t.setJsapiTicketContent(object1.getString("ticket"));
					
				    log.info("insert ticket:"+JSONObject.toJSONString(t));
					
					jsap.insertSelective(t);
					
					result = object1.getString("ticket");

				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else{
			result  = tickets.get(0).getJsapiTicketContent();
		}
		
		
		
		return result;
		
	}
	
	public static boolean expired(JsapiTicket ticket ){
		
		Date getTime = ticket.getGetTime();
		
	    long diff = (new Date()).getTime() - getTime.getTime();
	    long secords = diff / (1000);
		if( ticket.getExpiresIn() > secords ){
			return false;
		}
		return true ;
	}
	
	public static boolean expired(JsapiToken token ){
		
		Date getTime = token.getGetTime();
		
	    long diff = (new Date()).getTime() - getTime.getTime();
	    long secords = diff / (1000);
		if( token.getExpiresIn() > secords ){
			return false;
		}
		return true ;
	}
	
	public static Map<String, String> sign(String jsapi_ticket, String url) {
		
        Map<String, String> ret = new HashMap<String, String>();
        String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();
        String string1;
        String signature = "";

        //注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsapi_ticket +
                  "&noncestr=" + nonce_str +
                  "&timestamp=" + timestamp +
                  "&url=" + url;
        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        ret.put("url", url);
        ret.put("jsapi_ticket", jsapi_ticket);
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);

        return ret;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }

    private static JSONObject getToken(JsapiTokenMapper jtm){
    	Map<String ,String> param = new HashMap<String,String>();
    	List<JsapiToken> tokens= jtm.selectByWhere(null);
    	String result = "";
    	JSONObject object = null ;
    	if( 0 == tokens.size() || expired(tokens.get(0))){
            	param.clear();
            	param.put("appid", StaticVariableUtil.APPID);
            	param.put("secret", StaticVariableUtil.SECRET);
            	param.put("grant_type","client_credential");
            	try {
					result = JuheDemo.net("https://api.weixin.qq.com/cgi-bin/token", param, "GET",null);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	object = JSONObject.parseObject(result);  
				if(null != object.getString("errcode")){
					log.info("通过微信接口获取 token 的时候出现问题：" + object.getString("errmsg"));
					return object;
				}else{
					
					jtm.deleteAll();
					
					JsapiToken record= new JsapiToken();
					record.setExpiresIn(object.getInteger("expires_in") - 200);
					record.setJsapiTokenContent(object.getString("access_token"));
					jtm.insertSelective(record);
					return object;
				}
    	}
    	
    	object = new JSONObject();
    	object.put("access_token", tokens.get(0).getJsapiTokenContent());
    	return  object;
    }
	
}
