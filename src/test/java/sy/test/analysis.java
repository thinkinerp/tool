package sy.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import org.junit.Test;
import org.wang.tools.dao.DB;

import com.alibaba.fastjson.JSONObject;
public class analysis {

	@Test
	public void tesf(){
		
        URL[] urls;
		try {
			urls = new URL[] { new URL("file:/Users/wangyifei/Documents/java/apache-tomcat-8.0.36/webapps/tool/WEB-INF/classes/org/wang/tools/vo/")  };
//			urls = new URL[] { new URL("file:/Users/wangyifei/Documents/")  };
	        URLClassLoader cLoader = new URLClassLoader(urls);  
	        System.out.println(System.getProperty("java.class.path"));
	        Class<?> c = cLoader.loadClass("org.wang.tools.vo.Kpi_budget_group_profit_json");  
	        System.out.println(c);
	        cLoader.close();  	

		} catch ( ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  

	}
	@Test
	public void t(){
		char ch = 'a';
		char ch1 = 'z';
		System.out.println((byte)ch);
		System.out.println((byte)ch1);
	}
	@Test
	public void test1(){
		String s = "from `yonghuibi_s`.`kpi_member_sale_realtime` as t1 where t1.store_name is not null and t1.channelname is null group by t1.store_name order by t1.store_name";
		String regex = "kpi_.*?\\b";
		String regex1 = "( group | order ) by.*\\b";
		
		Pattern p = Pattern.compile(regex1 , Pattern.CASE_INSENSITIVE);
		Pattern p1 = Pattern.compile(regex , Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(s);
		Matcher m1 = p1.matcher(s);
		List<String> strings = new ArrayList<String>();
		while(m.find()){
			s = s.replaceAll(m.group(0),"" );
		}
		while(m1.find()){
			if(!strings.contains(m1.group(0))){
				strings.add(m1.group(0));
			}
		}
		generateJavaObject(strings.get(0) ,"select * " + s);
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

		getjson = getjson.replaceAll("@vo", "" + captureName(className));
		getjson = getjson.replaceAll("@getJSON_function",  "to_" + captureName(className + "_json"));
		getjson = getjson.replaceAll("@getJSON",   captureName(className+ "_json"));
		
		DB db = new DB();
		ResultSet rs = null ;
		rs = db.getResultSet(db.getStatemente(db.getConnection()),"SHOW COLUMNS FROM yonghuibi_s."+className+";");
		
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
        cs[0]-=32;
        return String.valueOf(cs);
	}
	
	public String javaObjectInvoke( String className , String classBody , Connection conn){
		String currentDir = System.getProperty("user.dir"); 
		String src = "package org.wang.tools.vo; " +
							classBody;
	       String filename = currentDir + "/src/main/java/org/wang/tools/vo/"+className+"_json.java";  
	        File file = new File(filename);  
	        FileWriter fw;
			try {
			fw = new FileWriter(file);
	        fw.write(src);  
	        fw.flush();  
	        fw.close();  
	        // 使用JavaCompiler 编译java文件  
	        JavaCompiler jc = ToolProvider.getSystemJavaCompiler();  
	        StandardJavaFileManager fileManager = jc.getStandardFileManager(null, null, null);  
	        Iterable<? extends JavaFileObject> fileObjects = fileManager.getJavaFileObjects(filename);  
	        CompilationTask cTask = jc.getTask(null, fileManager, null, null, null, fileObjects);  
	        cTask.call();  
	        fileManager.close();   
	        // 使用URLClassLoader加载class到内存  
	        URL[] urls = new URL[] { new URL("file:/" + currentDir + "/src/") };  
	        URLClassLoader cLoader = new URLClassLoader(urls);  
	        Class<?> c = cLoader.loadClass("org.wang.tools.vo." + className + "_json");  
	        cLoader.close();  
	        // 利用class创建实例，反射执行方法  
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
