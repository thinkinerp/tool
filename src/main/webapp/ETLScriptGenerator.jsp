<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ETL脚本生成器</title>
<%@include file="/common/css.jsp" %> 
<script type="text/javascript" >
    var ctx = "${ctx}";
</script>
<script type="text/javascript" src="${ctx}/js/comboboxConfig.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.scrollTo.min.js"></script>
<script type="text/javascript" src="${ctx}/js/tools.js"></script>
<script type="text/javascript" src="${ctx}/js/formatter.js"></script>
<script type="text/javascript" src="${ctx}/js/generateSql.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.edatagrid.js"></script>
<script type="text/javascript" src="${ctx}/js/ace.js"></script>
</head>
<body>

<div id = "editor_sql" style="position:absolute;z-index:9"></div>
<div id="cc" class="easyui-layout" style="width:100%;height:400px;">  
    <div data-options="region:'east',iconCls:'icon-reload',title:'数据库的配置',split:true" style="width:200px;">
      <table id = 'dataSource'>
      
      </table>
      
      源数据：<br>
      <input id = "sourceConfig" / ><br>
      目标数据<br>
      <input id = "targetConfig" / ><br>
      
    </div>   
    <div data-options="region:'west',title:'导航',split:true" style="width:200px;">
	<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" 
	    >添加语句块</a>
	<a id="btn1" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick = 'gen()'>生成sql</a>
	<div id="aa" class="easyui-accordion"
     style="width:300px;height:200px;"
	 data-options = "onSelect:changLocation">   
  
   </div>
	<table id="pg" style="width:300px">
	   权限配置：<br/>

	</table> 
	</div>   
    <div id = "editor" data-options="region:'center',title:'center title'" style="padding:5px;background:#eee;">

	</div>   
	
</div> 

	<div id="addSection"  class="easyui-dialog" title="添加语句块" style="width:400px;height:200px;"   
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,toolbar:[{
				text:'保存',
				iconCls:'icon-edit',	
				handler:addSectionF
			}]" > 
	   报表ID：<br/>
      <input id = "id_i" type = 'text' /> <br/>
	   分块标志：<br/>
      <input id = "part_name_i" type = 'text' /> <br/>
       目标数据: <br/>
      <input id = 'target_i' class = 'easyui-combobox' /> <br/>
      源数据: <br/>
      <input id = 'source_i' class = 'easyui-combobox'/> <br/>
	  语句块描述：<br/>
	  <input id = "desc_i" type = 'text' />

	<div id="showSql"  class="easyui-dialog" title="ETL脚本" style="width:1000px;height:500px;"   
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true" >  

   </div>
   
</body>
</html>