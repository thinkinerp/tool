/**
 * 通用combobox配置
 * 
 * 参数说明:
 *                  id:combobox控件的id
 * 		    	    tableName:数据源table名称，例如：fresh.a_fresh_sale_daily
		    	    idField：id对应的字段， 例如：shopid
		    	    nameField：name的对应字段，例如sName
		    	    dafualtConditions：默认的过滤条件,例如,shopDfualtConditions
		    	    onSelectFunction:联动的function对象
 * 
 * 
 */

function comboboxConfig(config){

		  $('#' + config.id).combobox({
		      valueField: 'id',    
		      textField:  'textField',
		      loader:function(param,success,error){
		    	    $.ajax({  
		    	    	url: 'http://localhost:8080/tool/tool/getAnyForCombobox',  
		    	    	dataType: 'json',  
		    	    	type:'post',
		    	    	data:JSON.stringify(config.where), 
		    		    contentType : 'application/json;charset=utf-8', //设置请求头信息  
		    	    	success: function(data){  
		    	    	success(data);  
		    	    	},  
		    	    	error: function(){  
		    	    	error.apply(this, arguments);  
		    	    	}
		    	    	});
		    	    },
		    onSelect:config.onSelectFunction
		  });
		  
}