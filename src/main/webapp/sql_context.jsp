<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css" media="screen">
    #editor { 
        position: absolute;
        top: 0;
        right: 0;
        bottom: 0;
        left: 0;
    }
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${ctx}/js/ace.js"></script>
<title>sql_content</title>
</head>
<body>
<script type="text/javascript" >
    var ctx = "${ctx}";
</script>
<div id="editor"><%=  request.getParameter("content")%>
</div>
<script>
    var editor = ace.edit("editor");
    editor.setTheme("ace/theme/mysql");
    editor.getSession().setMode("ace/mode/javascript");
</script>
</body>
</html>