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
import com.intfocus.hdk.dao.ProjectMapper;
import com.intfocus.hdk.dao.SalesDataMapper;
import com.intfocus.hdk.dao.UsersMapper;
import com.intfocus.hdk.util.JuheDemo;
import com.intfocus.hdk.util.StaticVariableUtil;
import com.intfocus.hdk.util.WeiXinUserInfoUtil;
import com.intfocus.hdk.vo.Project;
import com.intfocus.hdk.vo.SalesData;
import com.intfocus.hdk.vo.Users;

@Controller
@RequestMapping("/project")
public class ProjectController implements ApplicationContextAware {
    private final static Logger log =  Logger.getLogger(ProjectController.class);
    private static ApplicationContext applicationContext; 
    
    
    @Resource
    private ProjectMapper projectmapper ;
    
    @RequestMapping(value = "submit" , method=RequestMethod.POST)
    @ResponseBody
    public void submit(HttpServletResponse res , HttpServletRequest req ,HttpSession session
    		              ,  Project project ){
    	
    }
    
    @RequestMapping(value = "setLast" , method=RequestMethod.GET)
    @ResponseBody
    public Integer setLast(HttpServletResponse res , HttpServletRequest req ,HttpSession session
            , Project project){
    	
    	Project p =  null;
    	try{
    	Map<String ,String> where = new HashMap<String,String>();
    	where.put("proName", project.getProName());
    	List<Project	> projects = projectmapper.selectByWhere(where);
    	
		if(null != projects && projects.size()>0){
			projectmapper.setAllNotIsLast();
			p = projects.get(0);	
			p.setIsLast(1);
			projectmapper.updateByPrimaryKeySelective(p);
		}	
		}catch(Exception e){
			e.printStackTrace();
			return 0 ;
		}
    	return 1;
    }
    
    @RequestMapping(value = "getSome" , method=RequestMethod.GET)
    @ResponseBody
    public String getSome(HttpServletResponse res , HttpServletRequest req ,HttpSession session
    		              , Project project ){
    	log.info("pro:"+project.getProName());

    	
    	
    	Map<String ,String> where = new HashMap<String,String>();
    	where.put("proName", project.getProName());
    	if(null != project.getIsLast()){
    		where.put("isLast", project.getIsLast().toString());
    	}
    	List<Project	> projects = projectmapper.selectByWhere(where);

    	return JSONObject.toJSONString(projects);
    }
    @RequestMapping(value = "modify" , method=RequestMethod.POST)
    @ResponseBody
    public void modify(HttpServletResponse res , HttpServletRequest req ,HttpSession session
    		, Project project){
    	
    }
    
	@Override
	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		applicationContext = ctx;
	}   

}