function scanCode( config){
	
	  wx.config({  
	      debug: false,  
	      appId: config.appId,  
	      timestamp:config.timestamp,  
	      nonceStr:config.nonceStr,  
	      signature:config.signature,  
	      jsApiList : [ 'checkJsApi', 'scanQRCode']  
	  });//end_config  

	  wx.error(function(res) {  
		  showMessage("相机调用失败，请使用微信的扫一扫功能进行重新绑定门店。"  );  
	  });  
	  
	  wx.ready(function() {  
	  	
	      wx.checkJsApi({  
	          jsApiList : ['scanQRCode'],  
	          success : function(res) {  
	          }  
	      });  
	 
	      wx.scanQRCode({  
	          needResult : 0, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，  
	          scanType : [ "qrCode", "barCode" ], // 可以指定扫二维码还是一维码，默认二者都有  
	          success : function(res) { 

	          }  
	      });  
	      
	  });//end_ready   
}
