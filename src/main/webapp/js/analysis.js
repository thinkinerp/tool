
comboboxConfig({
		id:'kpi',
		where:[
    		{key:'tableName',value:'yonghuibi.kpi_bases'},
    		{key:'idField',value:'kpi_id' },
    		{key:'nameField',value:"concat(kpi_id ,'-',kpi_name)"},
		],
		onSelectFunction:function excute_splite_sql(){
			$('#query_sql_content').datagrid({url:ctx + "/tool/getSplitSql?kpi_id=" + $('#kpi').combobox('getValue')});
		}
});
$('#query_sql_content').datagrid({
//	url:ctx + "/tool/getSplitSql?kpi_id=30",
	rownumbers: false, 
	animate: true,
	collapsible: true, 
	fitColumns: true, 
	singleSelect: true,
	remoteSort: false,
	pageSize:10,       
    pageList:[10,20,50], 
	pagination:true,//分页控件 
    columns:[[
        {title:'是否查看',field:'isCheck',width:150,checkbox:true},
        {title:'分组',field:'partName',width:150},
        {title:'SQL',field:'sQL',width:150},
	]],
	onDblClickRow:function(rowIndex, rowData){
		alert($('#user_num').val());
		//加载列信息
		loadColumns({
			gridName:'query_sql',
			data:{
				sql:rowData.sQL
			},
			nextFunction:function(){}
		});
		//加载用户的角色、数据权限
		 $.ajax({  
			 	type : "post",  
			 	url : ctx +  "/tool/getUserAuth",
			 	data:{userNum:$('#user_num').val()} ,
			 	success : function(result) {  
			 		console.log(result)
			 		$('#userAuth').html(result);
			 	},  
			 	error : function(errorMsg) {  
			 		console.log(errorMsg)
			 		$('#userAuth').html(errorMsg.responseText);
			 	}  
			 }); 	 
		
		//加载数据
		$('#query_sql').datagrid({url:ctx + "/tool/getData?sql=" + rowData.sQL + "&user_num=" + $('#user_num').val()});
	}
	
});

$('#query_sql').datagrid({
//	url: '${ctx}/app/group/getGroupList',
//	method: 'post',
	rownumbers: false, //行号
	animate: true,
	collapsible: true, //表伸缩
	fitColumns: true, //固定列
	singleSelect: true, //单选行
	remoteSort: false,//远程排序
	pageSize:10,       
    pageList:[10,20,50], 
	pagination:true,//分页控件 
    columns:[[
        {title:'SQL',field:'groupId',width:100},
        {title:'群组名称',field:'groupName',width:150},
        {title:'商行号',field:'classIds',width:150},
        {title:'门店号',field:'deptIds',width:150},
        {title:'大区',field:'districtIds',width:150},
        {title:'编辑权限',field:'c',width:150}
	]],
	data: [
			{groupId:'value11', groupName:'value12',classIds:'ddd',deptIds:'',districtIds:'',c:''},
		]
});

function loadColumns(cfg){
	var nextFunction = cfg.nextFunction;

    $.ajax({  
    	type : "post",  
    	url : ctx +  "/tool/getGridCols",
    	data:cfg.data ,
    	beforeSend:ajaxLoading,
    	success : function(result) {  
    		ajaxLoadEnd();
    		var cols = new Array();
    		cols.push(eval(result));
    		$('#'+cfg.gridName).datagrid({
    			method: 'post',  
    			//loadMsg:"载入中...",
    			animate: true,
    			collapsible: true, //表伸缩
    			singleSelect: true, //单选行
    			pageSize:10,       
    			pageList:[10,20,50], 
    			pagination:true,//分页控件 
    			rownumbers:true,
    			columns:cols
    		});
    	},  
    	error : function(errorMsg) {  
    		$.messager.alert("提示","数据请求失败!");  
    		ajaxLoadEnd();
    	}  
    }); 
    
    nextFunction();
}

function ajaxLoading(){
    $("<div class=\"datagrid-mask\"></div>").css({display:"block",width:"100%",height:$(window).height()}).appendTo("body");
    $("<div class=\"datagrid-mask-msg\"></div>").html("正在处理，请稍候。。。").appendTo("body").css({display:"block",left:($(document.body).outerWidth(true) - 190) / 2,top:($(window).height() - 45) / 2});
 }
 function ajaxLoadEnd(){
     $(".datagrid-mask").remove();
     $(".datagrid-mask-msg").remove();            
}
//create the editor
 var container = document.getElementById('jsoneditor');
 var options = {};
 var editor = new JSONEditor(container, options);

 
 
 
 
 // set json
 document.getElementById('setJSON').onclick = function () {
	 
	 $.ajax({  
		 	type : "post",  
		 	url : ctx +  "/tool/getReportJSON",
		 	data:{report_id:$('#kpi').combobox('getValue')} ,
		 	success : function(result) {  
		 	   editor.set(eval(result));
		 	},  
		 	error : function(errorMsg) {  
		 		editor.set(eval(errorMsg.responseText));
		 	}  
		 }); 	 
	 
 };

 document.getElementById('jsonbutton').onclick = function () {
	 $('#jsoneditor').show();
 };
 document.getElementById('jsonbutton1').onclick = function () {
	 $('#jsoneditor').hide();
 };
// 