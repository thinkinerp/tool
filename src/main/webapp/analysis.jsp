<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/taglibs.jsp"%>
<%@include file="/common/css.jsp" %> 
<script type="text/javascript" >
    var ctx = "${ctx}";	
</script>
<title>analysis</title>
  <link href="${ctx}/css/jsoneditor.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx}/js/comboboxConfig.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.scrollTo.min.js"></script>
<script type="text/javascript" src="${ctx}/js/formatter.js"></script>
<script type="text/javascript" src="${ctx}/js/generateSql.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.edatagrid.js"></script>
<script type="text/javascript" src="${ctx}/js/ace.js"></script>
<script type="text/javascript" src="${ctx}/js/jsoneditor.js"></script>


  <style type="text/css">
    #jsoneditor {
      width: 500px;
      height: 500px;
    }
  </style>


</head>
<body>
报表名称：<br/>
   <input id = "kpi" class = 'easyui-combobox'/><br/>
账号：<br/>
   <input id = "user_num" /><br/><br/>
   
   <div id = "userAuth"></div>
   
 <br/><br/>
     <button id="setJSON">获得 JSON</button><br/>
     <button id="jsonbutton" >显示 JSON</button><br/>
     <button id="jsonbutton1" >隐藏 JSON</button><br/>
     <div id = "jsoneditor"></div>		<br/>
		<table id="query_sql_content" class="easyui-datagrid">
			<thead>
			</thead>
		</table>

<br/>
<br/>
<br/>
<br/>
		<table id="query_sql" class="easyui-datagrid">
			<thead>
			</thead>
		</table>
</body>
<script type="text/javascript" src="${ctx}/js/analysis.js"></script>

</html>