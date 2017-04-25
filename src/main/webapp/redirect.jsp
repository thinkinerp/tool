<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0">
<%@include file="/common/taglibs.jsp"%>
<%@include file="/common/css.jsp" %> 
    <link href="${ctx}/js/bootstrap3/css/bootstrap-dialog.min.css" rel="stylesheet" type="text/css">
    <script src="${ctx}/js/bootstrap3/bootstrap-dialog.min.js"></script>
<script type="text/javascript" >
    var ctx = "${ctx}";	
</script>
<title>数据通-${storeName}</title>
</head>
<body>
<script type="text/javascript">


BootstrapDialog.show({
title: '无效账号汇总',
message: '任务名称为**' + Window.objResult.jobName +'**,共有' + Window.objResult.unCheckedCount+'个',
buttons: [{
    label: '导出 Excel',
    action: function(dialog) {
    	window.location.href =ctx + '/message/returnUncheckedContactExcel?jobName=sadfasdfa';
    }
},{
		label: '导出 SQL 语句',
		action: function(dialog) {
        	$.ajax({
        		url:ctx + '/message/returnUncheckedContactSQL',
        		type:'get',
				data:{
					jobName:'sadfasdfa'
				},
				success:function(result){
					dialog.setMessage(result);
				},
				error:function(result){
					
				}
        	});
			
		}      	
    }
]
});


</script>
</body>
</html>