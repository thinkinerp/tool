package org.wang.tools.util;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import javax.tools.JavaCompiler.CompilationTask;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.wang.tools.dao.DB;
import org.wang.tools.vo.ParamVo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class ComUtil{

    private static final Logger log = Logger.getLogger(ComUtil.class);
	
    public static List<String> getPartName(String reportId ){
    	
    		List<String> partNames = new ArrayList<String>();
    		
    		DB db = new DB();
    		String json = null ;
    	    Connection conn = db.getConnection();
    	    Statement stmt = db.getStatemente(conn);
    	    
    	    ResultSet rs = db.getResultSet(stmt, "select content from sys_template_reports where report_id = "  + reportId);
    	    try {
    			while(rs.next()){  
    				json = rs.getString("content");
    			}
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}finally{
    			db.close(rs);
    			db.close(stmt);
    			db.close(conn);
    		}
    	    partNames = getKeyValue(json.replace("\\", "") , "identifier");
    		return partNames ;
    }
	public static List<String> getKeyValue(String json , String key){
		
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
	public static  Map<String ,Object> TransListToMap(List<ParamVo> list){
		if(null == list || 0 ==  list.size() ){
			return null ;
		}
		
		
   	  Map<String ,Object> where = new HashMap<String, Object>();
   	  for (ParamVo paramVo : list) {
   		  if(null != paramVo.getKey() && null != paramVo.getValue()){
		    where.put(paramVo.getKey(), paramVo.getValue());
		  }
	}
	  return where ;
	}
	   public static Map<String , Object> getMap(Map<String ,String[]> mp){
	    	Map<String , Object> where = new HashMap<String, Object>();
			for (Map.Entry<String ,String[]> entry : mp.entrySet()) {  
				  
				if(entry.getKey().endsWith("[key]")){
					int i = entry.getKey().indexOf("]") ;
					String sub = entry.getKey().substring(0,i +1 );
					where.put(entry.getValue()[0],mp.get( sub+ "[value]")[0]);

				}
			  
			}  
	      return where ;	
	    	
	    }
	   public static Map<String , Object> transMap(Map<String ,String[]> mp){
		   Map<String , Object> where = new HashMap<String, Object>();
		   for (Map.Entry<String ,String[]> entry : mp.entrySet()) {  
			   
                   if("page_index".equalsIgnoreCase(entry.getKey()) || "rows".equalsIgnoreCase(entry.getKey())){
				       where.put(entry.getKey(),Integer.parseInt(entry.getValue()[0]));
                   } else {
                	   where.put(entry.getKey(),entry.getValue()[0]);
                   }
		   }  
		   return where ;	
		   
	   }
	
	public static String getCongfig(Class clazz , String target){
	    Properties pps = new Properties();
		try {
			InputStream is = clazz.getResourceAsStream("dafaultConditions.properties");
			BufferedReader bf = new BufferedReader(new    InputStreamReader(is));  
			pps.load(bf);
			return pps.getProperty(target);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null ;
	}
	public static String getWhere(Map<String ,Object> map){
		String where  = "where 1=1" ;
		
		for (Map.Entry<String ,Object> entry : map.entrySet()) {  
			  
			if(entry.getKey().startsWith("w.")&&!"".equalsIgnoreCase(entry.getValue().toString() )){
				
				if(entry.getKey().startsWith("w.like.")){
					where  = where + " and " + entry.getKey().replace("w.like.", "")+ " like '%"  + entry.getValue().toString() + "%'" ;
				}else if(entry.getKey().startsWith("w.")&&(entry.getKey().indexOf("like") ==-1)&&!entry.getKey().startsWith("w.userDefine")){
					where  = where + " and " + entry.getKey().replace("w.", "")+ " = '"  + entry.getValue().toString() + "'" ;
					
				}else if(entry.getKey().startsWith("w.userDefine")){
					where  = where + " and ( "  + entry.getValue().toString() + ")" ;					
				}
				
			}
		  
		} 
		
		return where;
	}
	public String generateJavaObject(String className , String query) {
		String vo =   "public class "+captureName(className)+"{\n" ;
	
		String getjson =   "import  java.sql.ResultSet;\n"+
									"import  java.sql.Connection;\n"+
									"import  java.sql.Statement;\n"+
									"import java.util.ArrayList;\n" +
									"import java.util.List;\n" +
									"import java.sql.SQLException;\n" +
									"import com.alibaba.fastjson.JSONObject;\n" +
									" public class @getJSON  {\n"+
									"  public String @getJSON_function( Connection conn) throws SQLException {\n"+
								    "   Statement st = conn.createStatement();\n"+
								    "  ResultSet rs = st.executeQuery(\""+query+"\");\n"+
								    "  List<@vo> records = new ArrayList<@vo>();\n"+
								    "  @vo vo = null;\n" +
								    " while(rs.next()){\n"+
								    "       vo =  new @vo();" ;
		log.info(query);
		getjson = getjson.replaceAll("@vo", "" + captureName(className));
		getjson = getjson.replaceAll("@getJSON_function",  "to_" + captureName(className + "_json"));
		getjson = getjson.replaceAll("@getJSON",   captureName(className+ "_json"));
		
		DB db = new DB();
		Connection conn = db.getConnection() ;
		java.sql.Statement stmt = db.getStatemente(conn) ;
		ResultSet rs = null ;
		rs = db.getResultSet(stmt,"SHOW COLUMNS FROM yonghuibi_s."+className+";");
		
		Map<String , String> column_types = new HashMap<String , String>();;
		try {
			while(rs.next()){
				column_types.put(rs.getString("Field"), rs.getString("Type"));
				
				vo = vo +"  private " +( rs.getString("Type").contains("int") ? "int":"String") + " " +rs.getString("Field") + " ;\n";
				vo = vo + "   public void set"+captureName(rs.getString("Field"))+"( "+( rs.getString("Type").contains("int") ? "int":"String")+" "+rs.getString("Field") +"){\n" +
							   	 "  this."+ rs.getString("Field") +" = "+rs.getString("Field")+";\n" +
							     "}\n";
				vo = vo + "  public " +( rs.getString("Type").contains("int") ? "int":"String") + " get"+captureName(rs.getString("Field"))+"(){\n" +
								"  return this."+rs.getString("Field")+" ;\n"+
								"}\n";
				getjson = getjson + "vo.set" + captureName(rs.getString("Field")) + "(rs.get"+ ( rs.getString("Type").contains("int") ? "Int":"String") + "(\"" +rs.getString("Field") +"\"));\n";
			}
			getjson = getjson + " records.add(vo);\n";
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			db.close(rs);
			db.close(stmt);
		}
		vo = vo + "}\n";
		getjson = getjson + 								     "}\n"+
						"JSONObject json = new JSONObject();" +
						" json.put(\"rows\", records);" +
						"json.put(\"total\", records.size());"  + 
			     		"return json.toJSONString();\n"+
				        "}"+
			     		vo+
			     		"\n}"; 
		return javaObjectInvoke(captureName(className) , getjson ,db.getConnection() );
}
	public String captureName(String name){
		
		char[] cs = name.toCharArray();
		
		if(cs[0] >= 97 && cs[0] <= 122 ){
			cs[0]-=32;
		}
        return String.valueOf(cs);
	}
    public String getResourcePath(Class clazz) {
        String className = clazz.getName();
        
        String classNamePath = className.replace(".", "/") + ".class";
        URL is = clazz.getClassLoader().getResource(classNamePath);
        String path = is.getFile();
        path = StringUtils.replace(path, "%20", " ");
        path = StringUtils.replace(path, StringUtils.replace(className , clazz.getPackage().getName() + ".", "") + ".class", "");
        return path;
    }
	public String javaObjectInvoke( String className , String classBody , Connection conn){
		String currentDir = getResourcePath(this.getClass()); 
		String src = "package org.wang.tools.vo; " +
							classBody;
	       String filename = currentDir.replaceAll("util", "vo") +className+"_json.java";  
	        File file = new File(filename);  
	        FileWriter fw;
			try {
			fw = new FileWriter(file);
	        fw.write(src);  
	        fw.flush();  
	        fw.close();  
	        JavaCompiler jc = ToolProvider.getSystemJavaCompiler();  
	        StandardJavaFileManager fileManager = jc.getStandardFileManager(null, null, null);  
	        Iterable<? extends JavaFileObject> fileObjects = fileManager.getJavaFileObjects(filename);  
	        CompilationTask cTask = jc.getTask( null
	        															, fileManager
	        		  													, null 
	        		  													, Arrays.asList("-classpath","/Users/wangyifei/Documents/java/apache-tomcat-8.0.36/webapps/tool/WEB-INF/lib/fastjson-1.2.7.jar")
	        		  													, null
	        		  													, fileObjects);  
	        cTask.call();  
	        fileManager.close();   
	        Class<?> c = Class.forName("org.wang.tools.vo." + className + "_json"  ,true,Thread.currentThread().getContextClassLoader()); 
	        Object obj = c.newInstance();  
	        Object[] argspara=new Object[]{conn}; 
	        Method method = c.getMethod("to_" + className + "_json" , Connection.class);  
	        return (String)method.invoke(obj,argspara);  
			} catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}  
			return null ;
	}
}
