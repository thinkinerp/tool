document.getElementsByTagName("html")[0].style.fontSize=Math.floor(document.documentElement.clientWidth*100000/750)/1000+"px";
var app ={
	listdata:'', //选择列表数据
	put:'', 	//选择后要显示的位置
	selecttype:'',	//选择类型   1只能选择   2可以选择也可以输入 输入匹配   3可以选择可以输入 输入匹配 并可选择没匹配项
	alert:function(msg,style,fun){ //消息内容    状态(1为只有确定按钮 2为是否按钮)  fun点确定后要执行的方法
		var html = [];
		html.push('<div class="g-popup" id="g-popup">');
		html.push('<div class="g-popup-main">');
		html.push('<div class="g-popup-main-title">提示</div>');
		html.push('<div class="g-popup-main-msg">'+msg+'</div>');
		html.push('<div class="g-popup-main-but">');
		if(style == 1 || style == undefined){
			html.push('<div class="on3" id="g-popupOk">确认</div>');	
		}else if(style == 2){
			html.push('<div class="on1" id="g-popupNo">否</div>');
			html.push('<div class="on2" id="g-popupOk">是</div>');
		}
		html.push('</div>');
		html.push('</div>');
		html.push('</div>');
		$("body").append(html.join(''));
		$("#g-popupOk").click(function(){
			if(fun != undefined){
				fun();
			}
			$("#g-popup").remove();		
		})
		$("#g-popupNo").click(function(){
			$("#g-popup").remove();
		})
	},
	select:function(obj,type){ //obj(有data-select的那个标签,传this 例如 app.select(this))    type: 1只能选择   2可以选择也可以输入 输入匹配   3可以选择可以输入 输入匹配 并可选择没匹配项
		app.put = obj;
		app.selecttype = type;
		app.listdata = $(app.put)[0].dataset.select;
		var content = $(app.put)[0].innerHTML;
		app.listdata = app.listdata.split(",");
        var dom = [];
        dom.push('<div class="g-select">');
        if(app.selecttype != 1){	//如果类型不等于1  显示搜索框
	        dom.push('<div class="g-select-seek">');
	        dom.push('<input type="text" oninput="app.selectSeek(this)"/>');
	        dom.push('<div></div>');//搜索按钮
	        dom.push('</div>');
        }
        dom.push('<ul class="g-select-list" id="g-select-list">');    //选项列表  选中状态给li添加class="on"
        for(var i = 0; i<app.listdata.length; i++){
        	if(app.listdata[i] == content){
       			dom.push('<li class="on">'+app.listdata[i]+'</li>');
       		}else{
       			dom.push('<li>'+app.listdata[i]+'</li>');
       		}
        }
        dom.push('</ul>');
        dom.push('</div>');
        $("body").append(dom.join(''));
        app.selectClick();     
    },
    selectSeek:function(obj){
    	var con = $(obj).val();		//输入的内容
    	var dom = [];
    	if(app.selecttype == 3){	//可以选择可以输入 输入匹配 并可选择没匹配项
    		dom.push('<li>'+con+'</li>');
    	}
    	if(con != '' && con != null && con != undefined){	//输入内容不为空		显示匹配的数据
    		for(var i = 0; i<app.listdata.length; i++){
				if(app.listdata[i].indexOf(con) >= 0 ){
				    dom.push('<li >'+app.listdata[i]+'</li>');
				}
			} 
    	}else{	//为空显示所有数据
    		for(var i = 0; i<app.listdata.length; i++){
	       		dom.push('<li>'+app.listdata[i]+'</li>');
	        }   		
    	}
    	$("#g-select-list").html(dom.join(''));
    	app.selectClick();
    },
    selectClick:function(){
    	
    	
    	
    	$("#g-select-list li").click(function(){
        	$(this).addClass('on').siblings('li').removeClass('on');
        	var ls = $(this).html();
        	setTimeout(function(){
        		$(app.put).html(ls);
        		$(app.put).removeClass('on');
        		$(".g-select").remove();
        		if(app.selected.id == $(app.put).attr("id")){
        			app.selected.onSelected();
        		}
        	},300);
        	
        	
        })
    },
    selected:{id:""
    				,onSelected:function(){
    	
    }}
    ,dateVerify:function(obj,type){	//1为不能选择大于当前日期    2为必须大于当前日期
//  	console.log(type);
//  	console.log($(obj).val());
    	var selectDate = $(obj).val();
    	selectDate = new Date(selectDate).getTime();
		var myDate = new Date();
		myDate = myDate.getTime(myDate);    
		console.log(selectDate);
		console.log(myDate);
		if(type == 1 && selectDate > myDate){
			app.alert('选择日期不能大于当前日期');
			$(obj).val('');
		}
		if(type == 2 && selectDate < myDate){
			app.alert('选择日期必须大于当前日期');
			$(obj).val('');
		}
//  	$(obj).val('');
    },
	saveDate:function(){
		alert('确定保存数据成功！');
	}
}
