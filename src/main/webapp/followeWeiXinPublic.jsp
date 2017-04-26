<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0">
<%@include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${ctx}/js/jweixin-1.0.0.js"></script>
<title>关注公众号</title>
</head>
<body>


<button id="scanQRCode" >扫描二维码</button>



<script  type="text/javascript">  
    wx.config({  
        debug: false,  
        appId: '${appId}',  
        timestamp:'${timestamp}',  
        nonceStr:'${nonceStr}',  
        signature:'${signature}',  
        jsApiList : [ 'checkJsApi', 'scanQRCode' ]  
    });//end_config  
  
    wx.error(function(res) {  
        alert("出错了：" + res.errMsg);  
    });  
  
    wx.ready(function() {  
        wx.checkJsApi({  
            jsApiList : ['scanQRCode'],  
            success : function(res) {  
            }  
        });  
  
        //扫描二维码  
        document.querySelector('#scanQRCode').onclick = function() {  
            wx.scanQRCode({  
                needResult : 1, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，  
                scanType : [ "qrCode", "barCode" ], // 可以指定扫二维码还是一维码，默认二者都有  
                success : function(res) { 
                    var result = res.resultStr; // 当needResult 为 1 时，扫码返回的结果  
                    
                    alert("扫描成功::扫描码=" + result);  
                    
                    window.location.href = result;
                    
                }  
            });  
        };//end_document_scanQRCode  
          
    });//end_ready  
</script>  
</body>
</html>