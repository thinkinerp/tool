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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.intfocus.hdk.dao.CashMapper;
import com.intfocus.hdk.dao.EquipmentMapper;
import com.intfocus.hdk.dao.PrinterMapper;
import com.intfocus.hdk.dao.ProblemMapper;
import com.intfocus.hdk.dao.ProjectMapper;
import com.intfocus.hdk.dao.ShopsMapper;
import com.intfocus.hdk.vo.Problem;

@Controller
@RequestMapping("/problem")
public class ProblemController implements ApplicationContextAware {
    private final static Logger log =  Logger.getLogger(ProblemController.class);
    private static ApplicationContext applicationContext; 
    
    @Resource
    private ProblemMapper problemMapper ;
    
    @Resource
    private PrinterMapper printerMapper;
    @Resource
    private CashMapper cashMapper;
    @Resource
    private EquipmentMapper equipmentMapper;
    @Resource
    private ProjectMapper projectMapper;
    
    @Resource
    private ShopsMapper shopsMapper;
    
    
    @RequestMapping(value = "getEquipmentList " , method=RequestMethod.GET)
    @ResponseBody     
    public String getEquipmentList (HttpServletResponse res , HttpServletRequest req ,HttpSession session
    		, Problem problem ){
    		Map<String, String> where = new HashMap<String ,String>();
    		where.put("proName",problem.getProName());
    		where.put("shopName",problem.getShopName());
			;
    		return "problem_getEquipmentList("+JSONObject.toJSONString(equipmentMapper.selectByWhere(where))+")";

    }
    @RequestMapping(value = "submit" , method=RequestMethod.GET)
    @ResponseBody     
    public String submit(HttpServletResponse res , HttpServletRequest req ,HttpSession session
            , Problem problem ){
    	try{
    		problemMapper.insertSelective(problem);
	    	return "{'message':'success'}";
	  }catch(Exception e){
		  e.printStackTrace();
		  return "{'message':'fail'}";
	  }
    }
    @RequestMapping(value = "modify" , method=RequestMethod.GET)
    @ResponseBody     
    public String modify(HttpServletResponse res , HttpServletRequest req ,HttpSession session
    		, Problem problem ){
    	try{
    		problemMapper.updateByPrimaryKeySelective(problem);
    		return "modify("+"{'message':'success'}"+")";
    	}catch(Exception e){
    		e.printStackTrace();
    		return "modify("+"{'message':'fail'}"+")";
    	}
    }
    @InitBinder("problem")    
    public void initBinder1(WebDataBinder binder) {    
            binder.setFieldDefaultPrefix("problem.");    
   } 
    @RequestMapping(value = "getCount" , method=RequestMethod.GET)
    @ResponseBody    
    public String getCount(HttpServletResponse res , HttpServletRequest req ,HttpSession session
            , Problem problem  ){
    	Map<String,String> where = new HashMap<String,String>();
    	
    	where.put("proName", problem.getProName());
    	
    	List<Problem> problems = problemMapper.getCount(where);
    	
		return "problem_getCount("+JSONObject.toJSONString(problems)+")";	
    }
    
    @RequestMapping(value = "getSome" , method=RequestMethod.GET)
    @ResponseBody
    public String getSome(HttpServletResponse res , HttpServletRequest req ,HttpSession session
    		              , Problem problem  ){
    	
    	Map<String,String> where = new HashMap<String,String>();
    	
    	where.put("proName", problem.getProName());
    	where.put("problemObject", problem.getProblemObject());
    	where.put("state", problem.getState());
    	
    	List<Problem> problems = problemMapper.selectByWhere(where);
    	
		return "problem_getSome("+JSONObject.toJSONString(problems)+")";	
    }
        @RequestMapping(value = "gotoModify" , method=RequestMethod.GET) 
    public String gotoModify(HttpServletResponse res , HttpServletRequest req ,HttpSession session
            , Problem problem  ){
    	String json= "" ;
    	Map<String,String> where = new HashMap<String,String>();
    	
    	where.put("proName", problem.getProName());
    	where.put("problemObject", problem.getProblemObject());
    	where.put("state", problem.getState());
    	
    	List<Problem> problems = problemMapper.selectByWhere(where);
    	
    	json = JSONObject.toJSONString(problems.get(0));
    	
		return "redirect:/problemDetails.jsp?allThings=" + json;
    }
    
	@Override
	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		applicationContext = ctx;
	}   

}