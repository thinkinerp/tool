package com.intfocus.hdk.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.intfocus.hdk.dao.CashMapper;
import com.intfocus.hdk.vo.Cash;
import com.intfocus.hdk.vo.Printer;

@Controller
@RequestMapping("/cash")
public class CashController implements ApplicationContextAware {
    private final static Logger log =  Logger.getLogger(CashController.class);
    private static ApplicationContext applicationContext; 
    @Resource
    private CashMapper cashMapper  ;
    
    
    @RequestMapping(value = "submit" , method=RequestMethod.POST)
    @ResponseBody
    public void submit(HttpServletResponse res , HttpServletRequest req ,HttpSession session
    		              , Cash cash ){
    	
    }
    
    
    @RequestMapping(value = "getSome" , method=RequestMethod.POST)
    @ResponseBody
    public String getSome(HttpServletResponse res , HttpServletRequest req ,HttpSession session
    		              , Cash cash  ){
    	
    	Map<String,String> where = new HashMap<String,String>();
    	
    	where.put("surId", cash.getSurId());
    	
    	List<Cash> cashes = cashMapper.selectByWhere(where);
    	
		return "cash_getSome"+JSONObject.toJSONString(cashes)+")";	
    }
    @RequestMapping(value = "modify" , method=RequestMethod.POST)
    @ResponseBody
    public void modify(HttpServletResponse res , HttpServletRequest req ,HttpSession session
    		, Cash cash  ){
    	
    }
	@Override
	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		applicationContext = ctx;
	}   

}