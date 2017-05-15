
package com.intfocus.hdk.controller;
import java.io.IOException;
import java.io.Writer;
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
import com.intfocus.hdk.dao.PrinterMapper;
import com.intfocus.hdk.dao.ProjectMapper;
import com.intfocus.hdk.dao.ShopsMapper;
import com.intfocus.hdk.dao.SurveyMapper;
import com.intfocus.hdk.vo.Cash;
import com.intfocus.hdk.vo.Printer;
import com.intfocus.hdk.vo.Shops;
import com.intfocus.hdk.vo.Survey;

@Controller
@RequestMapping("/survey")
public class SurveyController implements ApplicationContextAware {
    private final static Logger log =  Logger.getLogger(SurveyController.class);
    private static ApplicationContext applicationContext; 
    @Resource
    private SurveyMapper surveymapper ;
    @Resource
    private ProjectMapper projectMapper ;
    @Resource
    private ShopsMapper shopsMapper ;
    @Resource
    private PrinterMapper printerMapper ;
    @Resource
    private CashMapper cashMapper ;
    
    @RequestMapping(value = "modify" , method=RequestMethod.GET)
    @ResponseBody
    public String modify(HttpServletResponse res , HttpServletRequest req ,HttpSession session
    		, Survey survey , Printer printer , Cash cash , Shops shops,String callback){
    	
		try {
				surveymapper.updateByPrimaryKeyWithBLOBs(survey);
				printerMapper.updateByPrimaryKeySelective(printer);
				cashMapper.updateByPrimaryKeySelective(cash);
				shopsMapper.updateByPrimaryKeySelective(shops);
		}catch(Exception e){
			e.printStackTrace();
			return callback+"({'message':'fail'})";
		}
		return callback+"({'message':'success'})";
    	
    	
    	
    }
     @RequestMapping(value = "getList" , method=RequestMethod.GET)
    @ResponseBody
    public void getList(HttpServletResponse res , HttpServletRequest req ,HttpSession session
    		              ,  Survey survey ){
    	
    }   
    
    @RequestMapping(value = "getSome" , method=RequestMethod.GET)
    @ResponseBody
    public String getSome(HttpServletResponse res , HttpServletRequest req ,HttpSession session
    		              , Survey survey ){
    	
    	Map<String,String> where = new HashMap<String,String>();
		where.put("proName", survey.getProId());	
		where.put("shopName", survey.getShopId());	
		where.put("shopStation", survey.getShopMerStation());
		if("正序".equalsIgnoreCase(survey.getOrder())){
			where.put("order", "asc");
		}else if("倒序".equalsIgnoreCase(survey.getOrder())){
			where.put("order", "desc");
		}
    	List<Survey> surveys = surveymapper.selectByWhere(where);
    	
		return "getSome("+JSONObject.toJSONString(surveys) +")";	
    }

    
    @RequestMapping(value = "gotoModify" , method=RequestMethod.GET)
    public void gotoModify(HttpServletResponse res , HttpServletRequest req ,HttpSession session
    		              , Survey survey , String callback ){
    	

    	// 取出相关的信息
    	JSONObject json = new JSONObject();
    	Map<String, String> where = new HashMap<String,String>();
    	where.put("surveyId", survey.getSurId());
		// 调研
    	List<Survey> surveys = surveymapper.selectByWhere(where ); 
    	Survey survey1 = surveys.get(0);
    	json.put("survey", survey1);
    	// 收银机 
    	List<Printer> printers = printerMapper.selectByWhere(where ); 
    	Printer printer = printers.get(0);
    	json.put("printer", survey1);
    	
    	// 打印机
    	List<Cash> cashes = cashMapper.selectByWhere(where ); 
    	Cash cash = cashes.get(0);
    	json.put("cash", cash);
    	
    	//门店
    	List<Shops> shops = shopsMapper.selectByWhere(where ); 
    	Shops shop = shops.get(0);
    	json.put("shops", shop);
    	

    	Writer w = null;
		try {
			w = res.getWriter();
			w.write( callback + "("+json.toJSONString()+")");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	@RequestMapping(value = "submit" , method=RequestMethod.GET)
    @ResponseBody
    public String submit(HttpServletResponse res , HttpServletRequest req ,HttpSession session
    		, Survey survey , Printer printer , Cash cash , Shops shops ,String callback){
    	
		try {
				surveymapper.insertSelective(survey);
				printerMapper.insertSelective(printer);
				cashMapper.insertSelective(cash);
				shopsMapper.insertSelective(shops);
		}catch(Exception e){
			e.printStackTrace();
			return callback+"({'message':'fail'})";
		}
		return callback+"({'message':'success'})";
    }
	
	   @InitBinder("survey")    
	   public void initBinder1(WebDataBinder binder) {    
	           binder.setFieldDefaultPrefix("survey.");    
	  } 
	   @InitBinder("printer")    
	   public void initBinder2(WebDataBinder binder) {    
		   binder.setFieldDefaultPrefix("printer.");    
	   } 
	   @InitBinder("cash")    
	   public void initBinder3(WebDataBinder binder) {    
		   binder.setFieldDefaultPrefix("cash.");    
	   } 
	   @InitBinder("shops")    
	   public void initBinder4(WebDataBinder binder) {    
		   binder.setFieldDefaultPrefix("shops.");    
	   } 
	   
	@Override
	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		applicationContext = ctx;
	}   

}