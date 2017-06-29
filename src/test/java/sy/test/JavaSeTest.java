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
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import org.junit.Test;
import org.wang.tools.dao.DB;
import org.wang.tools.dao.toolMapper;
import org.wang.tools.util.CalenderUtil;
import org.wang.tools.util.ColumnLoader;
import org.wang.tools.util.ComUtil;
import org.wang.tools.vo.ColProperty;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JavaSeTest {

	
	private toolMapper too  ;
	
	@Test
	public void testSubString(){
		String str = "Users/wangyifei/Documents/java/apache-tomcat-8.0.36/webapps/hdk/upload/1494728602837__4.pic.jpg";
		System.out.println(str.substring(0,str.indexOf("upload") ));
	}
	@Test
	public void test() {
//		List<ColProperty> cols = ColumnLoader.sqlGenerator("importSome.xml", ColumnLoader.class, "sys_users");
//	
//	    System.out.println(JSONObject.toJSON(cols)) ;
		Integer i = new Integer(5) ;
		System.out.println(modify(i));
		System.out.println(i);
	}
	
	public Integer modify( Integer  i ){
		
		return new Integer(++i);
	}
	@Test
	public void getClauses(){
		
		String sql = 	    		  "select\n" +
	    		  "  37\n" +
	    		  " , t3.group_id  \n" +
	    		  " , null as period \n" +
	    		  " ,  concat(t1.store_group,if(t1.amountTB = 0 , 0  , if(t1.amount -t1.amountTB > 0 ,1-ABS(t1.amountTB -t1.amount)/t1.amountTB ,1- ABS(t1.amountTB -t1.amount)/t1.amountTB)) , t1.store_name ) as num \n" +
	    		  " ,'grid1'     as part_name\n" +
	    		  " , 2           as `LEVEL`\n" +
	    		  " , t1.store_id       as dim1\n" +
	    		  " , t1.store_name    as dim_com\n" +
	    		  " , t1.store_group   as dim2\n" +
	    		  " ,'sale'   as dim3\n" +
	    		  " ,'kpi_financial_area_mans_sales_daily'     as dim4\n" +
	    		  " , t1.amount/10000		 as mea_float1 /*销售金额*/\n" +
	    		  " , (t1.amount -t1.amountTB )/t1.amountTB           as mea_float2 /*来客数'*/\n" +
	    		  " , (t1.amount-t1.amountHB)/t1.amountHB           as mea_float3/*毛利'*/\n" +
	    		  " , null			             as mea_float4 /*客单价*/\n" +
	    		  " , null								 as mea_float5 /**/\n" +
	    		  " , null 								 as mea_float6 /**/\n" +
	    		  " , null 								 as mea_float7 /*毛利率'*/\n" +
	    		  " , null 								 as mea_float8 /*'动销量*/\n" +
	    		  " , null                   			 as mea_float9 /*'动销率*/\n" +
	    		  " , now()         					 as updatetime /*'*/\n" +
	    		  "from yonghuibi_s.kpi_financial_area_mans_sales_daily as t1\n" +
	    		  "left join (\n" +
	    		  "select group_id ,dept_ids,class_ids,gids ,\n" +
	    		  "       district_ids,province_ids from  `yonghuibi`.sys_group_resources \n" +
	    		  "       where group_id in (select group_id from yonghuibi.sys_group_reports where report_id = 37 )\n" +
	    		  ") t3 on 1=1 \n" +
	    		  "where  1=1 and t1.store_class = 'sf' and  (t3.district_ids = 'all' or locate(t1.store_group,t3.district_ids) > 0)\n" +
	    		  "          and (t3.dept_ids = 'all' or locate(t1.store_id,t3.dept_ids) > 0)  \n" +
	    		  "          and \n" +
	    		  "           store_group is not null and store_id is not  null and cat1_id is null\n" ;
		
		String[] clauses = sql.substring(sql.lastIndexOf("where") , sql.length()).replace("where", "").split("and") ;
		 
		for (String string : clauses) {
			if(!string.contains("t3")){
				System.out.println(string);
			}
		}
		
	}
	
	@Test
	public void getProcedureTest1(){
		
		DB db = new DB();
		String body = "" ;
		Connection conn = db.getConnection();
		Statement stmt = db.getStatemente(conn);
		ResultSet rs = db.getResultSet(stmt, " show create procedure ETL_report_id_030_main");
		
		try {
			while(rs.next()){
				body = rs.getString("Create Procedure");
				System.out.println(body);
				body = body.substring(body.toLowerCase().indexOf("begin") ,body .toLowerCase().lastIndexOf("end"));
				System.out.println(body);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.close(conn);
			db.close(stmt);
			db.close(rs);
		}
		
	}
	
	@Test
	public void CalendarTest() throws Exception{
        System.out.println(CalenderUtil.lunarToSolar("19901204", false));  
        System.out.println(CalenderUtil.lunarToSolar("19841021", true));  
        System.out.println("************");  
        System.out.println(CalenderUtil.solarToLunar("20170101"));  
        System.out.println(CalenderUtil.solarToLunar("20170102"));  
        System.out.println(CalenderUtil.solarToLunar("20170103"));  
        System.out.println(CalenderUtil.solarToLunar("20170104"));  
	}	
	
	
	@Test
	public void test111(){
		
		int x = 2 ;
		int y =-1 ;
		int z = 2 ;
		if(x<y)
			if(y<0) z = 0;
			else z += 1;
		
		System.out.println(z);
	}
	
	@Test
	public void reg(){
	      String line = "delete from  yonghuibi.report_data_037_main where report_id = 37 ;\n" +
	    		  "DELETE FROM `yonghuibi`.`kpi_datas` WHERE kpi_id = 37;  \n" +
	    		  "/*\n" +
	    		  " * 本月区\n" +
	    		  " * */	\n" +
	    		  "	INSERT into yonghuibi.report_data_037_main(\n" +
	    		  "			  report_id,group_id,period,num,\n" +
	    		  "              part_name,`level`,dim1,dim_com\n" +
	    		  "              ,mea_float1,mea_float2,mea_float3\n" +
	    		  "			  ,mea_float4,mea_float5,mea_float6,mea_float7\n" +
	    		  "              ,mea_float8,mea_float9,load_time\n" +
	    		  ")\n" +
	    		  "select\n" +
	    		  "  37\n" +
	    		  " , t3.group_id  \n" +
	    		  " , null as period \n" +
	    		  " ,   concat(t1.store_group,\n" +
	    		  "     case when t1.store_name = '全店' then '-2' else '-1' end\n" +
	    		  "      )  as num \n" +
	    		  " ,'grid1'     as part_name\n" +
	    		  " , 1           as `LEVEL`\n" +
	    		  " , null       as dim1\n" +
	    		  " , concat(t1.store_group,t1.store_name)    as dim_com\n" +
	    		  " , t1.amount/10000		 as mea_float1 /*销售金额*/\n" +
	    		  " , ( t1.amount- t1.amountTB)/t1.amountTB           as mea_float2 /*来客数'*/\n" +
	    		  " , (t1.amount-t1.amountHB)/t1.amountHB           as mea_float3/*毛利'*/\n" +
	    		  " , null			             as mea_float4 /*客单价*/\n" +
	    		  " , null								 as mea_float5 /**/\n" +
	    		  " , null 								 as mea_float6 /**/\n" +
	    		  " , null 								 as mea_float7 /*毛利率'*/\n" +
	    		  " , null 								 as mea_float8 /*'动销量*/\n" +
	    		  " , null                   			 as mea_float9 /*'动销率*/\n" +
	    		  " , now()         					 as updatetime /*'*/\n" +
	    		  "from yonghuibi_s.kpi_financial_area_mans_sales_daily as t1\n" +
	    		  "left join (\n" +
	    		  "select group_id ,dept_ids,class_ids,gids ,\n" +
	    		  "       district_ids,province_ids from  `yonghuibi`.sys_group_resources \n" +
	    		  "       where group_id in (select group_id from yonghuibi.sys_group_reports where report_id = 37 )\n" +
	    		  ") t3 on 1=1 \n" +
	    		  "where  (t3.district_ids = 'all' or locate(t1.store_group,t3.district_ids) > 0 ) and \n" +
	    		  "           store_group is not null and store_id is  null and cat1_id is null\n" +
	    		  "order by t1.store_group\n" +
	    		  ";\n" +
	    		  "	INSERT into yonghuibi.report_data_037_main(\n" +
	    		  "			  report_id,group_id,period,num,\n" +
	    		  "              part_name,`level`,dim1,\n" +
	    		  "              dim_com,dim2 , dim3 ,dim4\n" +
	    		  "              ,mea_float1,mea_float2,mea_float3\n" +
	    		  "			  ,mea_float4,mea_float5,mea_float6,mea_float7\n" +
	    		  "              ,mea_float8,mea_float9,load_time\n" +
	    		  ")\n" +
	    		  "select\n" +
	    		  "  37\n" +
	    		  " , t3.group_id  \n" +
	    		  " , null as period \n" +
	    		  " ,  concat(t1.store_group,if(t1.amountTB = 0 , 0  , if(t1.amount -t1.amountTB > 0 ,1-ABS(t1.amountTB -t1.amount)/t1.amountTB ,1- ABS(t1.amountTB -t1.amount)/t1.amountTB)) , t1.store_name ) as num \n" +
	    		  " ,'grid1'     as part_name\n" +
	    		  " , 2           as `LEVEL`\n" +
	    		  " , t1.store_id       as dim1\n" +
	    		  " , t1.store_name    as dim_com\n" +
	    		  " , t1.store_group   as dim2\n" +
	    		  " ,'sale'   as dim3\n" +
	    		  " ,'kpi_financial_area_mans_sales_daily'     as dim4\n" +
	    		  " , t1.amount/10000		 as mea_float1 /*销售金额*/\n" +
	    		  " , (t1.amount -t1.amountTB )/t1.amountTB           as mea_float2 /*来客数'*/\n" +
	    		  " , (t1.amount-t1.amountHB)/t1.amountHB           as mea_float3/*毛利'*/\n" +
	    		  " , null			             as mea_float4 /*客单价*/\n" +
	    		  " , null								 as mea_float5 /**/\n" +
	    		  " , null 								 as mea_float6 /**/\n" +
	    		  " , null 								 as mea_float7 /*毛利率'*/\n" +
	    		  " , null 								 as mea_float8 /*'动销量*/\n" +
	    		  " , null                   			 as mea_float9 /*'动销率*/\n" +
	    		  " , now()         					 as updatetime /*'*/\n" +
	    		  "from yonghuibi_s.kpi_financial_area_mans_sales_daily as t1\n" +
	    		  "left join (\n" +
	    		  "select group_id ,dept_ids,class_ids,gids ,\n" +
	    		  "       district_ids,province_ids from  `yonghuibi`.sys_group_resources \n" +
	    		  "       where group_id in (select group_id from yonghuibi.sys_group_reports where report_id = 37 )\n" +
	    		  ") t3 on 1=1 \n" +
	    		  "where  (t3.district_ids = 'all' or locate(t1.store_group,t3.district_ids) > 0)\n" +
	    		  "          and (t3.dept_ids = 'all' or locate(t1.store_id,t3.dept_ids) > 0)  \n" +
	    		  "          and \n" +
	    		  "           store_group is not null and store_id is not  null and cat1_id is null\n" +
	    		  ";\n" +
	    		  "	\n" +
	    		  "	INSERT into yonghuibi.report_data_037_main(\n" +
	    		  "			  report_id,group_id,period,num,\n" +
	    		  "              part_name,`level`,dim1,dim_com\n" +
	    		  "              ,mea_float1,mea_float2,mea_float3\n" +
	    		  "			  ,mea_float4,mea_float5,mea_float6,mea_float7\n" +
	    		  "              ,mea_float8,mea_float9,load_time\n" +
	    		  ")\n" +
	    		  "select\n" +
	    		  "  37\n" +
	    		  " , t3.group_id  \n" +
	    		  " , null as period \n" +
	    		  " ,   concat(t1.store_group,\n" +
	    		  "    case when t1.store_name = '全店' then '-2' else '-1' end\n" +
	    		  "      )  as num \n" +
	    		  " ,'grid2'     as part_name\n" +
	    		  " , 1           as `LEVEL`\n" +
	    		  " , null       as dim1\n" +
	    		  " , concat(t1.store_group,t1.store_name)    as dim_com\n" +
	    		  " , t1.profit/10000		 as mea_float1 /*销售金额*/\n" +
	    		  " , ( t1.profit-  t1.profitTB  )/  t1.profitTB          as mea_float2 /*来客数'*/\n" +
	    		  " ,(  t1.profit- t1.profitHB   )/ t1.profitHB         as mea_float3/*毛利'*/\n" +
	    		  " , null			             as mea_float4 /*客单价*/\n" +
	    		  " , null								 as mea_float5 /**/\n" +
	    		  " , null 								 as mea_float6 /**/\n" +
	    		  " , null 								 as mea_float7 /*毛利率'*/\n" +
	    		  " , null 								 as mea_float8 /*'动销量*/\n" +
	    		  " , null                   			 as mea_float9 /*'动销率*/\n" +
	    		  " , now()         					 as updatetime /*'*/\n" +
	    		  "from yonghuibi_s.kpi_financial_area_mans_sales_daily as t1\n" +
	    		  "left join (\n" +
	    		  "select group_id ,dept_ids,class_ids,gids ,\n" +
	    		  "       district_ids,province_ids from  `yonghuibi`.sys_group_resources \n" +
	    		  "       where group_id in (select group_id from yonghuibi.sys_group_reports where report_id = 37 )\n" +
	    		  ") t3 on 1=1 \n" +
	    		  "where  (t3.district_ids = 'all' or locate(t1.store_group,t3.district_ids) > 0 ) and \n" +
	    		  "           store_group is not null and store_id is  null and cat1_id is null\n" +
	    		  "order by t1.store_group\n" +
	    		  ";\n" +
	    		  "	INSERT into yonghuibi.report_data_037_main(\n" +
	    		  "			  report_id,group_id,period,num,\n" +
	    		  "              part_name,`level`,dim1,\n" +
	    		  "              dim_com,dim2 , dim3 ,dim4\n" +
	    		  "              ,mea_float1,mea_float2,mea_float3\n" +
	    		  "			  ,mea_float4,mea_float5,mea_float6,mea_float7\n" +
	    		  "              ,mea_float8,mea_float9,load_time\n" +
	    		  ")\n" +
	    		  "select\n" +
	    		  "  37\n" +
	    		  " , t3.group_id  \n" +
	    		  " , null as period \n" +
	    		  " ,  concat(t1.store_group ,if(t1.profit - t1.profitTB , t1.profit - t1.profitTB  , t1.profitTB - t1.profit ), t1.store_name ) as num \n" +
	    		  " ,'grid2'     as part_name\n" +
	    		  " , 2           as `LEVEL`\n" +
	    		  " , t1.store_id       as dim1\n" +
	    		  " , t1.store_name    as dim_com\n" +
	    		  " , t1.store_group   as dim2\n" +
	    		  " ,'profit'   as dim3\n" +
	    		  " ,'kpi_financial_area_mans_sales_daily'     as dim4\n" +
	    		  " , t1.profit/10000		 as mea_float1 /*销售金额*/\n" +
	    		  " , ( t1.profit - t1.profitTB  )/  t1.profitTB          as mea_float2 /*来客数'*/\n" +
	    		  " ,(  t1.profit- t1.profitHB   )/ t1.profitHB         as mea_float3/*毛利'*/\n" +
	    		  " , null			             as mea_float4 /*客单价*/\n" +
	    		  " , null								 as mea_float5 /**/\n" +
	    		  " , null 								 as mea_float6 /**/\n" +
	    		  " , null 								 as mea_float7 /*毛利率'*/\n" +
	    		  " , null 								 as mea_float8 /*'动销量*/\n" +
	    		  " , null                   			 as mea_float9 /*'动销率*/\n" +
	    		  " , now()         					 as updatetime /*'*/\n" +
	    		  "from yonghuibi_s.kpi_financial_area_mans_sales_daily as t1\n" +
	    		  "left join (\n" +
	    		  "select group_id ,dept_ids,class_ids,gids ,\n" +
	    		  "       district_ids,province_ids from  `yonghuibi`.sys_group_resources \n" +
	    		  "       where group_id in (select group_id from yonghuibi.sys_group_reports where report_id = 37 )\n" +
	    		  ") t3 on 1=1 \n" +
	    		  "where  (t3.district_ids = 'all' or locate(t1.store_group,t3.district_ids) > 0)\n" +
	    		  "          and (t3.dept_ids = 'all' or locate(t1.store_id,t3.dept_ids) > 0)  \n" +
	    		  "          and \n" +
	    		  "           store_group is not null and store_id is not  null and cat1_id is null\n" +
	    		  ";\n" +
	    		  "	INSERT into yonghuibi.report_data_037_main(\n" +
	    		  "			  report_id,group_id,period,num,\n" +
	    		  "              part_name,`level`,dim1,dim_com\n" +
	    		  "              ,mea_float1,mea_float2,mea_float3\n" +
	    		  "			  ,mea_float4,mea_float5,mea_float6,mea_float7\n" +
	    		  "              ,mea_float8,mea_float9,load_time\n" +
	    		  ")\n" +
	    		  "select\n" +
	    		  "  37\n" +
	    		  " , t3.group_id  \n" +
	    		  " , null as period \n" +
	    		  " ,   concat(t1.store_group,\n" +
	    		  "     case when t1.store_name = '全店' then '-2' else '-1' end\n" +
	    		  "      )  as num \n" +
	    		  " ,'grid3'     as part_name\n" +
	    		  " , 1           as `LEVEL`\n" +
	    		  " , null       as dim1\n" +
	    		  " , concat(t1.store_group,t1.store_name)    as dim_com\n" +
	    		  " , t1.profitRate		 as mea_float1 /*销售金额*/\n" +
	    		  " , (t1.profitRate - t1.profitTBRate)/ t1.profitTBRate              as mea_float2 /*来客数'*/\n" +
	    		  " , (t1.profitRate -t1.profitHBRate)/  t1.profitHBRate             as mea_float3/*毛利'*/\n" +
	    		  " , null			             as mea_float4 /*客单价*/\n" +
	    		  " , null								 as mea_float5 /**/\n" +
	    		  " , null 								 as mea_float6 /**/\n" +
	    		  " , null 								 as mea_float7 /*毛利率'*/\n" +
	    		  " , null 								 as mea_float8 /*'动销量*/\n" +
	    		  " , null                   			 as mea_float9 /*'动销率*/\n" +
	    		  " , now()         					 as updatetime /*'*/\n" +
	    		  "from yonghuibi_s.kpi_financial_area_mans_sales_daily as t1\n" +
	    		  "left join (\n" +
	    		  "select group_id ,dept_ids,class_ids,gids ,\n" +
	    		  "       district_ids,province_ids from  `yonghuibi`.sys_group_resources \n" +
	    		  "       where group_id in (select group_id from yonghuibi.sys_group_reports where report_id = 37 )\n" +
	    		  ") t3 on 1=1 \n" +
	    		  "where  (t3.district_ids = 'all' or locate(t1.store_group,t3.district_ids) > 0 ) and \n" +
	    		  "           store_group is not null and store_id is  null and cat1_id is null\n" +
	    		  "order by t1.store_group\n" +
	    		  ";\n" +
	    		  "	INSERT into yonghuibi.report_data_037_main(\n" +
	    		  "			  report_id,group_id,period,num,\n" +
	    		  "              part_name,`level`,dim1,\n" +
	    		  "              dim_com,dim2 , dim3 ,dim4\n" +
	    		  "              ,mea_float1,mea_float2,mea_float3\n" +
	    		  "			  ,mea_float4,mea_float5,mea_float6,mea_float7\n" +
	    		  "              ,mea_float8,mea_float9,load_time\n" +
	    		  ")\n" +
	    		  "select\n" +
	    		  "  37\n" +
	    		  " , t3.group_id  \n" +
	    		  " , null as period \n" +
	    		  " ,  concat(t1.store_group , if(t1.profitRate - t1.profitTBRate > 0 , t1.profitRate - t1.profitTBRate , t1.profitTBRate - t1.profitRate) , t1.store_name ) as num \n" +
	    		  " ,'grid3'     as part_name\n" +
	    		  " , 2           as `LEVEL`\n" +
	    		  " , t1.store_id       as dim1\n" +
	    		  " , t1.store_name    as dim_com\n" +
	    		  " , t1.store_group   as dim2\n" +
	    		  " ,'profitRate'   as dim3\n" +
	    		  " ,'kpi_financial_area_mans_sales_daily'     as dim4\n" +
	    		  " , t1.profitRate		 as mea_float1 /*销售金额*/\n" +
	    		  " , (t1.profitRate - t1.profitTBRate)/ t1.profitTBRate              as mea_float2 /*来客数'*/\n" +
	    		  " , (t1.profitRate -t1.profitHBRate)/  t1.profitHBRate             as mea_float3/*毛利'*/\n" +
	    		  " , null			             as mea_float4 /*客单价*/\n" +
	    		  " , null								 as mea_float5 /**/\n" +
	    		  " , null 								 as mea_float6 /**/\n" +
	    		  " , null 								 as mea_float7 /*毛利率'*/\n" +
	    		  " , null 								 as mea_float8 /*'动销量*/\n" +
	    		  " , null                   			 as mea_float9 /*'动销率*/\n" +
	    		  " , now()         					 as updatetime /*'*/\n" +
	    		  "from yonghuibi_s.kpi_financial_area_mans_sales_daily as t1\n" +
	    		  "left join (\n" +
	    		  "select group_id ,dept_ids,class_ids,gids ,\n" +
	    		  "       district_ids,province_ids from  `yonghuibi`.sys_group_resources \n" +
	    		  "       where group_id in (select group_id from yonghuibi.sys_group_reports where report_id = 37 )\n" +
	    		  ") t3 on 1=1 \n" +
	    		  "where  (t3.district_ids = 'all' or locate(t1.store_group,t3.district_ids) > 0)\n" +
	    		  "          and (t3.dept_ids = 'all' or locate(t1.store_id,t3.dept_ids) > 0)  \n" +
	    		  "          and \n" +
	    		  "           store_group is not null and store_id is not  null and cat1_id is null\n" +
	    		  ";\n" +
	    		  "insert into `yonghuibi`.`kpi_datas`(`kpi_id` ,`group_id` ,`period` ,`num` ,`dim` ,`value1` ,`value2` ,`is_last` ,`load_time` )\n" +
	    		  "SELECT\n" +
	    		  "37 as kpi_id\n" +
	    		  ",t3.group_id as group_id\n" +
	    		  ",null as period\n" +
	    		  ",@rownum := @rownum + 1 as num\n" +
	    		  ",store_name  as dim\n" +
	    		  ", amount as value1\n" +
	    		  ",null as value2\n" +
	    		  ",1 as is_last\n" +
	    		  ",now() as load_time\n" +
	    		  "from (\n" +
	    		  "select amount/10000 as amount , concat(store_group,store_name) as store_name\n" +
	    		  "from yonghuibi_s.kpi_financial_area_mans_sales_daily\n" +
	    		  "where store_group is not null and store_id is  null and cat1_id is null and concat(store_group,store_name) = '上海区可比店'\n" +
	    		  ")   as t1\n" +
	    		  "left join (\n" +
	    		  "select group_id ,dept_ids,class_ids,gids ,\n" +
	    		  "       district_ids,province_ids from  `yonghuibi`.sys_group_resources \n" +
	    		  "       where group_id in (select group_id from yonghuibi.sys_group_reports where report_id = 37 )\n" +
	    		  ") t3 on 1=1 \n" +
	    		  "order by null\n" +
	    		  ";\n" +
	    		  "insert into `yonghuibi`.`kpi_datas`(`kpi_id` ,`group_id` ,`period` ,`num` ,`dim` ,`value1` ,`value2` ,`is_last` ,`load_time` )\n" +
	    		  "SELECT\n" +
	    		  "37 as kpi_id\n" +
	    		  ",t3.group_id as group_id\n" +
	    		  ",null as period\n" +
	    		  ",@rownum := @rownum + 1 as num\n" +
	    		  ",null  as dim\n" +
	    		  ", t1.num as value1\n" +
	    		  ",null as value2\n" +
	    		  ",0 as is_last\n" +
	    		  ",now() as load_time\n" +
	    		  "from\n" +
	    		  "(\n" +
	    		  "select 4*1000 as num\n" +
	    		  "union \n" +
	    		  "select 4*1000 as num\n" +
	    		  "union \n" +
	    		  "select 3*1000 as num\n" +
	    		  "union \n" +
	    		  "select 2*1000 as num\n" +
	    		  "union \n" +
	    		  "select 5*1000 as num\n" +
	    		  "union \n" +
	    		  "select 7*1000 as num\n" +
	    		  "union \n" +
	    		  "select 9*1000 as num\n" +
	    		  ")t1 , (select @rownum := 0) t0\n" +
	    		  "left join (select group_id as report_data_099_banner group_id,dept_ids,class_ids,district_ids,province_ids from `yonghuibi`.sys_group_resources\n" +
	    		  "    			where group_id in (select group_id from `yonghuibi`.sys_group_reports where report_id = 37)) t3 on 1=1\n" +
	    		  "order by null\n" +
	    		  ";";
	      
	      String[] query = line.split(";");
	      String item = null ;

	      String s = null ;

	      for(int i = 0 ; i < query.length ; i ++){
	    	  	
	    	  	item = query[i];
	    	  	
	    	  	if(item.indexOf("from") < 0){

	    	  		continue;
	    	  		
	    	  	}
 	  	
	    	  	s = item.substring(item.indexOf("from"));
		      	
	    	  	System.out.println("{isCheck:\"\", SQL:\"select *  "+ s.replaceAll("\n", " ")+"\"}," );

	      }

//	      String s = "	INSERT into yonghuibi.`report_data_037_main`(\n" ;
//	      String pattern = "report_data_.*?(main|banner|y])";	      
//	      String p2         = "(kpi_.* as )";	 
//	      Pattern r = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
//	    
//	      Matcher m = r.matcher(line);
//        
//	      while( m.find()){
//	    	     System.out.println(m.group(0));
//	      }
//	      Pattern r2 = Pattern.compile(p2, Pattern.CASE_INSENSITIVE);
//	      
//	      Matcher m2 = r2.matcher(line);
//	      
//	      while( m2.find()){
//	    	  System.out.println(m2.group(0));
//	    	   
//	      }
	}
	@Test
	public void gettest(){
		ComUtil c = new ComUtil();
		System.out.println(c.getResourcePath(this.getClass()));
	}
	
	@Test
	public void getProcedureTest(){
		String s = "from `yonghuibi_s`.`kpi_member_sale_daily` as t1 left join ( select max(sales_date) as maxdate from `yonghuibi_s`.`kpi_member_sale_daily` ) d on 1=1 where 1=1 and t1.store_id is not null and t1.cat1_id is null and t1.channelname is null and d.maxdate = t1.sales_date order by t1.sales_date ,t1.store_name";
		String regex = "kpi_.*?\\b";
		String regex1 = "order by .*\\b";
		
		Pattern p = Pattern.compile(regex1 , Pattern.CASE_INSENSITIVE);
		
		Matcher m = p.matcher(s);
		List<String> strings = new ArrayList<String>();
		while(m.find()){
			System.out.println(m.group(0));
			if(!strings.contains(m.group(0))){
				strings.add(m.group(0));
			}
		}
		
	}
	
	@Test
	public void Regex(){
		
		// 两个重要的类： Pattern 、Matcher
		
		String terget = "insertinsert" ;
		
		String regex = "(insert){2}";
		
		Pattern p = Pattern.compile(regex , Pattern.CASE_INSENSITIVE);
		
		Matcher m = p.matcher(terget);
		
//		while(m.find()){
//			System.out.println(m.group(0));
//		}
		
		//字符、单词的重复：+、?、*、{n}、{n,m}、
		System.out.println("".matches(""));
		//范围[]
		
		
		//特殊字符的
		
		//边界符：（^）开头、（$）结尾、/b
		
		// && and |
		
		// URl
		System.out.println("https://localhost:9090");
		System.out.println("^ord".matches("\\w{1,6}"));
		System.out.println("A".matches("[\\w&&[^a-z]]"));
		System.out.println("！@#￥%……&*（）——+=!~`@#$%&*()-_=+ord".matches(".{1,90}"));
		System.out.println("://localhost:9090".matches("^((https|http)://)?.*"));
		
	}
	@Test
    public void test3(){
		
        // 时间表示格式可以改变，yyyyMMdd需要写例如20160523这种形式的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = "1970-01-01 08:00:00";
        // 将字符串的日期转为Date类型，ParsePosition(0)表示从第一个字符开始解析
        Date date = sdf.parse(str, new ParsePosition(0));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // add方法中的第二个参数n中，正数表示该日期后n天，负数表示该日期的前n天
        calendar.add(Calendar.SECOND, 1481181316);
        Date date1 = calendar.getTime();
        String out = sdf.format(date1);
        System.out.println(out);
		
	}	
	@Test
	public void Testjson(){
		DB db = new DB();
		String kpi_id = "31";
		String json = null ;
	    Connection conn = db.getConnection();
	    Statement stmt = db.getStatemente(conn);
	    
	    ResultSet rs = db.getResultSet(stmt, "select content from sys_template_reports where report_id = "  + kpi_id);
	    try {
			while(rs.next()){  
				json = rs.getString("content");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    List<String> list = getKeyValue(json.replace("\\", "") , "identifier");
		for(String s : list){
			System.out.println(s);
		}
	}
	@Test
	public void getKeyValueTest(){
		List<String> list = getKeyValue("{\"identifier\":\"gds\"}" , "identifier");
		
		for(String s : list){
			System.out.println(s);
		}
		
	}
	public List<String> getKeyValue(String json , String key){
		
		List<String> list = new ArrayList<String>();
		if( json.startsWith("{")){
			
			JSONObject jo = JSON.parseObject(json);
			for (Map.Entry<String, Object> entry : jo.entrySet()) {  
			    if(entry.getValue().toString().startsWith("{") || entry.getValue().toString().startsWith("[")){
			    		list.addAll(getKeyValue(entry.getValue().toString() ,key));
			    } else {
			    	  if(entry.getKey().equalsIgnoreCase(key)){
			    		  if(!list.contains(entry.getValue().toString())){
			    			  list.add(entry.getValue().toString());
			    		  }
			    	  }
			    }
			} 
		} else if( json.startsWith("[")){
			
			JSONArray ja = JSON.parseArray(json);

			ja.forEach((var) ->{
				if( true){
					JSONObject jo = (JSONObject)var;
					for (Map.Entry<String, Object> entry : jo.entrySet()) {  
					    if(entry.getValue().toString().startsWith("{") || entry.getValue().toString().startsWith("[")){
					    		list.addAll(getKeyValue(entry.getValue().toString() ,key));
					    } else {
					    	  if(entry.getKey().equalsIgnoreCase(key)){
					    		  if(!list.contains(entry.getValue().toString())){
					    			  list.add(entry.getValue().toString());
					    		  }
					    	  }
					    }
					} 
				}
			});
		}
		return list ;
	}
	
    @Test
	public void test5(){	
        
    		String s = "from  yonghuibi_s.kpi_bravo_realtime as t1 left join ( select group_id ,dept_ids,class_ids,gids ,        district_ids,province_ids from  `yonghuibi`.sys_group_resources        where group_id in (select group_id from yonghuibi.sys_group_reports where report_id = 32 ) ) t3 on 1=1  where  t1.store_class = 'class2' and t1.FlagType = 'shopgroup' and t1.typeflag = 1                      and (t3.dept_ids = 'all' or locate(t1.store_id,t3.dept_ids) > 0)                      group by t1.sales_hour,t3.group_id,t1.store_name  ,t1.cat1_name   order by";
    		
    		System.out.println(s.indexOf("select"));
    		System.out.println(s.indexOf("from"));
    		System.out.println(s.indexOf("group by"));
    		System.out.println(s.length());
    		System.out.println(s.substring(s.indexOf("from")  , (s.indexOf("group by") == -1 ) ? s.length() : s.indexOf("group by") ));
    	
    }
    @Test
	public void test2(){
     	//取得根目录路径  
       	String rootPath=getClass().getResource("/").getFile().toString();  
       	System.out.println(rootPath);
        //当前目录路径  
        String currentPath1=getClass().getResource(".").getFile().toString();  
        System.out.println(currentPath1);
        String currentPath2=getClass().getResource("").getFile().toString();  
        System.out.println(currentPath2);
        //当前目录的上级目录路径  
        String parentPath=getClass().getResource("../../").getFile().toString();  
    	
	}
}
