package org.wang.tools.controller;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wang.tools.dao.toolMapper;
import org.wang.tools.vo.Tables;
import org.wang.tools.vo.ToJSON;

import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/tool")
public class ToolsController {
    private final static Logger log =  Logger.getLogger(ToolsController.class);
	@Resource private toolMapper tm;
    @RequestMapping(value = "getColums" , method=RequestMethod.POST)
    @ResponseBody
    public void getColums(HttpServletResponse res , HttpServletRequest req 
    		              , String tableName){
		String json = JSONObject.toJSONString(tm.getTableInfo(tableName));
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
    @RequestMapping(value = "getTables" , method=RequestMethod.POST)
    @ResponseBody
    public void getTables(HttpServletResponse res , HttpServletRequest req ){
    	ToJSON tj = new ToJSON();
    	List<Tables> ts =  tm.getTablesName() ;
    	tj.setRows(ts);
    	
    	tj.setTotal(ts.size());
    	String json = JSONObject.toJSONString(tj);
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
 
    
}
