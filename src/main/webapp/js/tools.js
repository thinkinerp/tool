/**
 * 
 *开发步骤：1.先能一个table生成sql,并使用dialog展示。加上权限
 *       2.动态的添加对应关系，在添加的时候可以指定tableName，然后在批量的生成sql。
 *       3.实现拖到指定字段的功能。
 *       4.
 */
var tables = new Array();

var div = '' ;
$(function(){  
	

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
	
    $('#btn').bind('click', function(){    
          $('#addSection').dialog('open');
    });   
*/
	tables.push("section1");
	
	$.each($('#section1 .easyui-combogrid'),function(index , item){
                  $(item).combogrid({
          			valueField: 'field',
        			textField: 'comment',
        			method:'post',
        			url:ctx + "/tool/getColums?tableName=kpi_bonus_monthly",
        	/*		formatter: function(row){
        				var opts = $(this).combobox('options');
        				return  row[opts.textField] + row[opts.valueField] ;
        			}*/
        		    columns:[[    
        		              {field:'field',title:'字段名称',width:60},    
        		              {field:'comment',title:'说明',width:100},     
        		          ]]
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


function gen(){
	
	var a = generateSql({
		targeTable:'report_data_021_banner',
		tables:['section1']
	});
	console.log(a);
}


function fun1(e,source){ 
alert("fun1") ;
} 
function fun2(e,source){ 
alert("fun2") ;
} 

function addSectionF(){

}

function changLocation(title,index){
	 console.log(title);
	 console.log(index);
	 console.log($('#aa').accordion('getSelected'));
}
