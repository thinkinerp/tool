package com.intfocus.hdk.controller;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.intfocus.hdk.dao.CashMapper;
import com.intfocus.hdk.dao.EquipmentMapper;
import com.intfocus.hdk.dao.InstallMapper;
import com.intfocus.hdk.dao.PrinterMapper;
import com.intfocus.hdk.dao.ProjectMapper;
import com.intfocus.hdk.dao.ShopsMapper;
import com.intfocus.hdk.vo.Cash;
import com.intfocus.hdk.vo.Equipment;
import com.intfocus.hdk.vo.Install;
import com.intfocus.hdk.vo.Printer;
import com.intfocus.hdk.vo.Project;
import com.intfocus.hdk.vo.Shops;

@Controller
@RequestMapping("/install")
public class InstallController implements ApplicationContextAware {
    private final static Logger log =  Logger.getLogger(InstallController.class);
    private static ApplicationContext applicationContext; 
    @Resource
    private InstallMapper installmapper ;
    
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
    
   @RequestMapping(value = "submit" , method=RequestMethod.POST)
    @ResponseBody
    public void submit(HttpServletResponse res , HttpServletRequest req ,HttpSession session
    		              ,  Install install,Printer printer,Cash cash,Equipment equipmengt ,
    		              @RequestParam(value = "fileImg", required = false) MultipartFile[] files,HttpServletRequest request) throws Exception {
		       
	   String path = request.getSession().getServletContext().getRealPath("upload");
	   String fileName =null;
	   List<String> filePaths = new ArrayList<String>(); 
	   String fileNameStr =null;
	   File targetFile = null;
	   for(MultipartFile file: files){
		   fileName = file.getOriginalFilename();
		   fileNameStr = (new Date().getTime())+"__"+fileName;
		   filePaths.add(path+"/"+fileNameStr);
		   targetFile = new File(path, fileNameStr);
		       if(!targetFile.exists()){
		           targetFile.mkdirs();
		       }
		       //保存
		       try {
		           file.transferTo(targetFile);
		       } catch (Exception e) {
		           e.printStackTrace();
		       }
	   }
 	   Map<String, String> where = new HashMap<String,String>();
	   where.put("proName",install.getProId());
	   
		List<Project> projects = projectMapper.selectByWhere(where );
		Project i = projects.get(0);
		install.setProId(i.getProId());	
		install.setAttachment_url(org.apache.commons.lang.StringUtils.join(filePaths.toArray(),","));
    	installmapper.insertSelective(install);
    	printerMapper.insertSelective(printer);
    	cashMapper.insertSelective(cash);
    	equipmentMapper.insertSelective(equipmengt);
    	
    }
   @InitBinder("install")    
   public void initBinder1(WebDataBinder binder) {    
           binder.setFieldDefaultPrefix("install.");    
  } 
   @InitBinder("printer")    
   public void initBinder2(WebDataBinder binder) {    
	   binder.setFieldDefaultPrefix("printer.");    
   } 
   @InitBinder("cash")    
   public void initBinder3(WebDataBinder binder) {    
	   binder.setFieldDefaultPrefix("cash.");    
   } 
   @InitBinder("equipment")    
   public void initBinder4(WebDataBinder binder) {    
	   binder.setFieldDefaultPrefix("equipment.");    
   } 
   
   
   
   @RequestMapping(value = "gotoModify" , method=RequestMethod.GET)
   public String gotoModify(HttpServletResponse res , HttpServletRequest req ,HttpSession session
		   , Install install ){
	   
	   JSONObject json = new JSONObject();
	   
	   Map<String, String> where = new HashMap<String,String>();
	   where.put("installId", install.getInstallId());
	   
	   List<Install> installs = installmapper.selectByWhere(where);
	   
	   if(null != installs && installs.size() > 0 ){
		   json.put("install", installs.get(0));
		   where.clear();
		   //找到门店
		   where.put("shopId", installs.get(0).getShopId());
		   List<Shops> shopss = shopsMapper.selectByWhere(where);
		   
		   if(null != shopss && shopss.size()>0){
			   json.put("shop",shopss.get(0));
		   }
		   
		   //找到收款机
		   where.clear();
		   where.put("cashId", installs.get(0).getCashId());
		   List<Cash> cashes = cashMapper.selectByWhere(where);
		   
		   if(null != cashes && cashes.size() > 0 ){
			   json.put("cash",cashes.get(0));
		   }
		   //打印机
		   where.clear();
		   where.put("printerId", installs.get(0).getPrinterId());
		   List<Printer> printers = printerMapper.selectByWhere(where);
		   
		   if(null != printers && printers.size() > 0 ){
			   json.put("printer",printers.get(0));
		   }
		   //采集点
		   where.clear();
		   where.put("eqId", installs.get(0).getEqId());
		   List<Equipment> equipments = equipmentMapper.selectByWhere(where);
		   
		   if(null != equipments && equipments.size() > 0 ){
			   json.put("equipment",equipments.get(0));
		   }
		   //项目
		   where.clear();
		   where.put("proId", installs.get(0).getProId());
		   List<Project> projects = projectMapper.selectByWhere(where);
		   
		   if(null != projects && projects.size() > 0 ){
			   json.put("project",projects.get(0));
		   }
		   
		   
	   }
	   return "forward:/installDetails.jsp?allThing=" + json.toJSONString();
   }
    @RequestMapping(value = "getSome" , method=RequestMethod.POST)
    @ResponseBody
    public void getSome(HttpServletResponse res , HttpServletRequest req ,HttpSession session
    		              , Install install ){
    	
    }
    @RequestMapping(value = "modify" , method=RequestMethod.POST)
    @ResponseBody
    public String modify(HttpServletResponse res , HttpServletRequest req ,HttpSession session
    ,  Install install,Printer printer,Cash cash,Equipment equipmengt ){
    	try{
			installmapper.updateByPrimaryKeySelective(install);
			printerMapper.updateByPrimaryKeySelective(printer);
			cashMapper.updateByPrimaryKeySelective(cash);
			equipmentMapper.updateByPrimaryKeySelective(equipmengt);

    	}catch(Exception e){
			e.printStackTrace();
			return "fail" ;
    	}
		return "success";
    }
	@Override
	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		applicationContext = ctx;
	}   
    public static Map<String, Object> uploadFile(MultipartFile file)
            throws IOException  {
        String fail = "fail";//上传失败状态
        String success = "success";//上传成功状态
        String errorMsg = "imgErrorMsg";//上传错误信息
        String filePath = "/Users/wangyifei/";//上传路径，本来是相对路径，但是在发布后路径会创建在tomcat的bin目录下，所以接口上传的路径是一个难题，我用的是绝对路径，等到发布到Linux环境中，通过配置文件修改路径为Linux中的资源地址【防止工程删除而资源文件不会丢失】，然后重新发布工程时再通过Linux的命令把我们需要的资源文件导入到我们发布的工程项目中。
        String size =  "fileSize";
        String interfaceService = "interfaceService";
         
        long maxFileSize = (Integer.valueOf(size)) * 1024 * 1024;
        String suffix = file.getOriginalFilename().substring(
                file.getOriginalFilename().lastIndexOf("."));
        long fileSize = file.getSize();
        Map<String, Object> map = new HashMap<String, Object>();
        if (suffix.equals(".png") || suffix.equals(".jpg")) {
            if (fileSize < maxFileSize) {
                // System.out.println("fileSize"+fileSize);
                String fileName = file.getOriginalFilename();
                fileName = new String(fileName.getBytes("ISO-8859-1"), "UTF-8");
                File tempFile = new File(filePath, new Date().getTime()
                        + fileName);
 
                try {
                    if (!tempFile.getParentFile().exists()) {
                        tempFile.getParentFile().mkdirs();//如果是多级文件使用mkdirs();如果就一层级的话，可以使用mkdir()
                    }
                    if (!tempFile.exists()) { 
                        tempFile.createNewFile();
                    }
                    file.transferTo(tempFile);
                } catch (IllegalStateException e) {
                		e.printStackTrace();
                }
 
                map.put("SUCESS", success);
                map.put("data",interfaceService + filePath + new Date().getTime() + tempFile.getName());
 
            } else {
                map.put("SUCESS", fail);
                map.put("data", "Image too big");
            }
 
        } else {
            map.put("SUCESS", fail);
            map.put("data", "Image format error");
        }
        return map;
    }
}