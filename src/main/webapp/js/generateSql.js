/**
 * 将各个table的数据转换成sql。
 *参数说明：cfg.targeTable：目标的表
 *       tables:section的名称
 */
function generateSql(cfg){
	  var insert = 'insert into ' + cfg.targeTable + '(' ;
	  var select = 'select ' ;
	  var from   = ' from ' + cfg.targeTable  ;
	  var orderby = 'order by ' ;
	  var groupby = 'group by ' ;
	  var join   = ' left join ' ;
	  var sql = '' ;
	  var resouces_col = '';
	  var aggregate = '';
	  var orderby = '';
	  var groupby = '';
	  var targetCol = '' ;
	  var authority = 'left join ( ' +
		              'select group_id ,dept_ids,class_ids,' +
	                  'district_ids,province_ids ' +
						'from  `yonghuibi`.sys_group_resources' +
						'where group_id in (' +
						'select group_id ' +
						'from `yonghuibi`.sys_group_reports ' +
						'where report_id = 13)) t3'  ;
	  var maxdate = 'left join ( ' +
		            '     select  max(`sales_date`) as maxdate,  ' +
	                '     date_format(DATE_SUB(max(`sales_date`), ' + 
	                '     INTERVAL 1 DAY),\'%Y%m%d\') as day2 ' +
	                '     from `yonghuibi_s`.`%kpi_table%` ) t2 on 1=1 ' ;
	  var weekdate ='left join (  ' +
			  'select max(yweek) as week1,  ' +
			  'min(yweek) as week2,  ' +
			  'max(maxdate) as maxdate ' +
			  'from ( ' +
			  '    select concat(concat(left(week_num,4),\'w\'), ' +
			  '    right(week_num,2)) as yweek, ' +
			  '    max(`sales_date`) as maxdate ' +
			  '    from  `yonghuibi_s`.`kpi_catsales_daily`  ' +
			  '    group by concat(concat(left(week_num,4),\'w\'),right    (week_num,2)) ' +
			  '   order by concat(concat(left(week_num,4),\'w\'),right(    week_num,2)) DESC  ' +
			  '    limit 2 ) yweek  ' +
			  ' ) t2 on 1=1 ' ;
	  var  where = ' where 1=1 ' ;
	  
	  
	  
	  
	$.each(cfg.tables,function(index , item){

		$.each($('#' + item + " tr"),function(index , i){
			
			if("" != i.id){

			if('easyui-textbox textbox-f' == $("#" + i.id + " input[id='resouces_col']").attr("class")){
				
				resouces_col= $("#" + i.id + " #resouces_col").textbox('getValue');
			}	
			if('easyui-combobox combobox-f combo-f textbox-f' == $("#" + i.id + " input[id='resouces_col']").attr("class")){

				resouces_col= $("#" + i.id + " #resouces_col").combobox('getValue');
			}	

			aggregate = $("#" + i.id + " #aggregate").combobox('getValue');	
			orderby = $("#" + i.id + " #order").combobox('getValue');	
			groupby = $("#" + i.id + " #group").combobox('getValue');	

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
					if(''==resouces_col || null == resouces_col){
					select +=' ,null <br/>' ;
					}else{
						select +=' ,' +  resouces_col + "<br/>" ;
						
					}
				}
				console.log(select);
				insert += ',' + targetCol + "<br/>" ;
				
			}							  
			}
		
		});
		if( !!sectionAttributions & 'area'== sectionAttributions[index].authority ){
			where = where + " and (t3.`district_ids`='all' or t3.`district_ids`='AZ' or LOCATE(t1.`store_group`,t3.`district_ids`)>0 ) "
			select =  select + authority ;
		} else if(  !!sectionAttributions & 'group' == sectionAttributions[index].authority){
			where = where + " and ( t3.dept_ids='all' or t3.`dept_ids`='AZ' or LOCATE(t1.store_id,t3.`dept_ids`)>0)" ;
			select =  select + authority ;
		} else if( !!sectionAttributions & 'shop' == sectionAttributions[index].authority){
			where = where + " and (t3.`class_ids`='all' or t3.`class_ids`='AZ' or LOCATE(t1.`cat1_ID`,t3.`class_ids`)>0 )" ;
			select =  select + authority  ;
		}
		
		if( !!sectionAttributions & 'maxdate'== sectionAttributions[index].dataConfig ){
			select =  select + weekdate.replace('%kpi_table%',tables[index].tableName)  ;
		} else if(  !!sectionAttributions & 'weekdate' == sectionAttributions[index].dataConfig){
			 select =  select + weekdate.replace('%kpi_table%',tables[index].tableName)  ;	
		}
		
		sql += insert + ")" + select + where;	
		console.log(where);
		console.log(select);
	});

	return sql ;
}