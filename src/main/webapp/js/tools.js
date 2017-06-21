/**
 * 
 *开发步骤：1.先能一个table生成sql,并使用dialog展示。加上权限
 *       2.动态的添加对应关系，在添加的时候可以指定tableName，然后在批量的生成sql。
 *       3.实现拖到指定字段的功能。
 *       4.
 */
var tables = new Array();
var index  = 0 ;
var current = 0 ;
var div = '' ;
var sectionAttributions = new Array();
$(function(){  

/*	$('#dataSource').datagrid({    
	    url: ctx +  '/tool/getTables',
	    method:'post',
	    pagination:true ,
	    rownumbers:true , 
	    columns:[[    
	        {field:'field',title:'表名称',width:100},    
	    ]],
	    onDblClickRow:function(rowIndex, rowData){
	    	$('#source').val(rowData.field);
	    	$.each(tables,function(index , item ){
	    		
	    		if(current == item.sectionId){
	    			sectionAttributions[index].tableName = item
	    		}
	    		
	    	});
	    	
	    }
	}); */
	
	var input = '' ;
	var input = "<input id = '_authority'   /><br/>" ;
	$('#pg').append(input);
	
//	
//	$('#dateConfig').combobox({
//		"valueField": 'value', 
//        "textField": 'name', 
//        "required": true,
//        onSelect:setSectionAttr1,
//        "data":[{
//     	       name: '最大日期',
//			   value: 'maxdate'
//		       },{
//					name: '周日期',
//					value: 'weekdate'
//				}]
	
//	});
	
	$('#_authority').combobox({
		 "valueField": 'value', 
         "textField": 'name', 
         multiple: true , 
         "onSelect":setSectionAttr,
         "data":[{
      	       name: '大区',
			   value: 'area'
		       },{
					name: '门店',
					value: 'shop'
				},{
					name: '商行',
					value: 'group'
				}]
	});
	
	$('#pg').append( "<input id = 'target'  type = 'text' /><br/>" );
	$('#pg').append( "<input id = 'source' type = 'text'   /><br/>" );
	
	
	

    $('#btn').bind('click', function(){   
    	 
		    	if(!$('#sourceConfig').val()){
		    		$.messager.alert('提示','源数据的配置是必须的');
		    		return ;
		    	}
		    	if(!$('#targetConfig').val()){
		    		$.messager.alert('提示','目标数据的配置是必须的');
		    		return ;
		    	}
		
		    	comboboxConfig({
		    		id:              'source_i' ,
		    		where:[
		    		{key:'tableName',value:'information_schema.columns'},
		    		{key:'idField',value:'table_name' },
		    		{key:'nameField',value:      'table_name'},
		    		{key:'w.TABLE_SCHEMA',value: $('#sourceConfig').val()},
		    	    ]
		    	});	
		    	comboboxConfig({
		    		id:              'target_i' ,
		    		where:[
		    		{key:'tableName',value:'information_schema.columns'},
		    		{key:'idField',value:'table_name' },
		    		{key:'nameField',value:      'table_name'},
		    		{key:'w.TABLE_SCHEMA',value:$('#targetConfig').val()},
		    	    ]
		    	});		

    	
        $('#addSection').dialog('open');
  });   
	$.each($('#section1 .easyui-combobox'),function(index , item){
                  $(item).combobox({
          			valueField: 'field',
        			textField: 'comment',
        			method:'post',
        			url:ctx + "/tool/getColums?tableName=kpi_bonus_monthly",
        	/*		formatter: function(row){
        				var opts = $(this).combobox('options');
        				return  row[opts.textField] + row[opts.valueField] ;
        			}*/

        			});		
	});
	
	$.each($('#section1 .aggregate'),function(index , item){
        $(item).combobox({
			 valueField:'id',
			 textField:'text',
			 data:[
			       {id:"sum",text:'sum'},
			       {id:'avg',text:'avg'},
			       ]

			});		
	});	   
    	$.each($('#section1 .orderby'),function(index , item){
            $(item).combobox({
    			 valueField:'id',
    			 textField:'text',
    			 data:[
    			       {id:1,text:'排序元素'},
    			       {id:0,text:'常规'},
    			       ]

    			});	 
            
    	});	 
       $.each($('#section1 .groupby'),function(index , item){
            	$(item).combobox({
            		valueField:'id',
            		textField:'text',
            		data:[
            		      {id:1,text:'聚合维度'},
            		      {id:0,text:'常规'},
            		      ]
            	
            	});	  
   	});	 

});  

function setSectionAttr(record){
	
	var is = 0 ;
	$.each(sectionAttributions,function(index,item){
		console.log(item);
		if(current == item.sectionId){
			sectionAttributions[index].authority = $('#_authority').combobox('getValues');
		    is = 1 ;
		}
	});
	
	console.log(record);
	
	if( 0 == is ){
		sectionAttributions.push({sectionId:current,authority:$('#_authority').combobox('getValues'),dataConfig:'',dateConfig:''});
	}
	console.log(sectionAttributions);
}
function setSectionAttr1(record){
	
	var is = 0 ;
	console.log(record);
	$.each(sectionAttributions,function(index,item){
		console.log(item);
		if(current == item.sectionId){
			sectionAttributions[index].dateConfig = record.value ;
		    is = 1 ;
		}
	});
	if( 0 == is ){
		sectionAttributions.push({sectionId:current,dateConfig:record.value,dataConfig:'',authority:''});
	}
	console.log(sectionAttributions);
}
function gen(){

	console.log(tables);
    
	var a = generateSql(tables);

	$('#showSql').dialog({
		content:'' + a + ''
	});
	//window.open(ctx + '/sql_context.jsp?content=' + a.replace(new RegExp("<br/>", 'g'),'\n'));
	//console.log(a.replace(new RegExp("<br/>", 'g'),'\n'));
	$('#showSql').dialog('open');
}


function fun1(e,source){ 
alert("fun1") ;
} 
function fun2(e,source){ 
alert("fun2") ;
} 

function addSectionF(){

	if("" == $('#id_i').val()){
		$.messager.alert('提示','报表ID号不能为空');
		return ;
	};
	
	if("" == $('#targetTable_i').val()){
		$.messager.alert('提示','目标数据表不能为空');
		return ;
	};
	
	
	if("" == $('#part_name_i').val()){
		$.messager.alert('提示','语句块标识不能为空');
		return ;
	};
	if("" == $('#desc_i').val()){
		$.messager.alert('提示','描述不能为空');
		return ;
	};
	if("" == $('#source_i').combobox('getValue')){
		$.messager.alert('提示','源数据不能为空');
		return ;
	};
	if("" == $('#target_i').combobox('getValue')){
		$.messager.alert('提示','目标表不能为空');
		return ;
	};	
	
	
	tables.push({ id: ++index ,
		                 part_name:$('#part_name_i').val(),
		                 report_id : $('#id_i').val(),
		                 desc:$('#desc_i').val(),
		                 tableName:$('#source_i').combobox('getValue'),
	                     targetTable:$('#target_i').combobox('getValue')	        
	});
	
	current = index ;
	$('#editor').append(Template({id:index}));
    //$('#report_id_'+index + ' #resouces_col').combobox( 'setValue' , $('#id_i').val() );
   console.log($('#report_id_'+index + ' input[id="resouces_col"]').val($('#id_i').val()));
	//  初始化组件
	$.parser.parse('#section' + index);
	$.each($('#section'+index+' .easyui-combobox'),function(index , item){
        $(item).combobox({
			valueField: 'field',
			textField: 'comment',
			multiple:true,
			method:'post',
			url:ctx + "/tool/getColums?tableName=" + $('#source_i').combobox('getValue'),
			});		
});

		$.each($('#section'+index+' .aggregate'),function(index , item){
		$(item).combobox({
			 valueField:'id',
			 textField:'text',
			 data:[
			       {id:"sum",text:'sum'},
			       {id:'avg',text:'avg'},
			       ]
		
			});		
		});	   
		$.each($('#section'+index+' .orderby'),function(index , item){
		  $(item).combobox({
				 valueField:'id',
				 textField:'text',
				 data:[
				       {id:1,text:'排序元素'},
				       {id:0,text:'常规'},
				       ]
		
				});	 
		  
		});	 
		$.each($('#section'+index+' .groupby'),function(index , item){
		  	$(item).combobox({
		  		valueField:'id',
		  		textField:'text',
		  		data:[
		  		      {id:1,text:'聚合维度'},
		  		      {id:0,text:'常规'},
		  		      ]
		  	
		  	});	  
		});
// 添加索引
		$('#aa').accordion('add', {
			title: $('#id_i').val(),
			content: $('#desc_i').val(),
			selected: false
		});
		$('#addSection').dialog('close');
}
function Template(cfg){
	
	var template =  "" +
	"<table id = 'section"+cfg.id+"' height = '1000px'>" +
	"<tr style='background:rgb(214,234,294);'>" +
	"<td  >" +
	"              目标字段" +
	"</td>" +
	"<td  >" +
	"              源字段" +
	"</td>" +
	"<td  >" +
	"              聚合类型" +
	"<td>" +
	"           排序" +
	"</td>" +
	"<td>" +
	"           聚合字段" +
	"</td>" +
	"<tr id = 'report_id_"+cfg.id+"'>" +
	"<td>report_id(报表的id):</td>" +
	"<td><input  id = 'resouces_col' class = 'easyui-textbox'   /></td>" +
	"<td><input  id = 'aggregate' class = 'aggregate'   /></td>" +
	"<td><input  id = 'order' class = 'orderby'   /></td>" +
	"<td><input  id = 'group' class = 'groupby'   /></td>" +
	"</tr>" +
	"<tr id = 'group_id_"+cfg.id+"'>" +
	"<td>group_id(数据权限):</td><td><input  id = 'resouces_col' class = 'easyui-textbox' value = 't3.group_id'/></td>" +
	"<td><input  id = 'aggregate' class = 'aggregate' /></td>" +
	"<td><input  id = 'order' class = 'orderby'   /></td>" +
	"<td><input  id = 'group' class = 'groupby'   /></td>" +
	"</tr >" +
	"<tr id = 'period_"+cfg.id+"'>" +
	"<td id = 'period'>period(日期):</td><td><input  id = 'resouces_col' class = 'easyui-combobox' /></td>" +
	"<td><input  id = 'aggregate' class = 'aggregate'   /></td>" +
	"<td><input  id = 'order' class = 'orderby'   /></td>" +
	"<td><input  id = 'group' class = 'groupby'   /></td>" +
	"</tr>" +
	"<tr id = 'num_"+cfg.id+"'>" +
	"<td>num(排序字段):</td><td><input  id = 'resouces_col'  class = 'easyui-combobox'/></td>" +
	"<td><input  id = 'aggregate' class = 'aggregate'   /></td>" +
	"<td><input  id = 'order' class = 'orderby'   /></td>" +
	"<td><input  id = 'group' class = 'groupby'   /></td>" +
	"</tr>" +
	"<tr id = 'part_name_"+cfg.id+"'>" +
	"<td>part_name(内容划分):</td><td><input  id = 'resouces_col' class = 'easyui-textbox' /></td>" +
	"<td><input  id = 'aggregate' class = 'aggregate'   /></td>" +
	"<td><input  id = 'order' class = 'orderby'   /></td>" +
	"<td><input  id = 'group' class = 'groupby'   /></td>" +
	"</tr>" +
	"<tr id = 'level_"+cfg.id+"'>" +
	"<td>level(级次):</td><td><input  id = 'resouces_col' class = 'easyui-textbox' /></td>" +
	"<td><input  id = 'aggregate' class = 'aggregate'   /></td>" +
	"<td><input  id = 'order' class = 'orderby'   /></td>" +
	"<td><input  id = 'group' class = 'groupby'   /></td>" +
	"</tr >" +
	"<tr id = 'dim_date_"+cfg.id+"'>" +
	"<td>dim_date(日期维度):</td><td><input  id = 'resouces_col' class = 'easyui-combobox' /></td>" +
	"<td><input  id = 'aggregate' class = 'aggregate'   /></td>" +
	"<td><input  id = 'order' class = 'orderby'   /></td>" +
	"<td><input  id = 'group' class = 'groupby'   /></td>" +
	"</tr>" +
	"<tr id = 'dim_com_"+cfg.id+"'>	  " +
	"<td>dim_com(通用维度):</td><td><input  id = 'resouces_col' class = 'easyui-combobox' /></td>" +
	"<td><input  id = 'aggregate' class = 'aggregate'   /></td>" +
	"<td><input  id = 'order' class = 'orderby'   /></td>" +
	"<td><input  id = 'group' class = 'groupby'   /></td>" +
	"</tr>" +
	"<tr id = 'dim1_"+cfg.id+"'>	  " +
	"<td>dim1(维度1):</td><td><input  id = 'resouces_col' class = 'easyui-combobox' /></td>" +
	"<td><input  id = 'aggregate' class = 'aggregate'   /></td>" +
	"<td><input  id = 'order' class = 'orderby'   /></td>" +
	"<td><input  id = 'group' class = 'groupby'   /></td>" +
	"</tr>" +
	"<tr id = 'dim2_"+cfg.id+"'>" +
	"<td>dim2(维度2):</td><td><input  id = 'resouces_col' class = 'easyui-combobox' /></td>" +
	"<td><input  id = 'aggregate' class = 'aggregate'   /></td>" +
	"<td><input  id = 'order' class = 'orderby'   /></td>" +
	"<td><input  id = 'group' class = 'groupby'   /></td>" +
	"</tr>" +
	"<tr id = 'dim3_"+cfg.id+"'>" +
	"<td>dim3(维度3):</td><td><input  id = 'resouces_col' class = 'easyui-combobox' /></td>" +
	"<td><input  id = 'aggregate' class = 'aggregate'   /></td>" +
	"<td><input  id = 'order' class = 'orderby'   /></td>" +
	"<td><input  id = 'group' class = 'groupby'   /></td>" +
	"</tr>" +
	"<tr id = 'dim4_"+cfg.id+"'>" +
	"<td>dim4(维度4):</td><td><input  id = 'resouces_col' class = 'easyui-combobox' /></td>" +
	"<td><input  id = 'aggregate' class = 'aggregate'   /></td>" +
	"<td><input  id = 'order' class = 'orderby'   /></td>" +
	"<td><input  id = 'group' class = 'groupby'   /></td>" +
	"</tr>" +
	"<tr id = 'dim5_"+cfg.id+"'>" +
	"<td>dim5(维度5):</td><td><input  id = 'resouces_col' class = 'easyui-combobox' /></td>" +
	"<td><input  id = 'aggregate' class = 'aggregate'   /></td>" +
	"<td><input  id = 'order' class = 'orderby'   /></td>" +
	"<td><input  id = 'group' class = 'groupby'   /></td>" +
	"</tr>" +
	"<tr id = 'mea_int1_"+cfg.id+"'>" +
	"<td>mea_int1(整形数据值1):</td><td><input  id = 'resouces_col' class = 'easyui-combobox' /></td>" +
	"<td><input  id = 'aggregate' class = 'aggregate'   /></td>" +
	"<td><input  id = 'order' class = 'orderby'   /></td>" +
	"<td><input  id = 'group' class = 'groupby'   /></td>" +
	"</tr>" +
	"<tr id = 'mea_int2_"+cfg.id+"'>" +
	"<td>mea_int2(整形数据值2):</td><td><input  id = 'resouces_col' class = 'easyui-combobox' /></td>" +
	"<td><input  id = 'aggregate' class = 'aggregate'   /></td>" +
	"<td><input  id = 'order' class = 'orderby'   /></td>" +
	"<td><input  id = 'group' class = 'groupby'   /></td>" +
	"</tr>" +
	"<tr id = 'mea_int3_"+cfg.id+"'>" +
	"<td> mea_int3(整形数据值3):</td><td><input  id = 'resouces_col' class = 'easyui-combobox' /></td>" +
	"<td><input  id = 'aggregate' class = 'aggregate'   /></td>" +
	"<td><input  id = 'order' class = 'orderby'   /></td>" +
	"<td><input  id = 'group' class = 'groupby'   /></td>" +
	"</tr>" +
	"<tr id = 'mea_int4_"+cfg.id+"'>" +
	"<td >mea_int4(整形数据值4):</td><td><input  id = 'resouces_col' class = 'easyui-combobox' /></td>" +
	"<td><input  id = 'aggregate' class = 'aggregate'   /></td>" +
	"<td><input  id = 'order' class = 'orderby'   /></td>" +
	"<td><input  id = 'group' class = 'groupby'   /></td>" +
	"</tr>" +
	"<tr id = 'mea_int5_"+cfg.id+"'>" +
	"<td>mea_int5(整形数据值5):</td><td><input  id = 'resouces_col' class = 'easyui-combobox' /></td>" +
	"<td><input  id = 'aggregate' class = 'aggregate'   /></td>" +
	"<td><input  id = 'order' class = 'orderby'   /></td>" +
	"<td><input  id = 'group' class = 'groupby'   /></td>" +
	"</tr>" +
	"<tr id = 'mea_float1_"+cfg.id+"'>" +
	"<td>mea_float1(浮点型数据1):</td><td><input  id = 'resouces_col' class = 'easyui-combobox' /></td>" +
	"<td><input  id = 'aggregate' class = 'aggregate'   /></td>" +
	"<td><input  id = 'order' class = 'orderby'   /></td>" +
	"<td><input  id = 'group' class = 'groupby'   /></td>" +
	"</tr>" +
	"<tr id = 'mea_float2_"+cfg.id+"'>" +
	"<td>mea_float2(浮点型数据2):</td><td><input  id = 'resouces_col' class = 'easyui-combobox' /></td>" +
	"<td><input  id = 'aggregate' class = 'aggregate'   /></td>" +
	"<td><input  id = 'order' class = 'orderby'   /></td>" +
	"<td><input  id = 'group' class = 'groupby'   /></td>" +
	"</tr>" +
	"<tr id = 'mea_float3_"+cfg.id+"'>" +
	"<td>mea_float3(浮点型数据3):</td><td><input  id = 'resouces_col' class = 'easyui-combobox' /></td>" +
	"<td><input  id = 'aggregate' class = 'aggregate'   /></td>" +
	"<td><input  id = 'order' class = 'orderby'   /></td>" +
	"<td><input  id = 'group' class = 'groupby'   /></td>" +
	"</tr>" +
	"<tr id = 'mea_float4_"+cfg.id+"'>" +
	"<td>mea_float4(浮点型数据4):</td><td><input  id = 'resouces_col' class = 'easyui-combobox' /></td>" +
	"<td><input  id = 'aggregate' class = 'aggregate'   /></td>" +
	"<td><input  id = 'order' class = 'orderby'   /></td>" +
	"<td><input  id = 'group' class = 'groupby'   /></td>" +
	"</tr>" +
	"<tr id = 'mea_float5_"+cfg.id+"'>" +
	"<td>mea_float5(浮点型数据5):</td><td><input  id = 'resouces_col' class = 'easyui-combobox' /></td>" +
	"<td><input  id = 'aggregate' class = 'aggregate'   /></td>" +
	"<td><input  id = 'order' class = 'orderby'   /></td>" +
	"<td><input  id = 'group' class = 'groupby'   /></td>" +
	"</tr>" +
	"<tr id = 'mea_float6_"+cfg.id+"'>" +
	"<td>mea_float6(浮点型数据6):</td><td><input  id = 'resouces_col' class = 'easyui-combobox' /></td>" +
	"<td><input  id = 'aggregate' class = 'aggregate'   /></td>" +
	"<td><input  id = 'order' class = 'orderby'   /></td>" +
	"<td><input  id = 'group' class = 'groupby'   /></td>" +
	"</tr>" +
	"<tr id = 'mea_float7_"+cfg.id+"'>" +
	"<td>mea_float7(浮点型数据7):</td><td><input  id = 'resouces_col' class = 'easyui-combobox' /></td>" +
	"<td><input  id = 'aggregate' class = 'aggregate'   /></td>" +
	"<td><input  id = 'order' class = 'orderby'   /></td>" +
	"<td><input  id = 'group' class = 'groupby'   /></td>" +
	"</tr>" +
	"<tr id = 'mea_float8_"+cfg.id+"'>" +
	"<td>mea_float8(浮点型数据8):</td><td><input  id = 'resouces_col' class = 'easyui-combobox' /></td>" +
	"<td><input  id = 'aggregate' class = 'aggregate'   /></td>" +
	"<td><input  id = 'order' class = 'orderby'   /></td>" +
	"<td><input  id = 'group' class = 'groupby'   /></td>" +
	"</tr>" +
	"<tr id = 'mea_float9_"+cfg.id+"'>" +
	"<td>mea_float9(浮点型数据9):</td><td><input  id = 'resouces_col' class = 'easyui-combobox' /></td>" +
	"<td><input  id = 'aggregate' class = 'aggregate'   /></td>" +
	"<td><input  id = 'order' class = 'orderby'   /></td>" +
	"<td><input  id = 'group' class = 'groupby'   /></td>" +
	"</tr>" +
	"<tr id = 'mea_float10_"+cfg.id+"'>" +
	"<td> mea_float10(浮点型数据10):</td><td><input  id = 'resouces_col' class = 'easyui-combobox' /></td>" +
	"<td><input  id = 'aggregate' class = 'aggregate'   /></td>" +
	"<td><input  id = 'order' class = 'orderby'   /></td>" +
	"<td><input  id = 'group' class = 'groupby'   /></td>" +
	"</tr>" +
	"" +
	"</table>" ;
   return template ;
}



function changLocation(title,index){

	 var p = $('#aa').accordion('getSelected');
	 var index = $('#aa').accordion('getPanelIndex', p);
	 current = index + 1 ;
	 var authority = '' ;
     $.each(sectionAttributions,function(i , item){
    	 if(current == item.sectionId){
    		 authority = item.authority
    	 }
     });
/*     var ed = $('#pg').propertygrid('getEditor',{index:0 ,field:'value'});
     console.log(ed);
     $(ed.target).combobox('setValue', authority);
*/
	 document.getElementById('editor').scrollTop = 1000*(index );
}