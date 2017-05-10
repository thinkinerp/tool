/**
 * 
 */

 loadCombobox("cashSystem","cash_system");
 loadCombobox("cashBrand","cash_brand");
 loadCombobox("cashPort","cash_port");
 loadCombobox("eqStyle","equipment_type");
 loadCombobox("printerPort","printer_port");

 $.ajax({ 
				 		url:ctx + '/project/getSome',
				 		type:'get',
						data:{

								},
				 		dataType:'json',
				 		success:function(rs){
							 var str = '';
							 var i = 0 ;
				  			 $.each(rs,function(index,item){
				  				 
				  				 if(0  == i){
				  					 i++;
				  					 str = str + item.proName;
				  				 }else{
				  					 str = str + ","+item.proName;
				  				 }
				  			 });
					  			$('#proName').attr("data-select",str);
				 		},
				  		error:function(rs){
				 		}
});	
 
 $.ajax({ 
	 url:ctx + '/state/getSome',
	 type:'get',
	 data:{
			'ownerTable':"install"
	 },
	 dataType:'json',
	 success:function(rs){
		 /* 	  			 alert(JSON.stringify(rs)); */
		 var str = '';
		 var i = 0 ;
		 $.each(rs,function(index,item){
			 
			 if(0  == i){
				 i++;
				 str = str + item.staName;
			 }else{
				 str = str + ","+item.staName;
			 }
		 });
		 $('#installState').attr("data-select",str);
	 },
	 error:function(rs){
	 }
 });	
 
 Window.selected = function(){

		 $.ajax({ 
	 		url:ctx + '/shops/getSome',
	 		type:'get',
			data:{
					'proId':$('#proName').html()
			},
	 		dataType:'json',
	 		success:function(rs){
			 var str = '';
			 var i = 0 ;
			 var has = new Array();
  			 $.each(rs,function(index,item){
  				 if(-1 == $.inArray(item.shopName, has)){
  					 has.push(item.shopName);
	  				 if(0  == i){
	  					 i++;
	  					 str = str + item.shopName;
	  				 }else{
	  					 str = str + ","+item.shopName;
	  				 }
  				 }
  			 });
	 			$("#shopName").attr("data-select",str);
	 			
	 		},
	  		error:function(rs){
	 		}
	  });	
 }
Window.shopSelected = function(){
	 $.ajax({ 
	 		url:ctx + '/shops/getSome',
	 		type:'get',
			data:{
					'shopName':$('#shopName').html()
			},
	 		dataType:'json',
	 		success:function(rs){
			 var str = '';
			 var i = 0 ;
			 $.each(rs,function(index,item){
				 $("#shopState").html(item.shopMerStation);
				 $('#shopPosition').html(item.shopPosition);
				 $('#shopType').html(item.shopType);
				 $('#shopSecType').html(item.shopSecType);
				 
				$('#shopCode').html(item.shopId);
			 });
	 			
	 		},
	  		error:function(rs){
	 		}
	  });	
	 //如果这个门店已经调研了，与此门店相关的打印机和收银机信息都已经存在，所以在这样情况下
	 //需要将收银机和打印机的信息加载过来。
	 $.ajax({ 
		 url:ctx + '/survey/getSome',
		 type:'get',
		 data:{
			 'shopName':$('#shopName').html(),
			 'proName':$('#proName').html()
		 },
		 dataType:'json',
		 success:function(rs){
			if(  null != rs  && undefined != rs  && rs.length > 0){
				loadPrinterAndCasher();
			}else{
				$.each(rs,function(index,item){
					surId = item.surId
				});
			}
		 },
		 error:function(rs){
		 }
	 });	
}
function loadPrinterAndCasher(surId){
	 $.ajax({ 
		 url:ctx + '/printer/getSome',
		 type:'post',
		 data:{
			 'surId':surId,
		 },
		 dataType:'json',
		 success:function(rs){
			if(  null != rs  && undefined != rs  && rs.length > 0){
					$.each(rs,function(index,item){
						$('#priId').html(item.printerId);	
						$('#priBrand').html(item.printerBrand);	
						$('#prinPort').html(item.printerPort);	
					});
			} 
		 },
		 error:function(rs){
		 }
	 });	
	 $.ajax({ 
		 url:ctx + '/cash/getSome',
		 type:'post',
		 data:{
			 'surId':surId,
		 },
		 dataType:'json',
		 success:function(rs){
			 if(  null != rs  && undefined != rs  && rs.length > 0){
				 $.each(rs,function(index,item){
					 $('#cashId').html(item.cashId);	
					 $('#cashSystem').html(item.cashSystem);	
					 $('#cashBrand').html(item.cashBrand);	
					 $('#cashPort').html(item.cashPort);	
					 
					 if(undefined == item.printerDriver || "" == item.printerDriver){
						 $('#t').attr("class","off");	
						 $('#f').attr("class","on");	
					 }else{
						 $('#t').attr("class","on");	
						 $('#f').attr("class","off");	
						 
					 }
					 
				 });
			 } 
		 },
		 error:function(rs){
		 }
	 });	
}


function setCashidOnInstall(){
	$('#cashCode').html($('#cashId').val());
}


function setpriIdOnInstall(){
	$('#printCode').html($('#priId').val());
	
}

function seteqIdOnInstall(){
	$('#equipmentCode').html($('#eqId').val());
	
}

var submit = function(){
//	app.saveDate();
	// 在没有调研的情况下要进行一下动作
	//保存收银机
	
	// 保存打印机
	
	// 采集点
	if('{}' != allThing && "" !=allThing){
		
		$.ajax({
			url:ctx + '/install/modify',
			type:'post',
			data:{'install.installId':$("#installCode").val()
					,'install.id':allObjs.install.id
				     ,'install.installStation':$('#installStation').html()
					,'install.proId':allObjs.install.proId
					,'install.shopId':$('#shopCode').html()
					,'install.cashId':$('#cashCode').html()
					,'install.printerId':$('#printCode').html()
					,'install.eqId':$('#equipmentCode').html()
					,'install.installData':$('#installData').html()
					,'install.installTime':$('#installTime').val()
					//,'install.installUser':''
					,'install.installNetwork':$('#installNetworkHard').attr('class') == 'on'?"硬件":"软件"
					,'install.installRemote':$('#installRemote').val()
					//,'install.installEndtime':
					//,'install.install.createdAt':''
					//,'install.updatedAt':''
					//,'install.installRemarks':''
						//收银机
					,"cash.id":allObjs.cash.id
					,'cash.cashId':$("#cashId").val()
					,'cash.cashBrand':$("#cashBrand").html()
					//,'cashRegister':''
					,'cash.cashSystem':$("#cashSystem").html()
					,'cash.cashPort':$("#cashPort").html()
					,'cash.printerDriver':($("#t").attr("class") == "off"?"否":"是")
					,'cash.surId':surId
					,'cash.installId':$("#installCode").val()
						//打印机
					,'printer.id':allObjs.printer.id
					,'printer.printerId':$('#priId').val()
					,'printer.printerBrand':$('#priBrand').val()
//					,'printerModel':''
					,'printer.printerPort':$('#printerPort').html()
					,'printer.surId':surId
					,'printer.installId':$("#installCode").val()
//					,'createdAt':''
//					,'updatedAt':''
					//采集点
					,'equipment.id':allObjs.equipment.id
					,'equipment.eqId':$("#eqId").val()
					,'equipment.eqType':$('#eqTypeHard').attr('class') == "on"?"硬件":"软件"
					,'equipment.eqStyle':$("#eqStyle").html()
//					,'hardwareId':
					,'equipment.softwareVersion':$('#softwareVersion').val()
					,'equipment.proId':$('#proName').html()
					,'equipment.shopId':$('#shopCode').html()
//					,'remark1':''
//					,'remark2':''
//					,'createdAt':''
//					,'updatedAt':''
					},
			success:function(result){
				
				location.href = ctx + "/installList.jsp";
			},
			error:function(result){
				console.log(result)
			}
		})	;

		
	}else{

		
        $.ajaxFileUpload(
            {
                url: ctx + '/install/submit', //用于文件上传的服务器端请求地址
                secureuri: false, //是否需要安全协议，一般设置为false
                fileElementId: 'fileImg', //文件上传域的ID
                dataType: 'json', //返回值类型 一般设置为json
        		data:{'install.installId':$("#installCode").val()
//					,'install.id':allObjs.install.id
				     ,'install.installStation':$('#installStation').html()
//					,'install.proId':allObjs.install.proId
					,'install.shopId':$('#shopCode').html()
					,'install.cashId':$('#cashCode').html()
					,'install.printerId':$('#printCode').html()
					,'install.eqId':$('#equipmentCode').html()
					,'install.installData':$('#installData').html()
					,'install.installTime':$('#installTime').val()
					//,'install.installUser':''
					,'install.installNetwork':$('#installNetworkHard').attr('class') == 'on'?"硬件":"软件"
					,'install.installRemote':$('#installRemote').val()
					//,'install.installEndtime':
					//,'install.install.createdAt':''
					//,'install.updatedAt':''
					//,'install.installRemarks':''
						//收银机
//					,"cash.id":allObjs.cash.id
					,'cash.cashId':$("#cashId").val()
					,'cash.cashBrand':$("#cashBrand").html()
					//,'cashRegister':''
					,'cash.cashSystem':$("#cashSystem").html()
					,'cash.cashPort':$("#cashPort").html()
					,'cash.printerDriver':($("#t").attr("class") == "off"?"否":"是")
					,'cash.surId':surId
					,'cash.installId':$("#installCode").val()
						//打印机
//					,'printer.id':allObjs.printer.id
					,'printer.printerId':$('#priId').val()
					,'printer.printerBrand':$('#priBrand').val()
//					,'printerModel':''
					,'printer.printerPort':$('#printerPort').html()
					,'printer.surId':surId
					,'printer.installId':$("#installCode").val()
//					,'createdAt':''
//					,'updatedAt':''
					//采集点
//					,'equipment.id':allObjs.equipment.id
					,'equipment.eqId':$("#eqId").val()
					,'equipment.eqType':$('#eqTypeHard').attr('class') == "on"?"硬件":"软件"
					,'equipment.eqStyle':$("#eqStyle").html()
//					,'hardwareId':
					,'equipment.softwareVersion':$('#softwareVersion').val()
					,'equipment.proId':$('#proName').html()
					,'equipment.shopId':$('#shopCode').html()
//					,'remark1':''
//					,'remark2':''
//					,'createdAt':''
//					,'updatedAt':''
   				},
                success: function (data, status)  //服务器成功响应处理函数
                {
                	location.href = ctx + "/installList.jsp";
                },
                error: function (data, status, e)//服务器响应失败处理函数
                {
                    //alert(e);
                }
            }
        );
//	$.ajax({
//		url:ctx + '/install/submit',
//        contentType:false, //必须false才会避开jQuery对 formdata 的默认处理 , XMLHttpRequest会对 formdata 进行正确的处理
//        processData: false, //必须false才会自动加上正确的Content-Type 
//		type:'post',
//		data:{'install.installId':$("#installCode").val()
//		     ,'install.installStation':$('#installStation').html()
//			,'install.proId':$('#proName').html()
//			,'install.shopId':$('#shopCode').html()
//			,'install.cashId':$('#cashCode').html()
//			,'install.printerId':$('#printCode').html()
//			,'install.eqId':$('#equipmentCode').html()
//			,'install.installData':$('#installData').html()
//			,'install.installTime':$('#installTime').val()
//			//,'install.installUser':''
//			,'install.installNetwork':$('#installNetworkHard').attr('class') == 'on'?"硬件":"软件"
//			,'install.installRemote':$('#installRemote').val()
//			//,'install.installEndtime':
//			//,'install.install.createdAt':''
//			//,'install.updatedAt':''
//			//,'install.installRemarks':''
//			,"pictures":$("#fileImg").get(0).files[0]
//				//收银机
//			,'cash.cashId':$("#cashId").val()
//			,'cash.cashBrand':$("#cashBrand").html()
//			//,'cashRegister':''
//			,'cash.cashSystem':$("#cashSystem").html()
//			,'cash.cashPort':$("#cashPort").html()
//			,'cash.printerDriver':$("#t").attr("class") == "on"?"是":"否"
//			,'cash.surId':surId
//			,'cash.installId':$("#installCode").val()
//				//打印机
//			,'printer.printerId':$('#priId').val()
//			,'printer.printerBrand':$('#priBrand').val()
////			,'printerModel':''
//			,'printer.printerPort':$('#prinPort').html()
//			,'printer.surId':surId
//			,'printer.installId':$("#installCode").val()
////			,'createdAt':''
////			,'updatedAt':''
//			//采集点
//			,'equipment.eqId':$("#eqId").val()
//			,'equipment.eqType':$('#eqTypeHard').attr('class') == "on"?"硬件":"软件"
//			,'equipment.eqStyle':$("#eqStyle").html()
////			,'hardwareId':
//			,'equipment.softwareVersion':$('#softwareVersion').val()
//			,'equipment.proId':$('#proName').html()
//			,'equipment.shopId':$('#shopCode').html()
////			,'remark1':''
////			,'remark2':''
////			,'createdAt':''
////			,'updatedAt':''
//				},
//		success:function(result){
//		},
//		error:function(result){
//			console.log(result)
//		}
//	})	;
	}
	
}

var onSetupState = function(){
	$('#installStation').html($('#installState').html());
}



