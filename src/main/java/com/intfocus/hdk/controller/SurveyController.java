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
import com.intfocus.hdk.dao.SurveyMapper;
import com.intfocus.hdk.vo.Survey;

@Controller
@RequestMapping("/survey")
public class SurveyController implements ApplicationContextAware {
    private final static Logger log =  Logger.getLogger(SurveyController.class);
    private static ApplicationContext applicationContext; 
    @Resource
    private SurveyMapper surveymapper ;
    
    @RequestMapping(value = "submit" , method=RequestMethod.POST)
    @ResponseBody
    public void submit(HttpServletResponse res , HttpServletRequest req ,HttpSession session
    		              ,  Survey survey ){
    	
    }
    
    
    @RequestMapping(value = "getSome" , method=RequestMethod.GET)
    @ResponseBody
    public String getSome(HttpServletResponse res , HttpServletRequest req ,HttpSession session
    		              , Survey survey ){
    	
    	Map<String,String> where = new HashMap<String,String>();
		where.put("proName", survey.getProId());	
		where.put("shopName", survey.getShopId());	
    	List<Survey> surveys = surveymapper.selectByWhere(where);
    	
    	
		return JSONObject.toJSONString(surveys) ;	
    }



	@RequestMapping(value = "modify" , method=RequestMethod.POST)
    @ResponseBody
    public void modify(HttpServletResponse res , HttpServletRequest req ,HttpSession session
    		, Survey survey){
    	
    }
	@Override
	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		applicationContext = ctx;
	}   

}