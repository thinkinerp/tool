/**
 * 将各个table的数据转换成sql。
 *参数说明：cfg.targeTable：目标的表
 *       tables:section的名称
 */
function generateSql(cfg){

	var sql = '' ;
	  var resouces_col = '';
	  var aggregate = '';

	  var targetCol = '' ;
	  var authority = 'left join ( ' +
		              'select group_id ,dept_ids,class_ids,'  + "<br/>" +
	                  'district_ids,province_ids ' +
						'from  `yonghuibi`.sys_group_resources'  + "<br/>" +
						'where group_id in (' +
						'select group_id ' +
						'from `yonghuibi`.sys_group_reports '  + "<br/>"+
						'where report_id = 13)) t3 on 1=1 '  ;
	  var maxdate = 'left join ( ' +
		            '     select  max(`sales_date`) as maxdate,  '  + "<br/>"+
	                '     date_format(DATE_SUB(max(`sales_date`), '  + "<br/>"+ 
	                '     INTERVAL 1 DAY),\'%Y%m%d\') as day2 ' + "<br/>" +
	                '     from `%kpi.table%` ) t2 on 1=1 ' ;
	  var weekdate ='left join (  '  + "<br/>"+
			  'select max(yweek) as week1,  '  + "<br/>"+
			  'min(yweek) as week2,  ' + "<br/>" +
			  'max(maxdate) as maxdate ' + "<br/>" +
			  'from ( ' + "<br/>" +
			  '    select concat(concat(left(week_num,4),\'w\'), ' + "<br/>" +
			  '    right(week_num,2)) as yweek, ' + "<br/>" +
			  '    max(`sales_date`) as maxdate '  + "<br/>"+
			  '    from  `%kpi.table%`  ' + "<br/>" +
			  '    group by concat(concat(left(week_num,4),\'w\'),right    (week_num,2)) ' + "<br/>" +
			  '   order by concat(concat(left(week_num,4),\'w\'),right(    week_num,2)) DESC  ' + "<br/>" +
			  '    limit 2 ) yweek  ' + "<br/>" +
			  ' ) t2 on 1=1 ' ;
	  var  where = ' where 1=1 ' ;
	  
	  
	  
	  
	$.each(cfg,function(index , item){
		
		
		  var insert = 'insert into ' + item.targetTable + '(' ;
		  var select = 'select ' ;
		  var from   = ' from ' + item.tableName + ' '   ;
		  var orderby = 'order by ' ;
		  var groupby = 'group by ' ;
		  var join   = ' left join ' ;
		$.each($('#' + "section" +  item.id+ " tr"),function(index , i){
			
			
			if("" != i.id){

			if('easyui-textbox textbox-f' == $("#" + i.id + " input[id='resouces_col']").attr("class")){
				
				resouces_col= $("#" + i.id + " #resouces_col").textbox('getValue');
			}	
			if('easyui-combobox combobox-f combo-f textbox-f' == $("#" + i.id + " input[id='resouces_col']").attr("class")){

				resouces_col= $("#" + i.id + " #resouces_col").combobox('getValues');
			}	

			aggregate = $("#" + i.id + " #aggregate").combobox('getValues');	
			orderby = $("#" + i.id + " #order").combobox('getValues');	
			groupby = $("#" + i.id + " #group").combobox('getValues');	
			
			
			console.log(resouces_col);
				
		    if((resouces_col instanceof Array) && resouces_col.length > 1){
		    	var tmp = 'concat(' ;
		    	var x ;
				for(x in resouces_col ){
				    	
					tmp = tmp + resouces_col[x] + " , "; 
				}
				
				tmp = tmp + " '' )" ;
				resouces_col = tmp ;
		    } else if( (resouces_col instanceof Array) && resouces_col.length == 1){
		    	
		    	
		    	tmp = '' ;
		    	tmp = tmp + resouces_col[0] ;
		    	
		    	resouces_col = tmp ;
		    } else if((resouces_col instanceof Array) && resouces_col.length == 0){
		    	resouces_col = '' ;
		    }		
				
				
				
			targetCol = i.id ;
			targetCol = targetCol.slice(0,targetCol.length-2);	

			if(1==index){
				if("sum"==aggregate){
					select += "sum(" + resouces_col + ")" + "<br/>" ;
				} else if("avg"==aggregate){
					select += "avg(" + resouces_col + ")" + "<br/>" ;
				}else{
					select += resouces_col + "<br/>" ;
				}
				insert += targetCol  + "<br/>" ;
			} else if(index > 1 ){

				if(''!=aggregate&&'sum'==aggregate){
					select +=',' +  "sum(" + resouces_col + ")" + "<br/>" ;
				} else if(''!=aggregate&&'avg'==aggregate){
					select += ',' + "avg(" + resouces_col + ")" + "<br/>" ;
					
				}else{
					if(!resouces_col){
					select +=' ,null <br/>' ;
					}else{
						select +=' ,' +  resouces_col + "<br/>" ;
						
					}
				}
				insert += ',' + targetCol + "<br/>" ;
			}							  
			}
		
		});
		
		select = select + from ;
		
		if( !!sectionAttributions & 'area'== sectionAttributions[index].authority ){
			where = where  + " and (t3.`district_ids`='all' or t3.`district_ids`='AZ' or LOCATE(t1.`store_group`,t3.`district_ids`)>0 ) "
			select =  select + authority ;
		} else if(  !!sectionAttributions & 'group' == sectionAttributions[index].authority){
			where = where + " and ( t3.dept_ids='all' or t3.`dept_ids`='AZ' or LOCATE(t1.store_id,t3.`dept_ids`)>0)" ;
			select =  select + authority ;
		} else if( !!sectionAttributions & 'shop' == sectionAttributions[index].authority){
			where = where + " and (t3.`class_ids`='all' or t3.`class_ids`='AZ' or LOCATE(t1.`cat1_ID`,t3.`class_ids`)>0 )" ;
			select =  select + authority  ;
		}
        
		console.log(sectionAttributions);
		
		if( !!sectionAttributions & 'maxdate'== sectionAttributions[index].dateConfig ){
			select =  select + weekdate.replace('%kpi.table%',item.tableName)  ;
		} else if(  !!sectionAttributions & 'weekdate' == sectionAttributions[index].dateConfig){
			 select =  select + weekdate.replace('%kpi.table%',item.tableName)  ;	
		}
		sql += insert + ")" + select + where;	

	});

	return sql ;
}