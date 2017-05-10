/**
 * 加载下拉框选项和默认值
 */
 function loadCombobox(id , table){
	 $.ajax({ 
		 url:ctx + '/state/getSome',
		 type:'get',
		 data:{
				'ownerTable':table
		 },
		 dataType:'json',
		 success:function(rs){
			 var str = '';
			 var i = 0 ;
			 $.each(rs,function(index,item){
				 
				 if(0  == i){
					 i++;
					 str = str + item.staName;
				 }else{
					 str = str + ","+item.staName;
				 }
				 
				 if(undefined!=item.isDefault&& 1==item.isDefault){
					 if($('#'+id).is('div')){
						 $('#'+id).html(item.staName);
					 }else if($('#'+id).is('div')){
						 $('#'+id).val(item.staName);
					 }
				 }
				 
			 });
			 $('#'+id).attr("data-select",str);
		 },
		 error:function(rs){
		 }
	 });	
 }
 
 
 function delAll(id){
	 
	 var exclude = [];
	 
	 exclude.push("全部");
	 exclude.push("安装状态");
	 exclude.push("接口类型");
	 
	 if($('#'+id).is('div')){
		 

			 if( -1 != $.inArray($('#'+id).html(),exclude) ){
				 return '';
			 }else{
				return  $('#'+id).html();
			 }
		 
		 
	 }else if($('#'+id).is('input')){
		 
		 if(-1 != $.inArray($('#'+id).val(),exclude)){
				 return ''; 
		 }else{
			 return $('#'+id).val();
		 }
	 }
 }
 
 
 function contains( needle ,list){

	   $.each( list , function(index,i) {
	     if (i == needle) return true;
	   });
	   return false;
 }
 