/**
 * 将各个table的数据转换成sql。
 *参数说明：cfg.targeTable：目标的表
 *
 */
function generateSql(cfg){
	  var insert = 'insert into ' + cfg.targetable + '(' ;
	  var select = 'select ' ;
	  var orderby = 'order by ' ;
	  var groupby = 'group by ' ;
	  var join   = ' left join ' ;
	  var sql = '' ;
	$.each(cfg.tables,function(index , item){
		  
		var rows = $('#' + item).edatagrid('options').data ;
		
		$.each(rows,function(index , item){
			if(0==index){
				if(0==item.aggregate){
					select += "sum(" + item.SourceCol + ")" + "\n" ;
				} else if(1==item.aggregate){
					select += "avg(" + item.SourceCol + ")" + "\n" ;
					
				}else{
					select += item.SourceCol + "\n" ;
					
				}
				insert += item.targetCol + + "\n" ;
			} else if(index > 0 ){

				if(''!=item.aggregate&&'sum'==item.aggregate){
					select +=',' +  "sum(" + item.SourceCol + ")" + "\n" ;
				} else if(''!=item.aggregate&&'avg'==item.aggregate){
					select += ',' + "avg(" + item.SourceCol + ")" + "\n" ;
					
				}else{
					if(''==item.SourceCol || null == item.SourceCol){
					select +=' ,null \n' ;
					}else{
						select +=' ,' +  item.SourceCol + "\n" ;
						
					}
				}
				insert += ',' + item.targetCol + "\n" ;
				
			}
		});
              sql += insert + ")" + select ;		
	});
	return sql ;
}