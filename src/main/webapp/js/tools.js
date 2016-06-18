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
/*	$('#pg').propertygrid({    
	    showGroup: true,    
	    scrollbarSize: 0,
	    title:'属性',
        columns: [[
                   { field: 'name', title: 'Name', width: 100, resizable: true },
                   { field: 'value', title: 'Value', width: 100, resizable: false }
           ]]
	}); 
	var rows = [
	            { "name": "权限选项", "value": "", "group": "权限配置", "field": "authority", 
	            	     "editor": { 
	            	    	  "type": 'combobox', 
	            	    	  "options": { "valueField": 'value', 
	            	    		           "textField": 'name', 
	            	    		           "required": true,
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
			            	    				}]}} },
	            { "name": "日期维度的关联", "value": "", "group": "日期", "field": "date", "editor": "text" },
	        ];
	 $('#pg').propertygrid('loadData', rows);*/
	var input = '' ;
	var input = "<input id = '_authority'   />" ;
	$('#pg').append(input);
	
	$('#_authority').combobox({
		 "valueField": 'value', 
         "textField": 'name', 
         "required": true,
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
/*	$.ajax({
		  url: ctx + "/tool/getColums",
		  cache: false,
		  method:'post',
		  dataType:'json',
		  data:{tableName:'kpi_bonus_monthly'},
		  success: function(data){
			  //option.data = data
		    console.log(option.data);
		  }
		});	
	

*/
	
    $('#btn').bind('click', function(){    
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
	console.log(record);
	$.each(sectionAttributions,function(index,item){
		console.log(item);
		if(current == item.sectionId){
			sectionAttributions[index].authority = record.value ;
		    is = 1 ;
		}
	});
	if( 0 == is ){
		sectionAttributions.push({sectionId:current,authority:record.value,dataConfig:''});
	}
	console.log(sectionAttributions);
}

function gen(){
	
	var ts = new Array();
	$.each(tables,function(index , item){
		ts.push('section' +  item.id);
	});
    console.log(ts);
    
	var a = generateSql({
		targeTable:tables[0].tableName,
		tables:ts
	});
	
	
	$('#showSql').dialog('options').content = a;
	console.log(a);
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
	if("" == $('#part_name_i').val()){
		$.messager.alert('提示','ID号不能为空');
		return ;
	};
	if("" == $('#desc_i').val()){
		$.messager.alert('提示','ID号不能为空');
		return ;
	};
	
	tables.push({id: ++index ,part_name:$('#part_name_i').val(),desc:$('#desc_i').val(),tableName:"kpi_bonus_monthly"});
	
	current = index ;
	$('#editor').append(Template({id:index}));

//  初始化组件
	$.parser.parse('#section' + index);
	$.each($('#section'+index+' .easyui-combobox'),function(index , item){
        $(item).combobox({
			valueField: 'field',
			textField: 'comment',
			method:'post',
			url:ctx + "/tool/getColums?tableName=kpi_bonus_monthly",
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
	"<td><input  id = 'resouces_col' class = 'easyui-textbox'  /></td>" +
	"<td><input  id = 'aggregate' class = 'aggregate'   /></td>" +
	"<td><input  id = 'order' class = 'orderby'   /></td>" +
	"<td><input  id = 'group' class = 'groupby'   /></td>" +
	"</tr>" +
	"<tr id = 'group_id_"+cfg.id+"'>" +
	"<td>group_id(数据权限):</td><td><input  id = 'resouces_col' class = 'easyui-textbox' /></td>" +
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
	"<tr id = 'mea_int3_"+cfg.id+"'>" +
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
	 var current = index + 1 ;
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
