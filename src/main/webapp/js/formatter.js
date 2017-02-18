/**
 * 按照不同的字段来为指定不同的editor 
 */
	var option = {
			valueField: 'field',
			textField: 'comment',
			method:'post',
			url:ctx + "/tool/getColums?tableName=kpi_bonus_monthly",
			formatter: function(row){
				var opts = $(this).combobox('options');
				return  row[opts.textField] + row[opts.valueField] ;
			}
			};
function GenerateEditor (value,row,index){
	console.log(index);
	if(row.targetCol =='report_id'){
		$(this)[0].editor ={type:'textbox'} ;
		console.log($(this)[0]);
	}

	
	return ;
	if(value =='group_id'){
		row.editor ={type:'textbox'} ;		
	}
	if(value =='period'){
		row.editor ={type:'combobox',options:option} ;	
	}
    if(value =='num'){
    	row.editor ={type:'combobox',options:option} ;	
    }
	if(value ==part_name){
		row.editor ={type:'textbox'} ;	
	}
	if(value ==level){
		row.editor ={type:'textbox'} ;	
	} 
    if(value ==dim_date){
    	row.editor ={type:'combobox',options:option} ;	
    } 
	if(value ==dim_com){
		
		row.editor ={type:'combobox',options:option} ;	
	} 
	if(value ==dim1 ){
		row.editor ={type:'combobox',options:option} ;	
		
	}
	if(value ==dim2){
		row.editor ={type:'combobox',options:option} ;	
		
	} 
	if(value ==dim3){
		row.editor ={type:'combobox',options:option} ;	
		
	}
	if(value ==dim4) {
		
		row.editor ={type:'combobox',options:option} ;	
	}
	if(value ==dim5){
		
		row.editor ={type:'combobox',options:option} ;	
	} 
	if(value ==mea_int1){
		row.editor ={type:'combobox',options:option} ;	
		
	} 
	if(value ==mea_int2){
		row.editor ={type:'combobox',options:option} ;	
		
	}
	row.editor ={type:'combobox',options:option} ;	
	if(value ==mea_int3){
		
	}
	if(value ==mea_int4){
		
		row.editor ={type:'combobox',options:option} ;	
	}
	if(value ==mea_int5){
		
		row.editor ={type:'combobox',options:option} ;	
	} 
	if(value ==mea_float1){
		row.editor ={type:'combobox',options:option} ;	
		
	} 
	if(value ==mea_float2){
		row.editor ={type:'combobox',options:option} ;	
		
	} 
	if(value ==mea_float3){
		
		row.editor ={type:'combobox',options:option} ;	
	}
	if(value ==mea_float4){
		
		row.editor ={type:'combobox',options:option} ;	
	}  
	if(value ==mea_float5){
		row.editor ={type:'combobox',options:option} ;	
		
	} 
	if(value ==mea_float6){
		row.editor ={type:'combobox',options:option} ;	
		
	} 
	if(value ==mea_float7){
		row.editor ={type:'combobox',options:option} ;	
		
	} 
	if(value ==mea_float8){
		
		row.editor ={type:'combobox',options:option} ;	
	}
	if(value ==mea_float9){
		
		row.editor ={type:'combobox',options:option} ;	
	}
	if(value ==mea_float10){
		
		row.editor ={type:'combobox',options:option} ;	
	} 
	if(value ==load_time){
		
		row.editor ={type:'combobox',options:option} ;	
	}
	if(value ==updated_at){
		
		row.editor ={type:'combobox',options:option} ;	
	}
}
