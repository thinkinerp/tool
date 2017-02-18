package org.wang.tools.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.wang.tools.vo.Order;
import org.wang.tools.vo.Tables;

public interface toolMapper {
	
	List<Tables> getTableInfo(String tableName);
	
	List<Tables> getTableColumn(String tableName);
	
	List<Tables> getDateInfo();
	
	List<Tables> getTablesName();

	List<Tables> getKpiBases();
	
	List<Map<String, String>> selectAnyForComboBox( @Param("tableName")String tableName,
			@Param("idField")String idField , 
			@Param("nameField")String nameField , 
			@Param("where")String where );

	List<Order> selectMenu();

	void updateDishWeighting(@Param("dishName")String dishName);
	
	void excute(@Param("list") List<String> list  );
	
}
