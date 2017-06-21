package org.wang.tools.controller;

import static org.quartz.DateBuilder.futureDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.DateBuilder.IntervalUnit;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.wang.tools.dao.DB;
import org.wang.tools.dao.Procdure;
import org.wang.tools.dao.toolMapper;
import org.wang.tools.util.ColumnLoader;
import org.wang.tools.util.ComUtil;
import org.wang.tools.util.ExcelReader;
import org.wang.tools.util.HelloWorldQuartz;
import org.wang.tools.util.ProcedureUtil;
import org.wang.tools.vo.ColProperty;
import org.wang.tools.vo.Columns;
import org.wang.tools.vo.ParamVo;
import org.wang.tools.vo.PocedureVo;
import org.wang.tools.vo.ROW;
import org.wang.tools.vo.Tables;
import org.wang.tools.vo.ToJSON;

import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/tool")
public class ToolsController implements ApplicationContextAware {
    private final static Logger log =  Logger.getLogger(ToolsController.class);
	@Resource private toolMapper tm;
	@Resource private Procdure p;
    private static ApplicationContext applicationContext;
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
    
    @RequestMapping(value = "upload" , method=RequestMethod.POST)
    public void upload(HttpServletRequest request,  
            HttpServletResponse response,@RequestParam("file_upload") CommonsMultipartFile file) throws FileUploadException{

    	             String filePath = "" ;

               	filePath  = request.getSession().getServletContext().getRealPath("/") + "/uploadXML/" ;
              	
               	try {
               		// MultipartFile是对当前上传的文件的封装，当要同时上传多个文件时，可以给定多个MultipartFile参数(数组)
               		if (!file.isEmpty()) {
               			
               		 String type = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
               		 String filename = System.currentTimeMillis() + type;		 
               		
               		File destFile = new File(filePath + filename);
               		
               	// FileUtils.copyInputStreamToFile()这个方法里对IO进行了自动操作，不需要额外的再去关闭IO流
               		
               		FileUtils.copyInputStreamToFile(file.getInputStream(), destFile);// 复制临时文件到指定目录下
               		
               		
                
                        ExcelReader excelReader = new ExcelReader();
                        List<ColProperty> user = ColumnLoader.sqlGenerator("importSome.xml", ColumnLoader.class, "sys_users");
                        List<ColProperty> group = ColumnLoader.sqlGenerator("importSome.xml", ColumnLoader.class, "sys_groups");
                        List<ColProperty> role = ColumnLoader.sqlGenerator("importSome.xml", ColumnLoader.class, "sys_roles");
                        // 对读取Excel表格内容测试
                        InputStream f = new BufferedInputStream( new FileInputStream(filePath + filename));
               
                        Map<Integer, String> map = excelReader.readExcelContent( f , user  ,role , group   );
                        
                        List<String> sqllist = map.values().stream().collect(Collectors.toList());
               		
                        tm.excute(sqllist);
                        
               		}
               	} catch (Exception e) {
					e.printStackTrace();
				}  
               	
               	
               	
    }
    
    
    @RequestMapping(value = "getTimeout" , method=RequestMethod.POST)
    @ResponseBody
    public void getTimeout(HttpServletResponse res , HttpServletRequest req ) {
    	     try {
				Writer w = res.getWriter();
				w.write("re");
			} catch (IOException e) {
				// TODO Auto-generated catch block
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

    @RequestMapping(value = "getReportJSON" , method=RequestMethod.POST)
    @ResponseBody    
    public void getReportJSON(HttpServletResponse response,String report_id){
    	
    	String rtn = "" ;
    	
    	DB db = new DB();
    	Connection  conn = db.getConnection();
    	java.sql.Statement stmt = db.getStatemente(conn);
    	ResultSet rs = db.getResultSet(stmt, 
    			                                         "select content from sys_template_reports where report_id = " + report_id );
    	
    	try {
			while(rs.next()){
				rtn = rs.getString("content");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			db.close(rs);
			db.close(stmt);
			db.close(conn);
		}
    	
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		response.setContentType("application/json");
		Writer w;
		try {
			w = response.getWriter();
			w.write(rtn);
		} catch (IOException e) {
			e.printStackTrace();
		}	
    }
    
    @RequestMapping(value = "getToExcuteSQl" , method=RequestMethod.POST)
    @ResponseBody    
    public void getToExcuteSQl(HttpServletResponse response,HttpServletRequest request, String sql, String user_num){
		String regex = "kpi_.*?\\b";
		String regex1 = "( group | order ) by.*\\b";
		boolean isDistrict = false ;
		boolean isClass = false ;
		boolean isDept = false ;
		
		String where = " where 1=1" ;
		String oWhere = sql.substring(sql.lastIndexOf("where") , sql.length());
		String[] clauses = oWhere.replace("where", "").split("and") ;
		for (String string : clauses) {
			if(!string.contains("t3")){
				where = where +" and " +string;
			}
		}
		
		isDistrict = oWhere.contains("district_ids");
		isClass = oWhere.contains("class_ids");
		isDept = oWhere.contains("dept_ids");
		
		Pattern p1 = Pattern.compile(regex1 , Pattern.CASE_INSENSITIVE);
		Pattern p = Pattern.compile(regex , Pattern.CASE_INSENSITIVE);
		Matcher m1 = p1.matcher(sql);
		Matcher m = p.matcher(sql);
		List<String> strings = new ArrayList<String>();
		while(m1.find()){
			sql = sql.replaceAll(m1.group(0),"" );
		}
		while(m.find()){
			if(!strings.contains(m.group(0))){
				strings.add(m.group(0));
			}
		}
			DB db = new DB();
			
			Connection conn = db.getConnection();
			Statement stmt = db.getStatemente(conn);
			ResultSet rs = db.getResultSet(stmt,    " select gr.district_ids , gr.class_ids , gr.dept_ids  " +
																		"   from sys_group_resources as gr "+
																		"where group_id in ("+
																		"        select group_id from sys_user_groups "+
																		"       where user_id in ("+
																		"               select id from sys_users where user_num = '"+user_num +"'"
																		+")"
																		+")");
			log.info( " select gr.district_ids , gr.class_ids , gr.dept_ids  " +
																		"   from sys_group_resources as gr "+
																		"where group_id in ("+
																		"        select group_id from sys_user_groups "+
																		"       where user_id in ("+
																		"               select id from sys_users where user_num = '"+user_num +"'"
																		+")"
																		+")");
			try {
				while(rs.next()){
					
					if(null != rs.getString("district_ids") && isDistrict &&  !"all".equalsIgnoreCase(rs.getString("district_ids"))){
						where = where + " and store_group = '"  + rs.getString("district_ids") + "'";
					}

					if(null != rs.getString("class_ids") && isClass  && !"all".equalsIgnoreCase(rs.getString("class_ids"))){
						where = where + " and cat1_id in ("  + rs.getString("class_ids") + ")";
					}
					
					if(null != rs.getString("dept_ids") && isDept && !"all".equalsIgnoreCase(rs.getString("dept_ids"))){
						where = where + " and store_id in ("  + rs.getString("dept_ids") + ")";
					}
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}finally{
				db.close(rs);
				db.close(stmt);
				db.close(conn);
			}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		response.setContentType("application/json");
		Writer w;
		try {
			w = response.getWriter();
			
			w.write("select * from yonghuibi_s." + strings.get(0) + " as t1 " + where + " limit 50");
		} catch (IOException e) {
			e.printStackTrace();
		}	
}
    
    
    @RequestMapping(value = "getData" , method=RequestMethod.POST)
    @ResponseBody    
    public void getData(HttpServletResponse response,HttpServletRequest request ,String sql, String user_num){
		String regex = "kpi_.*?\\b";
		String regex1 = "( group | order ) by.*\\b";
		
		boolean isDistrict = false ;
		boolean isClass = false ;
		boolean isDept = false ;
		
		String where = " where 1=1" ;
		String oWhere = sql.substring(sql.lastIndexOf("where") , sql.length());
		String[] clauses = oWhere.replace("where", "").split("and") ;
		for (String string : clauses) {
			if(!string.contains("t3")){
				where = where +" and " +string;
			}
		}
		
		isDistrict = oWhere.contains("district_ids");
		isClass = oWhere.contains("class_ids");
		isDept = oWhere.contains("dept_ids");
		
		
		Pattern p1 = Pattern.compile(regex1 , Pattern.CASE_INSENSITIVE);
		Pattern p = Pattern.compile(regex , Pattern.CASE_INSENSITIVE);
		Matcher m1 = p1.matcher(sql);
		Matcher m = p.matcher(sql);
		List<String> strings = new ArrayList<String>();
		while(m1.find()){
			sql = sql.replaceAll(m1.group(0),"" );
		}
		while(m.find()){
			if(!strings.contains(m.group(0))){
				strings.add(m.group(0));
			}
		}
		ComUtil comUtil = new ComUtil();
		
		if(null != user_num){
			DB db = new DB();
			
			Connection conn = db.getConnection();
			Statement stmt = db.getStatemente(conn);
			ResultSet rs = db.getResultSet(stmt,    " select gr.district_ids , gr.class_ids , gr.dept_ids  " +
																		"   from sys_group_resources as gr "+
																		"where group_id in ("+
																		"        select group_id from sys_user_groups "+
																		"       where user_id in ("+
																		"               select id from sys_users where user_num = '"+user_num +"'"
																		+")"
																		+")");
			
			try {
				while(rs.next()){
					
					if(null != rs.getString("district_ids") && isDistrict &&  !"all".equalsIgnoreCase(rs.getString("district_ids"))){
						where = where + " and store_group = '"  + rs.getString("district_ids") + "'";
					}

					if(null != rs.getString("class_ids") && isClass  && !"all".equalsIgnoreCase(rs.getString("class_ids"))){
						where = where + " and cat1_id in ("  + rs.getString("class_ids") + ")";
					}
					
					if(null != rs.getString("dept_ids") && isDept && !"all".equalsIgnoreCase(rs.getString("dept_ids"))){
						where = where + " and store_id in ("  + rs.getString("dept_ids") + ")";
					}
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}finally{
				db.close(rs);
				db.close(stmt);
				db.close(conn);
			}
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		response.setContentType("application/json");
		Writer w;
		try {
			w = response.getWriter();
			
			w.write(comUtil.generateJavaObject(strings.get(0) 
																	,"select * from yonghuibi_s." + strings.get(0) + " as t1 " + where + " limit 50"
																	,request.getSession().getServletContext().getRealPath("/") ));
		} catch (IOException e) {
			e.printStackTrace();
		}	
    }
    
    @RequestMapping(value = "getUserAuth" , method=RequestMethod.POST)
    @ResponseBody    
    public void getUserAuth(HttpServletResponse response,String userNum , String reportId){
    	
    		String rtn = "" ;
		DB db = new DB();
		Connection conn = db.getConnection() ;
		java.sql.Statement stmt = db.getStatemente(conn);
		ResultSet rs = null ;
		rs = db.getResultSet( stmt  ,"	SELECT list.user_name , list.group_id , list.group_name , list.role_name " +
													"				, gr.district_ids , gr.class_ids , gr.dept_ids , gr1.report_id" +
													"	FROM yonghuibi.user_power_list AS list" +
													" LEFT JOIN yonghuibi.sys_group_resources AS gr ON list.group_id =  gr.group_id" +
													" LEFT JOIN yonghuibi.sys_group_reports AS gr1 on gr1.group_id = list.group_id AND gr1.report_id = " + reportId +
													"	WHERE list.user_num = '"+(userNum == null ? "-1100":userNum)+"';");
		log.info("reportId:"+reportId);
		try {
			while(rs.next()){
				rtn = rtn + " user_name : " + rs.getString("user_name");
				rtn = rtn + " | role_name : " + rs.getString("role_name");
				rtn = rtn + " | group_id : " + rs.getString("group_id");
				rtn = rtn + " | group_name : " + rs.getString("group_name");
				rtn = rtn + "  | district_ids : " + rs.getString("district_ids");
				rtn = rtn + "  | class_ids : " + rs.getString("class_ids");
				rtn = rtn + "  | dept_ids : " + rs.getString("dept_ids");
				rtn = rtn + "  |  用户所在群组是否有此报表的权限 : " + (rs.getString("report_id") == null ? "否":"是" );
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.close(rs);
			db.close(stmt);
			db.close(conn);
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Writer w;
		try {
			w = response.getWriter();
			w.write(rtn);
		} catch (IOException e) {
			e.printStackTrace();
		}	
}
    
    @RequestMapping(value = "getGridCols" , method=RequestMethod.POST)
    @ResponseBody    
    public void getGridCols(HttpServletResponse response,String sql){
		DB db = new DB();
		List<Columns> columns = new ArrayList<Columns>();
		Columns column = null ;
		String regex = "kpi_.*?\\b";
		log.info(sql);
		Pattern p = Pattern.compile(regex , Pattern.CASE_INSENSITIVE);
		
		Matcher m = p.matcher(sql);
		List<String> strings = new ArrayList<String>();
		while(m.find()){
			if(!strings.contains(m.group(0))){
				strings.add(m.group(0));
			}
		}
		Connection conn = db.getConnection() ;
		java.sql.Statement stmt = db.getStatemente(conn);
		ResultSet rs = null ;
		rs = db.getResultSet( stmt  ,"SHOW COLUMNS FROM yonghuibi_s."+strings.get(0)+";");
		try {
			while(rs.next()){
				column = new Columns();
				column.setField(rs.getString("Field"));
				column.setTitle(rs.getString("Field"));
				column.setWidth("100");
				columns.add(column);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.close(rs);
			db.close(stmt);
			db.close(conn);
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		response.setContentType("application/json");
		Writer w;
		try {
			w = response.getWriter();
			w.write(JSONObject.toJSONString(columns));
		} catch (IOException e) {
			e.printStackTrace();
		}	
    }
    @RequestMapping(value = "getSplitSql" , method=RequestMethod.POST)
    @ResponseBody    
    public void getSplitSql(HttpServletResponse response,String kpi_id){
    	
    		String procedureName = null ;
    		int count = 0 ;
    		procedureName = "ETL_report_id_" +(Integer.parseInt(kpi_id) > 9 ? "0" + kpi_id : "00" + kpi_id ) ;
		List<PocedureVo> ls =ProcedureUtil.getProcedureBodys(procedureName, p);
	      String[] query = null;
	      String item = null ;
  	  	  int in = 0;
	      String s = null ;
	      List<ROW> list = new ArrayList<ROW>();
	      ROW row = null ;
	      List<String> partNames = ComUtil.getPartName(kpi_id);
		for (PocedureVo pocedureVo : ls) {
			query = pocedureVo.getProBody().toLowerCase().split(";");
			
			item = null;
			s = null ;
		      for(int i = 0 ; i < query.length ; i ++){
		    	  	
		    	  	item = query[i];
		    	  	
		    	  	if(item.indexOf("from") < 0){

		    	  		continue;
		    	  		
		    	  	}
		    	  	count++ ;
		    	  	
		    	  	item = item.replaceAll("\n", " ");
		    	  	in =  (item.indexOf("group by") == -1? item.length():item.indexOf("group by"));
		    	  	s = item.substring(item.indexOf("from") ,in);
		    	  	in = (s.indexOf("order by") == -1? s.length():s.indexOf("order by"));
		    	  	s = s.substring(s.indexOf("from") , in );
		    	  	
		    	  	if(s.contains("report_data")){
		    	  		continue ;
		    	  	}
		    	  	
		    	  	row = new ROW();
		    	  	for(String str : partNames){
		    	  		  if(item.indexOf(str) > 0){
		    	  			  row.setPartName(str);
		    	  		  }
		    	  	}
		    	  	row.setIsCheck("");
		    	  	row.setSQL(s.replaceAll("\n", " "));
		    	  	log.info(s.replaceAll("\n", " "));
		    	  	list.add(row);
		      }
		}
		JSONObject json = new JSONObject();
		json.put("rows", list);
		json.put("total", list.size());
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		response.setContentType("application/json");
		Writer w;
		try {
			w = response.getWriter();
			w.write(json.toJSONString());
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }
    @RequestMapping(value = "getAnyForCombobox" , method=RequestMethod.POST)
    @ResponseBody
	public List<Map<String , String>> getAnyForCombobox(
			@RequestBody  List<ParamVo>  where ){
		log.info(JSONObject.toJSONString(where));
		Map<String , Object> map = ComUtil.TransListToMap(where);
		String wher = null ;
		wher = ComUtil.getWhere(map);
		return tm.selectAnyForComboBox(map.get("tableName").toString(), map.get("idField").toString()
				      ,map.get("nameField").toString(), wher);
	}
    
/*    @RequestMapping(value = "selectMenu" , method=RequestMethod.POST)
    @ResponseBody
   public void selectMenu(HttpServletResponse res){
    	  List<Order> order =  tm.selectMenu();
    	  
    	  String json = JSONObject.toJSONString(order);
    	  log.info(json);
    	   	res.setCharacterEncoding("UTF-8");
        	res.setContentType("text/html;charset=UTF-8");
        	Writer w;
        	try {
        		w = res.getWriter();
        		w.write(json);
        	} catch (IOException e) {
        		e.printStackTrace();
        	}
    }*/
    
    @RequestMapping(value = "updataWeighting" , method=RequestMethod.POST)
    @ResponseBody  
    public void updateDishWeighting(String dish_name){
    	          tm.updateDishWeighting(dish_name);
    }
    
	@RequestMapping(value="add" , method=RequestMethod.POST)
	@ResponseBody
	public void add() throws SchedulerException{
		Scheduler sched = (Scheduler) applicationContext.getBean("scheduler");
		
	    sched.clear();

	      String schedId = sched.getSchedulerInstanceId();

	      int count = 1;

	      JobDetail job = newJob(HelloWorldQuartz.class).withIdentity("job_" + count, schedId) // put triggers in group
	                                                                                            // named after the cluster
	                                                                                            // node instance just to
	                                                                                            // distinguish (in logging)
	                                                                                            // what was scheduled from
	                                                                                            // where
	          .requestRecovery() // ask scheduler to re-execute this job if it was in progress when the scheduler went
	                             // down...
	          .build();

	      SimpleTrigger trigger = newTrigger().withIdentity("triger_" + count, schedId)
	          .startAt(futureDate(1, IntervalUnit.SECOND))
	          .withSchedule(simpleSchedule().withIntervalInSeconds(2).repeatForever()).build();

	      System.out.println(job.getKey() + " will run at: " + trigger.getNextFireTime() + " and repeat: "
	                + trigger.getRepeatCount() + " times, every " + trigger.getRepeatInterval() / 1000 + " seconds");
	      sched.scheduleJob(job, trigger);
	      
	      sched.start();
	      
	      try {
	          Thread.sleep(100L * 1000L);
	        } catch (Exception e) {
	          //
	        }

	      System.out.println("------- Shutting Down --------------------");
	        sched.shutdown(true);	     
	}
	@Override
	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		applicationContext = ctx;
	}   
}
