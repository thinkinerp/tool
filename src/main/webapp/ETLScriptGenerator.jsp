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
<script type="text/javascript" src="${ctx}/js/tools.js"></script>
<script type="text/javascript" src="${ctx}/js/formatter.js"></script>
<script type="text/javascript" src="${ctx}/js/generateSql.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.edatagrid.js"></script>


</head>
<body>
<div id="cc" class="easyui-layout" style="width:100%;height:400px;">  
    <div data-options="region:'east',iconCls:'icon-reload',title:'East',split:true" style="width:100px;"></div>   
    <div data-options="region:'west',title:'West',split:true" style="width:100px;">
	<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加语句块</a>
	<a id="btn1" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick = 'gen()'>生成sql</a>
	<div id="aa" class="easyui-accordion"
     style="width:300px;height:200px;"
	 data-options = "onSelect:changLocation">   
    <div title="Title1" data-options="iconCls:'icon-save'" style="overflow:auto;padding:10px;">   
        <h3 style="color:#0099FF;">Accordion for jQuery</h3>   
        <p>Accordion is a part of easyui framework for jQuery.     
        It lets you define your accordion component on web page more easily.</p>   
    </div>   
    <div title="Title2" data-options="iconCls:'icon-reload',selected:true" style="padding:10px;">   
        content2    
    </div>   
    <div title="Title3">   
        content3    
    </div>   
</div>
	
	</div>   
    <div id = "editor" data-options="region:'center',title:'center title'" style="padding:5px;background:#eee;">
      <table id = 'section1'>
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
      <tr>
      <td>report_id(报表的id):</td>
      <td><input  id = 'report_id_1' class = 'easyui-textbox'  /></td>
      <td><input  id = 'report_id_aggregate_1' class = 'aggregate'   /></td>
      <td><input  id = 'report_id_order_1' class = 'orderby'   /></td>
      <td><input  id = 'report_id_group_1' class = 'groupby'   /></td>
      </tr>
      <tr>
      <td>group_id(数据权限):</td><td><input  id = 'group_id_1' class = 'easyui-textbox' /></td>
            <td><input  id = 'group_id_aggregate_1' class = 'aggregate'   /></td>
      <td><input  id = 'group_id_order_1' class = 'orderby'   /></td>
      <td><input  id = 'group_id_group_1' class = 'groupby'   /></td>
      </tr>
      <tr>
      <td>period(日期):</td><td><input  id = 'period_1' class = 'easyui-combogrid' /></td>
            <td><input  id = 'period_aggregate_1' class = 'aggregate'   /></td>
      <td><input  id = 'period_order_1' class = 'orderby'   /></td>
      <td><input  id = 'period_group_1' class = 'groupby'   /></td>
      </tr>
      <tr>
      <td>num(排序字段):</td><td><input  id = 'num_1'  class = 'easyui-combogrid'/></td>
            <td><input  id = 'num_aggregate_1' class = 'aggregate'   /></td>
      <td><input  id = 'num_order_1' class = 'orderby'   /></td>
      <td><input  id = 'num_group_1' class = 'groupby'   /></td>
      </tr>
      <tr>
      <td>part_name(内容划分):</td><td><input  id = 'part_name_1' class = 'easyui-combogrid' /></td>
            <td><input  id = 'part_name_aggregate_1' class = 'aggregate'   /></td>
      <td><input  id = 'part_name_order_1' class = 'orderby'   /></td>
      <td><input  id = 'part_name_group_1' class = 'groupby'   /></td>
      </tr>
      <tr>
	  <td>level(级次):</td><td><input  id = 'level_1' class = 'easyui-textbox' /></td>
	        <td><input  id = 'level_aggregate_1' class = 'aggregate'   /></td>
      <td><input  id = 'level_order_1' class = 'orderby'   /></td>
      <td><input  id = 'level_group_1' class = 'groupby'   /></td>
      </tr>
      <tr>
	  <td>dim_date(日期维度):</td><td><input  id = 'dim_date_1' class = 'easyui-combogrid' /></td>
	        <td><input  id = 'dim_date_aggregate_1' class = 'aggregate'   /></td>
      <td><input  id = 'dim_date_order_1' class = 'orderby'   /></td>
      <td><input  id = 'dim_date_group_1' class = 'groupby'   /></td>
      </tr>
      <tr>	  
      <td>dim_com(通用维度):</td><td><input  id = 'dim_com_1' class = 'easyui-combogrid' /></td>
            <td><input  id = 'dim_com_aggregate_1' class = 'aggregate'   /></td>
      <td><input  id = 'dim_com_order_1' class = 'orderby'   /></td>
      <td><input  id = 'dim_com_group_1' class = 'groupby'   /></td>
      </tr>
      <tr>	  
	  <td>dim1(维度1):</td><td><input  id = 'dim1_1' class = 'easyui-combogrid' /></td>
	        <td><input  id = 'dim1_aggregate_1' class = 'aggregate'   /></td>
      <td><input  id = 'dim1_order_1' class = 'orderby'   /></td>
      <td><input  id = 'dim1_group_1' class = 'groupby'   /></td>
      </tr>
      <tr>
	  <td>dim2(维度2):</td><td><input  id = 'dim2_1' class = 'easyui-combogrid' /></td>
	        <td><input  id = 'dim2_aggregate_1' class = 'aggregate'   /></td>
      <td><input  id = 'dim2_order_1' class = 'orderby'   /></td>
      <td><input  id = 'dim2_group_1' class = 'groupby'   /></td>
      </tr>
      <tr>
	  <td>dim3(维度3):</td><td><input  id = 'dim3_1' class = 'easyui-combogrid' /></td>
	        <td><input  id = 'dim3_aggregate_1' class = 'aggregate'   /></td>
      <td><input  id = 'dim3_order_1' class = 'orderby'   /></td>
      <td><input  id = 'dim3_group_1' class = 'groupby'   /></td>
      </tr>
      <tr>
	  <td>dim4(维度4):</td><td><input  id = 'dim4_1' class = 'easyui-combogrid' /></td>
	        <td><input  id = 'dim4_aggregate_1' class = 'aggregate'   /></td>
      <td><input  id = 'dim4_order_1' class = 'orderby'   /></td>
      <td><input  id = 'dim4_group_1' class = 'groupby'   /></td>
      </tr>
      <tr>
	  <td>dim5(维度5):</td><td><input  id = 'dim5_1' class = 'easyui-combogrid' /></td>
	        <td><input  id = 'dim5_aggregate_1' class = 'aggregate'   /></td>
      <td><input  id = 'dim5_order_1' class = 'orderby'   /></td>
      <td><input  id = 'dim5_group_1' class = 'groupby'   /></td>
      </tr>
      <tr>
	  <td>mea_int1(整形数据值1):</td><td><input  id = 'mea_int1_1' class = 'easyui-combogrid' /></td>
	        <td><input  id = 'mea_int1_aggregate_1' class = 'aggregate'   /></td>
      <td><input  id = 'mea_int1_order_1' class = 'orderby'   /></td>
      <td><input  id = 'mea_int1_group_1' class = 'groupby'   /></td>
      </tr>
      <tr>
	  <td>mea_int2(整形数据值2):</td><td><input  id = 'mea_int2_1' class = 'easyui-combogrid' /></td>
	        <td><input  id = 'mea_int2_aggregate_1' class = 'aggregate'   /></td>
      <td><input  id = 'mea_int2_order_1' class = 'orderby'   /></td>
      <td><input  id = 'mea_int2_group_1' class = 'groupby'   /></td>
      </tr>
      <tr>
	    <td> mea_int3(整形数据值3):</td><td><input  id = 'mea_int3_1' class = 'easyui-combogrid' /></td>
	          <td><input  id = 'mea_int3_aggregate_1' class = 'aggregate'   /></td>
      <td><input  id = 'mea_int3_order_1' class = 'orderby'   /></td>
      <td><input  id = 'mea_int3_group_1' class = 'groupby'   /></td>
      </tr>
      <tr>
	  <td>mea_int4(整形数据值4):</td><td><input  id = 'mea_int4_1' class = 'easyui-combogrid' /></td>
	        <td><input  id = 'mea_int4_aggregate_1' class = 'aggregate'   /></td>
      <td><input  id = 'mea_int4_order_1' class = 'orderby'   /></td>
      <td><input  id = 'mea_int4_group_1' class = 'groupby'   /></td>
      </tr>
      <tr>
	  <td>mea_int5(整形数据值5):</td><td><input  id = 'mea_int5_1' class = 'easyui-combogrid' /></td>
	        <td><input  id = 'report_id_aggregate_1' class = 'aggregate'   /></td>
      <td><input  id = 'mea_int5_order_1' class = 'orderby'   /></td>
      <td><input  id = 'mea_int5_group_1' class = 'groupby'   /></td>
      </tr>
      <tr>
	  <td>mea_float1(浮点型数据1):</td><td><input  id = 'mea_float1_1' class = 'easyui-combogrid' /></td>
	        <td><input  id = 'mea_float1_aggregate_1' class = 'aggregate'   /></td>
      <td><input  id = 'mea_float1_order_1' class = 'orderby'   /></td>
      <td><input  id = 'mea_float1_group_1' class = 'groupby'   /></td>
      </tr>
      <tr>
	  <td>mea_float2(浮点型数据2):</td><td><input  id = 'mea_float2_1' class = 'easyui-combogrid' /></td>
	        <td><input  id = 'mea_float2_aggregate_1' class = 'aggregate'   /></td>
      <td><input  id = 'mea_float2_order_1' class = 'orderby'   /></td>
      <td><input  id = 'mea_float2_group_1' class = 'groupby'   /></td>
      </tr>
      <tr>
	  <td>mea_float3(浮点型数据3):</td><td><input  id = 'mea_float3_1' class = 'easyui-combogrid' /></td>
	        <td><input  id = 'mea_float3_aggregate_1' class = 'aggregate'   /></td>
      <td><input  id = 'mea_float3_order_1' class = 'orderby'   /></td>
      <td><input  id = 'mea_float3_group_1' class = 'groupby'   /></td>
      </tr>
      <tr>
	  <td>mea_float4(浮点型数据4):</td><td><input  id = 'mea_float4_1' class = 'easyui-combogrid' /></td>
	        <td><input  id = 'mea_float4_aggregate_1' class = 'aggregate'   /></td>
      <td><input  id = 'mea_float4_order_1' class = 'orderby'   /></td>
      <td><input  id = 'mea_float4_group_1' class = 'groupby'   /></td>
      </tr>
      <tr>
	  <td>mea_float5(浮点型数据5):</td><td><input  id = 'mea_float5_1' class = 'easyui-combogrid' /></td>
	        <td><input  id = 'mea_float5_aggregate_1' class = 'aggregate'   /></td>
      <td><input  id = 'mea_float5_order_1' class = 'orderby'   /></td>
      <td><input  id = 'mea_float5_group_1' class = 'groupby'   /></td>
      </tr>
      <tr>
	  <td>mea_float6(浮点型数据6):</td><td><input  id = 'mea_float6_1' class = 'easyui-combogrid' /></td>
	        <td><input  id = 'mea_float6_aggregate_1' class = 'aggregate'   /></td>
      <td><input  id = 'mea_float6_order_1' class = 'orderby'   /></td>
      <td><input  id = 'mea_float6_group_1' class = 'groupby'   /></td>
      </tr>
      <tr>
	  <td>mea_float7(浮点型数据7):</td><td><input  id = 'mea_float7_1' class = 'easyui-combogrid' /></td>
	        <td><input  id = 'mea_float7_aggregate_1' class = 'aggregate'   /></td>
      <td><input  id = 'mea_float7_order_1' class = 'orderby'   /></td>
      <td><input  id = 'mea_float7_group_1' class = 'groupby'   /></td>
      </tr>
      <tr>
	  <td>mea_float8(浮点型数据8):</td><td><input  id = 'mea_float8_1' class = 'easyui-combogrid' /></td>
	        <td><input  id = 'mea_float8_aggregate_1' class = 'aggregate'   /></td>
      <td><input  id = 'mea_float8_order_1' class = 'orderby'   /></td>
      <td><input  id = 'mea_float8_group_1' class = 'groupby'   /></td>
      </tr>
      <tr>
	  <td>mea_float9(浮点型数据9):</td><td><input  id = 'mea_float9_1' class = 'easyui-combogrid' /></td>
	        <td><input  id = 'report_id_aggregate_1' class = 'aggregate'   /></td>
      <td><input  id = 'mea_float9_order_1' class = 'orderby'   /></td>
      <td><input  id = 'mea_float9_group_1' class = 'groupby'   /></td>
      </tr>
      <tr>
	 <td> mea_float10(浮点型数据10):</td><td><input  id = 'mea_float10_1' class = 'easyui-combogrid' /></td>
	       <td><input  id = 'mea_float10_1' class = 'aggregate'   /></td>
      <td><input  id = 'mea_float10_order_1' class = 'orderby'   /></td>
      <td><input  id = 'mea_float10_group_1' class = 'groupby'   /></td>
      </tr>
	  
	  </table>
	</div>   
</div> 

	<div id="addSection"  class="easyui-dialog" title="" style="width:400px;height:200px;"   
        data-options="iconCls:'icon-save',resizable:true,modal:true,
			toolbar:[{
				text:'保存',
				iconCls:'icon-edit',
				handler:addSectionF
			}],
			closed:true"> 
	   语句块名称：<br/>
      <input id = "feeture" type = 'text' /> <br/>
	  语句块描述：<br/>
	  <input id = "desc" type = 'text' />
</div> 
</body>
</html>