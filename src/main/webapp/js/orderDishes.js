/**
 * 
 */
	var i = 0 ;
	var menu = new Array();
	var b = false ;
	var dish_name ='' ;
(function(){


	$.ajax({
		method : 'post' ,
		url :   'http://localhost:8080/tool/tool/selectMenu' ,
		dataType:'json',
		success:function(data){
             menu = data.concat();	
			console.log(demo2(menu));
		}
	});
    console.log(menu);
   //
}
)()

function demo(data){
	
	document.getElementById('main').innerHTML = data[i].field + " " + data[i].type ;
	if(i < data.length){
		i = i + 1 ;
	} else {
		i = 0 ;
	}
	if(b) { return ;}
	setTimeout("demo(menu)", 100)
}
function demo2(data){
	
	var r = Math.random().toFixed(4);
	
	var index = 0 ;
	
	if(0<= r && r<=data[0].comment) { 
		document.getElementById('main').innerHTML = data[0].field + " " + data[0].type ;
		
		updataWeighting(  data[0].field );
		
		return data[0].field + " " + data[0].type;
		}
	for(;index <= data.length - 1 ;index ++ ){
		
		if(r > data[index].comment && r<= data[index+1].comment){
			document.getElementById('main').innerHTML = data[index + 1].field + " " + data[index + 1].type ;
			
			updataWeighting( data[index + 1].field );
			
			return ;
		}
	}
	
}


function updataWeighting(dish_name){
	
	$.ajax({
		method:'post',
		url:'http://localhost:8080/tool/tool/updataWeighting',
		data:{dish_name:dish_name},
		dataType:'json',
		success:function(data){
			console.log(data);
		}
	});
	
}

