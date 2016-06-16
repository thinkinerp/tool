<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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


</head>
<body>
<div id="cc" class="easyui-layout" style="width:100%;height:400px;">  
    <div data-options="region:'east',iconCls:'icon-reload',title:'East',split:true" style="width:100px;"></div>   
    <div data-options="region:'west',title:'West',split:true" style="width:100px;">
	<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" 
	    >添加语句块</a>
	<a id="btn1" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick = 'gen()'>生成sql</a>
	<div id="aa" class="easyui-accordion"
     style="width:300px;height:200px;"
	 data-options = "onSelect:changLocation">   
  
   </div>
	<table id="pg" style="width:300px"></table> 
	</div>   
    <div id = "editor" data-options="region:'center',title:'center title'" style="padding:5px;background:#eee;">
<!--       <table id = 'section1'>
      <tr style='background:rgb(214,234,294);'>
      <td  >
                    目标字段
      </td>
      <td  >
                    源字段
      </td>
      <td  >
                    聚合类型
      <td>
                 排序
      </td>
      <td>
                 聚合字段
      </td>
      <tr id = 'report_id'>
      <td>report_id(报表的id):</td>
      <td><input  id = 'resouces_col' class = 'easyui-textbox'  /></td>
      <td><input  id = 'aggregate' class = 'aggregate'   /></td>
      <td><input  id = 'order' class = 'orderby'   /></td>
      <td><input  id = 'group' class = 'groupby'   /></td>
      </tr>
      <tr id = 'group_id'>
      <td>group_id(数据权限):</td><td><input  id = 'resouces_col' class = 'easyui-textbox' /></td>
      <td><input  id = 'aggregate' class = 'aggregate' /></td>
      <td><input  id = 'order' class = 'orderby'   /></td>
      <td><input  id = 'group' class = 'groupby'   /></td>
      </tr >
      <tr id = 'period'>
      <td id = 'period'>period(日期):</td><td><input  id = 'resouces_col' class = 'easyui-combobox' /></td>
      <td><input  id = 'aggregate' class = 'aggregate'   /></td>
      <td><input  id = 'order' class = 'orderby'   /></td>
      <td><input  id = 'group' class = 'groupby'   /></td>
      </tr>
      <tr id = 'num'>
      <td>num(排序字段):</td><td><input  id = 'resouces_col'  class = 'easyui-combobox'/></td>
      <td><input  id = 'aggregate' class = 'aggregate'   /></td>
      <td><input  id = 'order' class = 'orderby'   /></td>
      <td><input  id = 'group' class = 'groupby'   /></td>
      </tr>
      <tr id = 'part_name'>
      <td>part_name(内容划分):</td><td><input  id = 'resouces_col' class = 'easyui-textbox' /></td>
      <td><input  id = 'aggregate' class = 'aggregate'   /></td>
      <td><input  id = 'order' class = 'orderby'   /></td>
      <td><input  id = 'group' class = 'groupby'   /></td>
      </tr>
      <tr id = 'level'>
	  <td>level(级次):</td><td><input  id = 'resouces_col' class = 'easyui-textbox' /></td>
      <td><input  id = 'aggregate' class = 'aggregate'   /></td>
      <td><input  id = 'order' class = 'orderby'   /></td>
      <td><input  id = 'group' class = 'groupby'   /></td>
      </tr >
      <tr id = 'dim_date'>
	  <td>dim_date(日期维度):</td><td><input  id = 'resouces_col' class = 'easyui-combobox' /></td>
      <td><input  id = 'aggregate' class = 'aggregate'   /></td>
      <td><input  id = 'order' class = 'orderby'   /></td>
      <td><input  id = 'group' class = 'groupby'   /></td>
      </tr>
      <tr id = 'dim_com'>	  
      <td>dim_com(通用维度):</td><td><input  id = 'resouces_col' class = 'easyui-combobox' /></td>
      <td><input  id = 'aggregate' class = 'aggregate'   /></td>
      <td><input  id = 'order' class = 'orderby'   /></td>
      <td><input  id = 'group' class = 'groupby'   /></td>
      </tr>
      <tr id = 'dim1'>	  
	  <td>dim1(维度1):</td><td><input  id = 'resouces_col' class = 'easyui-combobox' /></td>
      <td><input  id = 'aggregate' class = 'aggregate'   /></td>
      <td><input  id = 'order' class = 'orderby'   /></td>
      <td><input  id = 'group' class = 'groupby'   /></td>
      </tr>
      <tr id = 'dim2'>
	  <td>dim2(维度2):</td><td><input  id = 'resouces_col' class = 'easyui-combobox' /></td>
      <td><input  id = 'aggregate' class = 'aggregate'   /></td>
      <td><input  id = 'order' class = 'orderby'   /></td>
      <td><input  id = 'group' class = 'groupby'   /></td>
      </tr>
      <tr id = 'dim3'>
	  <td>dim3(维度3):</td><td><input  id = 'resouces_col' class = 'easyui-combobox' /></td>
      <td><input  id = 'aggregate' class = 'aggregate'   /></td>
      <td><input  id = 'order' class = 'orderby'   /></td>
      <td><input  id = 'group' class = 'groupby'   /></td>
      </tr>
      <tr id = 'dim4'>
	  <td>dim4(维度4):</td><td><input  id = 'resouces_col' class = 'easyui-combobox' /></td>
      <td><input  id = 'aggregate' class = 'aggregate'   /></td>
      <td><input  id = 'order' class = 'orderby'   /></td>
      <td><input  id = 'group' class = 'groupby'   /></td>
      </tr>
      <tr id = 'dim5'>
	  <td>dim5(维度5):</td><td><input  id = 'resouces_col' class = 'easyui-combobox' /></td>
      <td><input  id = 'aggregate' class = 'aggregate'   /></td>
      <td><input  id = 'order' class = 'orderby'   /></td>
      <td><input  id = 'group' class = 'groupby'   /></td>
      </tr>
      <tr id = 'mea_int1'>
	  <td>mea_int1(整形数据值1):</td><td><input  id = 'resouces_col' class = 'easyui-combobox' /></td>
      <td><input  id = 'aggregate' class = 'aggregate'   /></td>
      <td><input  id = 'order' class = 'orderby'   /></td>
      <td><input  id = 'group' class = 'groupby'   /></td>
      </tr>
      <tr id = 'mea_int2'>
	  <td>mea_int2(整形数据值2):</td><td><input  id = 'resouces_col' class = 'easyui-combobox' /></td>
      <td><input  id = 'aggregate' class = 'aggregate'   /></td>
      <td><input  id = 'order' class = 'orderby'   /></td>
      <td><input  id = 'group' class = 'groupby'   /></td>
      </tr>
      <tr id = 'mea_int3'>
	    <td> mea_int3(整形数据值3):</td><td><input  id = 'resouces_col' class = 'easyui-combobox' /></td>
      <td><input  id = 'aggregate' class = 'aggregate'   /></td>
      <td><input  id = 'order' class = 'orderby'   /></td>
      <td><input  id = 'group' class = 'groupby'   /></td>
      </tr>
      <tr id = 'mea_int3'>
	  <td id = 'mea_int3'>mea_int4(整形数据值4):</td><td><input  id = 'resouces_col' class = 'easyui-combobox' /></td>
      <td><input  id = 'aggregate' class = 'aggregate'   /></td>
      <td><input  id = 'order' class = 'orderby'   /></td>
      <td><input  id = 'group' class = 'groupby'   /></td>
      </tr>
      <tr id = 'mea_int5'>
	  <td>mea_int5(整形数据值5):</td><td><input  id = 'resouces_col' class = 'easyui-combobox' /></td>
      <td><input  id = 'aggregate' class = 'aggregate'   /></td>
      <td><input  id = 'order' class = 'orderby'   /></td>
      <td><input  id = 'group' class = 'groupby'   /></td>
      </tr>
      <tr id = 'mea_float1'>
	  <td>mea_float1(浮点型数据1):</td><td><input  id = 'resouces_col' class = 'easyui-combobox' /></td>
      <td><input  id = 'aggregate' class = 'aggregate'   /></td>
      <td><input  id = 'order' class = 'orderby'   /></td>
      <td><input  id = 'group' class = 'groupby'   /></td>
      </tr>
      <tr id = 'mea_float2'>
	  <td>mea_float2(浮点型数据2):</td><td><input  id = 'resouces_col' class = 'easyui-combobox' /></td>
      <td><input  id = 'aggregate' class = 'aggregate'   /></td>
      <td><input  id = 'order' class = 'orderby'   /></td>
      <td><input  id = 'group' class = 'groupby'   /></td>
      </tr>
      <tr id = 'mea_float3'>
	  <td>mea_float3(浮点型数据3):</td><td><input  id = 'resouces_col' class = 'easyui-combobox' /></td>
      <td><input  id = 'aggregate' class = 'aggregate'   /></td>
      <td><input  id = 'order' class = 'orderby'   /></td>
      <td><input  id = 'group' class = 'groupby'   /></td>
      </tr>
      <tr id = 'mea_float4'>
	  <td>mea_float4(浮点型数据4):</td><td><input  id = 'resouces_col' class = 'easyui-combobox' /></td>
      <td><input  id = 'aggregate' class = 'aggregate'   /></td>
      <td><input  id = 'order' class = 'orderby'   /></td>
      <td><input  id = 'group' class = 'groupby'   /></td>
      </tr>
      <tr id = 'mea_float5'>
	  <td>mea_float5(浮点型数据5):</td><td><input  id = 'resouces_col' class = 'easyui-combobox' /></td>
      <td><input  id = 'aggregate' class = 'aggregate'   /></td>
      <td><input  id = 'order' class = 'orderby'   /></td>
      <td><input  id = 'group' class = 'groupby'   /></td>
      </tr>
      <tr id = 'mea_float6'>
	  <td>mea_float6(浮点型数据6):</td><td><input  id = 'resouces_col' class = 'easyui-combobox' /></td>
      <td><input  id = 'aggregate' class = 'aggregate'   /></td>
      <td><input  id = 'order' class = 'orderby'   /></td>
      <td><input  id = 'group' class = 'groupby'   /></td>
      </tr>
      <tr id = 'mea_float7'>
	  <td>mea_float7(浮点型数据7):</td><td><input  id = 'resouces_col' class = 'easyui-combobox' /></td>
      <td><input  id = 'aggregate' class = 'aggregate'   /></td>
      <td><input  id = 'order' class = 'orderby'   /></td>
      <td><input  id = 'group' class = 'groupby'   /></td>
      </tr>
      <tr id = 'mea_float8'>
	  <td>mea_float8(浮点型数据8):</td><td><input  id = 'resouces_col' class = 'easyui-combobox' /></td>
      <td><input  id = 'aggregate' class = 'aggregate'   /></td>
      <td><input  id = 'order' class = 'orderby'   /></td>
      <td><input  id = 'group' class = 'groupby'   /></td>
      </tr>
      <tr id = 'mea_float9'>
	  <td>mea_float9(浮点型数据9):</td><td><input  id = 'resouces_col' class = 'easyui-combobox' /></td>
      <td><input  id = 'aggregate' class = 'aggregate'   /></td>
      <td><input  id = 'order' class = 'orderby'   /></td>
      <td><input  id = 'group' class = 'groupby'   /></td>
      </tr>
      <tr id = 'mea_float10'>
	 <td> mea_float10(浮点型数据10):</td><td><input  id = 'resouces_col' class = 'easyui-combobox' /></td>
      <td><input  id = 'aggregate' class = 'aggregate'   /></td>
      <td><input  id = 'order' class = 'orderby'   /></td>
      <td><input  id = 'group' class = 'groupby'   /></td>
      </tr>
	  
	  </table> -->
	</div>   
</div> 

	<div id="addSection"  class="easyui-dialog" title="添加语句块" style="width:400px;height:200px;"   
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,toolbar:[{
				text:'保存',
				iconCls:'icon-edit',
				handler:addSectionF
			}]" >  
	   ID号：<br/>
      <input id = "id_i" type = 'text' /> <br/>
	   分块标志：<br/>
      <input id = "part_name_i" type = 'text' /> <br/>
	  语句块描述：<br/>
	  <input id = "desc_i" type = 'text' />
	  
	<div id="showSql"  class="easyui-dialog" title="ETL脚本" style="width:400px;height:200px;"   
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true" >  
   </div> 
</body>
</html>