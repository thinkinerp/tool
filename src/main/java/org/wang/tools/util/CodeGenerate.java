package org.wang.tools.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class CodeGenerate {

	/*
	 * 1. 首先将 xml 文件读到内存中来
	 * 2.然后根据 mybatis xml 文件的格式解析
	 * 3.生成对应的对 js contrallor mapper 的文件
	 * 
	 * - js 的语句包括如
	 * 	- 保存
	 * 
	 * - 修改
	 * - 查询
	 * 
	 * */
	
	public static  void generateXml(){
		
		String path = "/Users/wangyifei/workspace/hdk/src/main/java/com/intfocus/hdk/daomapper/MessageMapper.xml";
		String name = "";
		String jsGetSome = "";
		jsGetSome = " $.ajax({ \n" +
				   " 		url:ctx + '/#name#/getSome',\n" +
				   " 		type:'post',\n" +
				   "		data:#replace#\n"+
				   " 		dataType:'json',\n" +
				   " 		success:function(rs){\n" +
				   "  			/* alert(JSON.stringify(rs); */\n" +
				   "  			scanCode(rs);\n" +
				   " 		},\n" +
				   "  		error:function(rs){\n" +
				   " 		}\n" +
				   "  });	\n" ;
		
		String jsAdd = "";
		jsAdd = 
				"			$.ajax({\n" +
						"				url:ctx + '/#name#/submit',\n" +
						"				type:'post',\n" +
						"				data:#replace#,\n" +
						"				success:function(result){\n" +
						"				}\n"+
						"				error:function(result){\n" +
						"					console.log(result)\n" +
						"		  			showMessage('数据提交失败')\n" +
						"				}\n" +
						"			})\n";
		String modify =
				"			$.ajax({\n" +
						"				url:ctx + '/#name#/modify',\n" +
						"				type:'post',\n" +
						"				data:#replace#,\n" +
						"				success:function(result){\n" +
						"				}\n"+
						"				error:function(result){\n" +
						"					console.log(result)\n" +
						"		  			showMessage('数据提交失败')\n" +
						"				}\n" +
						"			})\n";
		modify = "";
		
		String mybatis = "";
		
		String mapperGetSome = "";
		
		mapperGetSome = 
				"  <select id=\"selectByWhere\" resultMap=\"BaseResultMap\"  parameterType=\"java.util.Map\">\n" +
						"    select \n" +
						"    <include refid=\"Base_Column_List\" />\n" +
						"    ,\n" +
						"    <include refid=\"Blob_Column_List\" />\n" +
						"    from #table# \n" +
						"            on u.weixin_id = sd.user_id\n" +
						"    <where>\n" +
						"    	1=1\n" +
						"          #replace# \n"+
						"    </where>\n" +
						"    <if test=\"null != page and \''!= page\">\n" +
								"	    <if test=\"null != pageSize and \''!= pageSize\">\n" +
								"			limit 	#{page,jdbcType=VARCHAR}, #{pageSize,jdbcType=VARCHAR}		    \n	" +
								"	    </if>\n" +
						"  </select>\n"

				;
		
		String controller = "";
		String mapper = "";
		controller = 
				"    @Resource\n" +
						"    private #mapper# ;\n" +
						"    " +
						"    " +
						"    @RequestMapping(value = \"submit\" , method=RequestMethod.POST)\n" +
						"    @ResponseBody\n" +
						"    public void submit(HttpServletResponse res , HttpServletRequest req ,HttpSession session\n" +
						"    		              ,  #vo# ){\n" +
						"    	\n" +
						"    }\n" +
						"    \n" +
						"    \n" +
						"    @RequestMapping(value = \"getSome\" , method=RequestMethod.POST)\n" +
						"    @ResponseBody\n" +
						"    public void getSome(HttpServletResponse res , HttpServletRequest req ,HttpSession session\n" +
						"    		              , #vo# ){\n" +
						"    	\n" +
						"    }\n" +
						"    @RequestMapping(value = \"modify\" , method=RequestMethod.POST)\n" +
						"    @ResponseBody\n" +
						"    public void modify(HttpServletResponse res , HttpServletRequest req ,HttpSession session\n" +
						"    		, #vo#){\n" +
						"    	\n" +
						"    }"

				;
		
		String json = "{";
		Element element2 = null ;
        File file = new  File(path);
        SAXBuilder builder = new SAXBuilder();
        try {
			Document parse = builder.build(file);
            Element root = parse.getRootElement();
            List<Element> resultMaps = root.getChildren("resultMap");
            
            mapper = root.getAttributeValue("namespace");
            mapper = mapper.substring(mapper.lastIndexOf(".")+1,mapper.length());
            controller = controller.replace("#mapper#",  mapper +" "+ mapper.toLowerCase());
           for (Element element : resultMaps) {

        	   name = element.getAttributeValue("type");
        	   name = name.substring(name.lastIndexOf(".") +1 ,name.length());
        	   
        	   controller = controller.replace("#vo#", name +" "+ name.toLowerCase());
        	   
			List<Element>	results = element.getChildren("result");
			for (int i = 0 ; i < results.size() ; i++) {
				 element2 = results.get(i);
				 json = json +element2.getAttributeValue("property") + "':\n" + "				,'" ;
				 
				 mybatis = mybatis + 						"    		<if test=\"null != "+element2.getAttributeValue("property")+"  and '' != "+element2.getAttributeValue("property")+"\">\n" +
							"				and "+element2.getAttributeValue("column")+"  = #{"+element2.getAttributeValue("property")+",jdbcType=VARCHAR}\n" +
							"			</if>	\n" ;
			}
			
		}
        
           System.out.println("----- getSome----------");
        System.out.println(jsGetSome.replace("#replace#", json.substring(0,json.length()-2) + "}").replace("#name#",name));
        
        System.out.println("----- Add----------");
        
       System.out.println(jsAdd.replace("#replace#", json.substring(0,json.length()-2) + "}").replace("#name#",name));

       System.out.println("----- modify----------");
       
       System.out.println(modify.replace("#replace#", json.substring(0,json.length()-2) + "}").replace("#name#",name));

       System.out.println("----- modify----------");
       
       System.out.println(mapperGetSome.replace("#replace#", mybatis));
       
       System.out.println("-----controller----------");
       
       System.out.println(controller);
       
        } catch (JDOMException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
