package org.wang.tools.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/dashbord")
public class DashboradController {
	private static Logger logger = Logger.getLogger(DashboradController.class);
	
//	@Resource 
//	private 
	
	@RequestMapping(name="save" , method=RequestMethod.POST)
	@ResponseBody
	public void saveDashBoard(  ){
		
	}
	
}
