package com.intfocus.hdk.controller;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.intfocus.hdk.dao.JsapiTicketMapper;
import com.intfocus.hdk.dao.JsapiTokenMapper;
import com.intfocus.hdk.dao.SalesDataMapper;
import com.intfocus.hdk.dao.UsersMapper;
import com.intfocus.hdk.util.JuheDemo;
import com.intfocus.hdk.util.StaticVariableUtil;
import com.intfocus.hdk.util.WeiXinUserInfoUtil;
import com.intfocus.hdk.vo.SalesData;
import com.intfocus.hdk.vo.Users;

@Controller
@RequestMapping("/data")
public class dataCommunicationController implements ApplicationContextAware {
    private final static Logger log =  Logger.getLogger(dataCommunicationController.class);

    	@Resource 
    	private SalesDataMapper sdm ;
    	@Resource 
    	private         UsersMapper um ;
    	@Resource 
    	private         JsapiTicketMapper jtm   ;

    	@Resource 
    	private          JsapiTokenMapper jtp   ;
    private static ApplicationContext applicationContext;
    @RequestMapping(value = "submit" , method=RequestMethod.POST)
    @ResponseBody
    public Integer submit(HttpServletResponse res , HttpServletRequest req ,HttpSession session
    		              , SalesData sd ){
    	
    		log.info(JSONObject.toJSONString(sd));
    		String uuid = (String)session.getAttribute("uuid");
    		String userId = (String)session.getAttribute("userId");
    		sd.setCreatedAt(new Date());
    		sd.setStoreUuid(uuid);
			sd.setUserId(userId);
			JuheDemo.setCharset("GBK");
    		Map<String , String> mp = JuheDemo.uplaodSalesData(sd);
    		if("成功".equalsIgnoreCase(mp.get("message"))){
    			sd.setState((byte) 1);
    		}else{
    			sd.setState((byte) 0);
    			sd.setRemark(mp.get("message"));
    		}
    		sd.setCreatedAt(null);
    		return sdm.insertSelective(sd) + sd.getState() ;

    }
    
    @RequestMapping(value = "getSalesDate" , method=RequestMethod.POST)
    @ResponseBody
    public void getSalesDate(HttpServletResponse res , HttpServletRequest req ,HttpSession session
            , String startDate , String endDate , Integer page , Integer pageSize){

    	// 获得登陆用户的 ID
    	String uuid = (String) session.getAttribute("uuid");
    	Map<String , Object> where = new HashMap<String , Object>();
    	where.put("startDate", startDate);
    	where.put("endDate", endDate);
    	where.put("uuid", uuid);
    	where.put("page", page);
    	where.put("pageSize", pageSize);
    	List<SalesData> sds  = sdm.selectByWhere(where);
    	
		String json = JSONObject.toJSONString(sds);
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		Writer w;
		try {
			w = res.getWriter();
			w.write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }
    
    @RequestMapping(value = "scan" , method=RequestMethod.GET)
    public String scan(HttpServletResponse res , HttpServletRequest req,String uuid, String keyid ){   	
    	
    	String uri = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE#wechat_redirect";

    	uri = uri.replace("APPID", StaticVariableUtil.APPID);
    	uri = uri.replace("SCOPE", "snsapi_userinfo");
    	String url = StaticVariableUtil.BASE_URL + "/data/checkOnUser?uuid=";
//    	try {
//			uri = uri.replace("REDIRECT_URI", java.net.URLDecoder.decode("http://www.chuanzhen.mobi/hdk/data/checkOnUser?uuid="+uuid+"&keyid="+keyid,   "utf-8"));
    	  
    	   if(null != uuid){
    		   
    		   url = url+uuid ;
    		   
    		   if(null != keyid){
    			   url =url +"&keyid="+keyid;
    		   }
    		   
    		   uri = uri.replace("REDIRECT_URI", url);
    		   
    		   
    		   
    	   }else{
    		   return "redirect:/error.jsp";
    	   }
    		
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
    	uri = uri.replace("SCOPE", "code");
    	log.info("redirectURI"+uri);
    	return "redirect:"+uri;
    }
    
    @RequestMapping(value = "checkOnregisteredUser" , method=RequestMethod.GET)
    public String checkOnregisteredUser(HttpServletResponse res , HttpServletRequest req , HttpSession session, String code){
    	
    	//getSingature(res , req , session);	
    	JuheDemo.setCharset("GBK");
        Map<String , String> param = new HashMap<String ,String>();
        param.put("appid", StaticVariableUtil.APPID);
        param.put("secret", StaticVariableUtil.SECRET);
        param.put("code",code);
        param.put("grant_type", "authorization_code");
        String  result = "";
        
			try {
				result = JuheDemo.net("https://api.weixin.qq.com/sns/oauth2/access_token", param, "GET",null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JSONObject object = JSONObject.parseObject(result);
			
            if(null !=object.getString("errcode")){
            	log.info("userInfo request fail:openid:" + object.getString("openid")+ " error msg:" +object.getString("errmsg"));
            	return "redirect:/error.jsp";
            }else{
                Map<String , String> param2 = new HashMap<String ,String>();
                //?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
                param2.put("appid", StaticVariableUtil.APPID);
                param2.put("secret", StaticVariableUtil.SECRET);
                param2.put("code",code);
                param2.put("grant_type", "authorization_code");
                JuheDemo.setCharset("UTF-8");
                try {
					result = JuheDemo.net("https://api.weixin.qq.com/sns/userinfo?access_token="+object.getString("access_token")+"&openid="+object.getString("openid")+"&lang=zh_CN", param, "GET",null);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                JSONObject object1 = JSONObject.parseObject(result);
                if(null !=object1.getString("errcode")){
                	log.info("userInfo request fail:openid:" + object.getString("openid")+ " error msg:" +object1.getString("errmsg"));
                	return "redirect:/error.jsp";
                }else{
                	
                	Map<String , String> where = new HashMap<String,String>();
                    where.put("weixinid", object.getString("openid"));
                    List<Users> users = um.selectByWhere(where);
                    
                    if( 0 < users.size()){
                    	
                    	session.setAttribute("uuid",users.get(0).getStoreUuid());
                    	session.setAttribute("userId", users.get(0).getWeixinId());
                    	session.setAttribute("storeName", users.get(0).getStoreName());
                    	return "redirect:/index.jsp";
                    }else{
                    	
                    	session.setAttribute("errorMsg", "您的微信账号没有和门店关联，请联系管理员，发送给你门店二维码进行绑定");
                    	
                    	return "redirect:/error.jsp";
                    }
                    
                }
            }
    }
    
    @RequestMapping(value = "gotoSubmit" , method=RequestMethod.GET)
    public String gotoSubmit(HttpServletResponse res , HttpServletRequest req){

       	String uri = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE#wechat_redirect";

    	uri = uri.replace("APPID", StaticVariableUtil.APPID);
    	uri = uri.replace("SCOPE", "snsapi_userinfo");
    	uri = uri.replace("REDIRECT_URI", StaticVariableUtil.BASE_URL + "/data/checkOnregisteredUser");
    	
    	return "redirect:"+uri;
    }
    
    @RequestMapping(value = "gotoBindingStore" , method=RequestMethod.GET)
    public String gotoBindingStore(HttpServletResponse res , HttpServletRequest req , HttpSession session , String openid , String storeName , String uuid ,String keyid){
    	
    	Map<String, String> where = new HashMap<String , String >();
    	where.put("weixinid", openid);
		List<Users> records = um.selectByWhere(where);
		
		if(0 < records.size()){
			Users record = records.get(0);
			record.setStoreName(storeName);
			record.setStoreUuid(uuid);
			um.updateByPrimaryKey(record);
			session.setAttribute("uuid", uuid);
			session.setAttribute("userId", openid);
			session.setAttribute("storeName", storeName);
			return "redirect:/index.jsp";
		}else{
			session.setAttribute("errorMsg", "无此账号");
			return "redirect:/error.jsp";
			
		}
    }
    
    
    @RequestMapping(value = "checkOnUser" , method=RequestMethod.GET)
    public String checkOnUser(HttpServletResponse res , HttpServletRequest req,HttpSession session,String uuid, String keyid , String code){
    	
    	log.info("can:" +uuid +" " + keyid +" code : " + code);
    	
    	Map<String , String> where = new HashMap<String,String>();
    	
    	JuheDemo.setCharset("GBK");
        Map<String , String> rs = JuheDemo.check(uuid , keyid);
    	//先判断 uuid（门店id）是否有效，如果无效，则发送给用户提示信息
    	if("1".equalsIgnoreCase(rs.get("result"))){
        	log.info("code request fail:" + rs.get("message"));
        	session.setAttribute("errorMsg", "二维码无效，请联系管理员");
        	return "redirect:/error.jsp";
    	}
    	
        Map<String , String> param = new HashMap<String ,String>();
        param.put("appid", StaticVariableUtil.APPID);
        param.put("secret", StaticVariableUtil.SECRET);
        param.put("code",code);
        param.put("grant_type", "authorization_code");
        String  result = "";
        
			try {
				result = JuheDemo.net("https://api.weixin.qq.com/sns/oauth2/access_token", param, "GET",null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
            JSONObject object = JSONObject.parseObject(result);
            
            if(null !=object.getString("errcode")){
            	log.info("code request fail:" + object.getString("errmsg"));
            	session.setAttribute("errorMsg", "微信服务器出现错误，请重试");
            	return "redirect:/error.jsp";
            }else{
            	
            	log.info("判断是否已经关注公众号");
            	// 在这里判断是否关注微信公众好。
            	JSONObject  jb = WeiXinUserInfoUtil.getUserInfo(object.getString("openid"),jtp);
            	
                if(null !=jb.getString("errcode")){
                	log.info("userInfo request fail: error msg:" +jb.getString("errmsg"));
                	session.setAttribute("errorMsg", "微信服务器出现错误，请重试");
                	return "redirect:/error.jsp";
                }else{
                	
                	if(null != jb.getString("subscribe") && "0".equalsIgnoreCase(jb.getString("subscribe"))){
                		String ticket = WeiXinUserInfoUtil.getSign(jtm, jtp);
                		log.info("用户没有关注公众号");
                		if("error".equalsIgnoreCase(ticket)){
                        	log.info("code request fail:获取 ticket 失败" );
                        	session.setAttribute("errorMsg", "微信验证无效，请重试");
                        	return "redirect:/error.jsp";
                		}
                		
                		Map<String,String> sign = WeiXinUserInfoUtil.sign(ticket, "http://www.chuanzhen.mobi/hdk/followeWeiXinPublic.jsp");
                		
                		session.setAttribute("appId", StaticVariableUtil.APPID);
                		session.setAttribute("timestamp", sign.get("timestamp"));
                		session.setAttribute("nonceStr", sign.get("nonceStr"));
                		session.setAttribute("signature", sign.get("signature"));
                		log.info("获取的 signature：" + sign.get("signature"));
                		return "redirect:http://www.chuanzhen.mobi/hdk/followeWeiXinPublic.jsp";
                	}
                	
                }

            	
            	JuheDemo.setCharset("UTF-8");
                try {
					result = JuheDemo.net("https://api.weixin.qq.com/sns/userinfo?access_token="+object.getString("access_token")+"&openid="+object.getString("openid")+"&lang=zh_CN", param, "GET",null);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                JSONObject object1 = JSONObject.parseObject(result);         
                if(null !=object1.getString("errcode")){
                	log.info("userInfo request fail:openid:" + object.getString("openid")+ " error msg:" +object1.getString("errmsg"));
                	return "redirect:/error.jsp";
                }
                
                
                where.put("uuid", uuid);
                where.put("keyid", keyid);
                where.put("weixinid", object.getString("openid"));
                List<Users> users = um.selectByWhere(where);
                // 0 < users.size() 说明此微信用户已经绑定过门店，另外一种情况是重新绑定，即已经绑定过了，例如 A 绑定了门店1
                // 然后，本次扫的是门店2，此时需要另外判断。这种情况的条件为 0 = users.size()
                
                if(0 < users.size()){
                	
                	log.info("微信id 与门店绑定");
                	
                	session.setAttribute("uuid", uuid);
                	session.setAttribute("storeName", users.get(0).getStoreName());
                	session.setAttribute("userId", users.get(0).getWeixinId());
//                	String ticket1 = WeiXinUserInfoUtil.getSign(null, jtm);
//            		Map<String,String> sign1 = WeiXinUserInfoUtil.sign(ticket1, "http://www.chuanzhen.mobi/hdk/index.jsp");
//            		session.setAttribute("appId", "wx1b5cef3e2e36fa21");
//            		session.setAttribute("timestamp", sign1.get("timestamp"));
//            		session.setAttribute("nonceStr", sign1.get("nonceStr"));
//            		session.setAttribute("signature", sign1.get("signature"));
                	return "redirect:/index.jsp";
                }else{	
                	log.info("微信id 与门店未绑定");
                	// 这里的情况分两种
                	//一种是重绑定
                	// 一种是第一次绑定
                	

                	//开始判断微信账号是否已经绑定了其他的门店
                	where.clear();
                	where.put("weixinid", object.getString("openid"));
                	List<Users> regeUsers = um.selectByWhere(where);
                	if( 0 < regeUsers.size()){
                		//此微信账号已经绑定过，但是本次扫码对应的门店还未绑定
                		session.setAttribute("redirectUrl", "/data/gotoBindingStore?openid="+object.getString("openid")
                																															+"&uuid="+uuid
                																															+"&keyid="+keyid
                																															+"&storeName="+rs.get("shop"));
                		session.setAttribute("oldStoreName", regeUsers.get(0).getStoreName());
                		session.setAttribute("newStoreName",rs.get("shop"));
                		session.setAttribute("openid", object1.getString("nickname"));
                		return "redirect:/redirect.jsp";
                				
                	}
                	
	                Map<String , String> param2 = new HashMap<String ,String>();
	                //?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
	                param2.put("appid", StaticVariableUtil.APPID);
	                param2.put("secret", StaticVariableUtil.SECRET);
	                param2.put("code",code);
	                param2.put("grant_type", "authorization_code");
	                JuheDemo.setCharset("UTF-8");

	                	log.info("userInfo request success: nickname"  + object1.getString("nickname") + "    openid:" + object.getString("openid"));
	                    if("成功".equalsIgnoreCase(rs.get("message"))){
	                    	Users record = new Users();
	            			//关联操作
	                    	record.setStoreUuid(uuid);
	                    	record.setStoreKeyId(keyid);
	                    	record.setStoreName(rs.get("shop"));
	                    	record.setWeixinName(object1.getString("nickname"));
	                    	record.setWeixinId(object1.getString("openid"));
	                    	um.insertSelective(record );
	                    	session.setAttribute("uuid", uuid);
	                    	session.setAttribute("userId", object.getString("openid"));
	                    	session.setAttribute("storeName", rs.get("shop"));
	                    	
	                    	return "redirect:/index.jsp";
                }
            }
            }
    
    return "";
}
    
    @RequestMapping(value = "getSingature" , method=RequestMethod.GET)
    @ResponseBody
    public String getSingature(HttpServletResponse res , HttpServletRequest req,HttpSession session){
    	String ticket = WeiXinUserInfoUtil.getSign(jtm,jtp);
		Map<String,String> sign = WeiXinUserInfoUtil.sign(ticket, StaticVariableUtil.BASE_URL +"/index.jsp");
		sign.put("appId", StaticVariableUtil.APPID);
		log.info("info"+JSONObject.toJSONString(sign));
    	return JSONObject.toJSONString(sign);
    }
    
    @RequestMapping(value = "reBindingStore" , method=RequestMethod.GET)
    public String reBindingStore(HttpServletResponse res , HttpServletRequest req,HttpSession session , String uuid,String keyid){
    	
    	JuheDemo.setCharset("GBK");
    	log.info("reBinduuid:"+uuid);
        Map<String , String> rs = JuheDemo.check(uuid , keyid);
    	//先判断 uuid（门店id）是否有效，如果无效，则发送给用户提示信息
    	if("1".equalsIgnoreCase(rs.get("result"))){
        	log.info("code request fail:" + rs.get("message"));
        	session.setAttribute("errorMsg", "二维码无效，请联系管理员");
        	return "redirect:/error.jsp";
    	}
    	String userId = (String)session.getAttribute("userId");
    	Map<String, String> where = new HashMap<String ,String>();
    	where.put("weixinid", userId);
    	log.info("update weixinid" + userId);
    	
		List<Users> users = um.selectByWhere(where );
    	Users u = users.get(0);
    	log.info("update weixinid" +JSONObject.toJSONString(u));
    	u.setStoreUuid(uuid);
    	u.setStoreName(rs.get("shop"));
		um.updateByPrimaryKeySelective(users.get(0));
		session.setAttribute("msg", rs.get("shop"));
    	return "redirect:/index.jsp" ;
    }
    
	@Override
	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		applicationContext = ctx;
	}   

}