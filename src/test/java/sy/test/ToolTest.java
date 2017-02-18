package sy.test;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wang.tools.dao.DB;
import org.wang.tools.dao.Procdure;
import org.wang.tools.dao.toolMapper;
import org.wang.tools.util.CalenderUtil;
import org.wang.tools.util.DataDictionaryExcel;
import org.wang.tools.vo.PocedureVo;
import org.wang.tools.vo.Relation;
import org.wang.tools.vo.Table;
import org.wang.tools.vo.Tables;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class ToolTest {
	Logger log = Logger.getLogger(this.getClass());
	@Resource private toolMapper tm;
	@Resource private Procdure p;
	
	
	@Test
	public void DataDictionary() {
		
		List<Table> tabs = new ArrayList<Table>();
		
		List<String> tableNames = new ArrayList<String>();
		tableNames.add("analysis_bases");
		tableNames.add("app_bases");
		tableNames.add("dim_date");
		tableNames.add("kpi_bases");
		tableNames.add("kpi_datas");
		tableNames.add("procedurelog");
		tableNames.add("redis_updated_at");
		tableNames.add("report_bases");
		tableNames.add("sys_action_logs");
		tableNames.add("sys_app_versions");
		tableNames.add("sys_bang_booms");
		tableNames.add("sys_barcode_result");
		tableNames.add("sys_comments");
		tableNames.add("sys_devices");
		tableNames.add("sys_group_reports");
		tableNames.add("sys_group_resources");
		tableNames.add("sys_groups");
		tableNames.add("sys_notification_groups");
		tableNames.add("sys_notification_roles");
		tableNames.add("sys_notifications");
		tableNames.add("sys_push_messages");
		tableNames.add("sys_role_resources");
		tableNames.add("sys_roles");
		tableNames.add("sys_template_reports");
		tableNames.add("sys_test_enviroment");
		tableNames.add("sys_thursday_say");
		tableNames.add("sys_user_devices");
		tableNames.add("sys_user_gravatars");
		tableNames.add("sys_user_groups");
		tableNames.add("sys_user_roles");
		tableNames.add("sys_user_stores");
		tableNames.add("sys_users");
		tableNames.add("sys_util_ods_report_relation");
		for(String name : tableNames){
			Table t = new Table() ;
			t.setTableName(name);
			t.setColumns(tm.getTableColumn(name));
			tabs.add(t);
		}
		
		DataDictionaryExcel.generateDataDictionaryExcel(tabs, "/Users/wangyifei/syrDict.xls");
		
	}
	@Test
	public void test() {
		List<String> list = new ArrayList<String>();
		list.add(" insert into sys_groups (group_name,memo) " +
		           " values('群组9',NULL);");
		
		list.add(" insert into sys_groups (group_name,memo) " +
		           " values('群组10',NULL);");
        tm.excute(list);
		
	}
	@Test
	public void sonerTo() throws Exception{
		List<Tables> list = tm.getDateInfo();
		
		for(Tables t : list){
			System.out.println("-- "+t.getType()+":"+CalenderUtil.solarToLunar(t.getType()));
			
			System.out.println("UPDATE yonghuibi.dim_date SET date_china = '"+CalenderUtil.solarToLunar(t.getType())+"' WHERE id = " + t.getField()+";");
			
		}
		
	}
	
	
	
	@Test
    public void test2(){
		
        // 时间表示格式可以改变，yyyyMMdd需要写例如20160523这种形式的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = "1970-01-01 00:00:00";
        // 将字符串的日期转为Date类型，ParsePosition(0)表示从第一个字符开始解析
        Date date = sdf.parse(str, new ParsePosition(0));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // add方法中的第二个参数n中，正数表示该日期后n天，负数表示该日期的前n天
        calendar.add(Calendar.SECOND, 1481177717);
        Date date1 = calendar.getTime();
        String out = sdf.format(date1);
        System.out.println(out);
		
	}	

	@Test
	public void generateInsertTest(){
		
		List<String> list = new ArrayList<String>();
		String insert = "" ;
		list.add("sys_roles");
		list.add("analysis_bases");
		list.add("app_bases");
		list.add("kpi_bases");
		list.add("report_bases");
		list.add("sys_group_reports");
		list.add("sys_group_resources");
		list.add("sys_groups");
		list.add("sys_role_resources");
		list.add("sys_roles");
		list.add("sys_user_groups");
		list.add("sys_user_roles");
		list.add("sys_users");
		
		for (String string : list) {
			insert = insert +"\n"+ generateInsert(string);
		}
		System.out.println(insert);
	}

	public String generateInsert(String table_name){
		
		List<Tables> list= tm.getTableColumn(table_name);
		
		String insert = " replace into yonghuibi." + table_name + "( " ;
		String select = "\n select ";
		insert = insert + list.get(0).getField();
		select = select + list.get(0).getField();
		
		for(int i = 1 ; i < list.size() ; i++){
			insert = insert + "," + list.get(i).getField();
			select = select + "," + list.get(i).getField();
		}
		insert = insert + ")";
		select = select + " from sys_ods." + table_name + ";";

		return insert + select ;
	}	
	
	@Test
	public void getProcedureTest(){
		
		String procedureName = "ETL_report_id_030_main";
		List<PocedureVo> ls = p.getProcedureInfo(procedureName );
	      String[] query = null;
	      String item = null ;

	      String s = null ;

		for (PocedureVo pocedureVo : ls) {
			query = pocedureVo.getProBody().split(";");
			item = null;
			s = null ;
		      for(int i = 0 ; i < query.length ; i ++){
		    	  	
		    	  	item = query[i];
		    	  	
		    	  	if(item.indexOf("from") < 0){

		    	  		continue;
		    	  		
		    	  	}
		    	  	
		    	  	s = item.substring(item.indexOf("from"));
		    	  	System.out.println("{isCheck:\"\", SQL:\"select *  "+ s.replaceAll("\n", " ")+"\"}," );

		      }
		}
		
	}
	
	@Test
	public void Testjson(){
		
		String jsonString = "{\"name\":\"wang\"}";
		
		JSONObject j = null;
		DB db = new DB();
		String kpi_id = "31";
	    Connection conn = db.getConnection();
	    Statement stmt = db.getStatemente(conn);
	    
	    ResultSet rs = db.getResultSet(stmt, "select content from sys_template_reports where report_id = "  + kpi_id);
		
	    try {
			while(rs.next()){
					
					j = JSON.parseObject(jsonString);
					
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		
		if(j.containsKey("identifier")){
			System.out.println(j.get("identifier"));
		}
		
	}
	
	
	@Test
	public void relation() {

        List<PocedureVo> l = null ;
        String kpiId = null;
        String     pattern = "report_data_.*?\\b";	      
        String     p2         = "kpi_.*?\\b";	 
        Pattern   r            = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        Pattern   r2          = Pattern.compile(p2, Pattern.CASE_INSENSITIVE);
        Matcher m 		   = null ;
        Matcher m2 		   = null ;
        String     line 	   = null ;
        List<Table> pre_kpi = new ArrayList<Table>();
        Table preKpiTable = null ;
        List<Table> pre_report_data = new ArrayList<Table>();
        Table preReportDataTable = null ;
        List<String> pre_report_data_tables =null;
        List<String> pre_kpi_tables =null;
        boolean exist = false;
        String pre_report_data_table = null ;
        String pre_kpi_table = null ;
        
        List<Tables> tables = tm.getKpiBases();
        List<Table> ts = new ArrayList<Table>();
        Table table = null;
        Tables column = null;
        List<Tables> columns = null ;

        for(Tables tab : tables){

        		kpiId = tab.getField();
        		table = new Table(); 
        		columns = new ArrayList<Tables>();

        		table.setTableName(tab.getType() + "-" + tab.getComment());
        		if(Integer.parseInt(kpiId)< 10){
        			kpiId = "00" + kpiId ;
        		} else if((Integer.parseInt(kpiId) >= 10) &&(Integer.parseInt(kpiId)< 100) ){
        			kpiId = "0" + kpiId ;
        		}
        		
	        l =  p.getProcedureInfo("ETL_report_id_"  +kpiId);
	        for( PocedureVo pv : l ){
        		    column = new Tables();
		        	pre_report_data_tables = new ArrayList<String>();
		        	pre_kpi_tables = new ArrayList<String>();
		        	line  = pv.getProBody() ;
		        	m2  = r2.matcher(line)  ;
			      m  = r.matcher(line)     ;
			      while( m.find()){
			    	      
			    	     pre_report_data_table = replaceString(m.group(0));
			    	     exist = !pre_report_data_tables.contains(pre_report_data_table)
			    	    		         && (pre_report_data_table.indexOf("where") <= 0);
			    	     if(exist){
			    	    	 	pre_report_data_tables.add(pre_report_data_table);
			    	        }
			    	     
			         }
	
			         while( m2.find()){
		
				         pre_kpi_table = replaceString(m2.group(0)) ;
		    	             exist = !pre_kpi_tables.contains(pre_kpi_table)
		    	            		 		&& (pre_kpi_table.indexOf("where") <= 0);
				        	 if(exist){
				    	    	     pre_kpi_tables.add(pre_kpi_table);
				    	     }
		    	   
		             }

			         System.out.println(tab.getType() + "-" + tab.getComment()+pv.getProName() 
			        		                           +" ------ kpi:" + groupConcate(pre_kpi_tables)
			        		                           + " ------ report_data:" + groupConcate(pre_report_data_tables));
			         column.setField(pv.getProName());
			         column.setType(groupConcate(pre_kpi_tables));
			         column.setComment(groupConcate(pre_report_data_tables));
				     columns.add(column);


			         for(String st : pre_kpi_tables){
			        	 	preKpiTable = new Table();
			        	 	preKpiTable.setTableName( tab.getComment()+ "-" +tab.getType() + ":" +pv.getProName() +"->"+ st );
			        	 	preKpiTable.setColumns(tm.getTableColumn(st));
			        	 	pre_kpi.add(preKpiTable);
			         }
			         for(String st : pre_report_data_tables){
				        	 preReportDataTable = new Table();
				        	 preReportDataTable.setTableName(tab.getComment()+ "-" +tab.getType()+ ":"+pv.getProName()+"->" + st );
				        	 preReportDataTable.setColumns(tm.getTableColumn(st));
				        	 pre_report_data.add(preReportDataTable);
			         }
	        }

	        table.setColumns(columns);
	        	ts.add(table);
        }
        

// System.out.println(JSONObject.toJSONString(ts));
//System.out.println(JSONObject.toJSONString(pre_kpi));
//        DataDictionaryExcel.generateDataDictionaryExcel(ts, "/Users/wangyifei/dataDic/dataDict.xls");        	
//        DataDictionaryExcel.generateDataDictionaryExcel(pre_kpi, "/Users/wangyifei/dataDic/pre_kpi_Dict.xls");        	
        DataDictionaryExcel.generateDataDictionaryExcel(pre_report_data, "/Users/wangyifei/dataDic/pre_report_dataDict.xls");        	
	}
	
	public String groupConcate( List<String> list){
		String rt = "";
		for(String str : list ){
			rt = rt + str + "," ;
		}
		
		return rt ;
		
	}
	
	public String replaceString( String SuperString ) {
		
		List<String> relacedString = new ArrayList<String>();
		relacedString.add(" as");
		relacedString.add("`");
		relacedString.add(")");
		relacedString.add("(");
		relacedString.add(" as");
		relacedString.add("t1");
		relacedString.add("kpi_id");
		relacedString.add("kpi_datas");
		for(String str :relacedString){
			SuperString = SuperString.replace(str, "");
		}
		
		return SuperString ;
	}	
	
	
}
