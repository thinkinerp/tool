package org.wang.tools.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.wang.tools.vo.Tables;

public interface toolMapper {
	List<Tables> getTableInfo(String tableName);
	List<Tables> getTablesName();
	List<Map<String, String>> selectAnyForComboBox( @Param("tableName")String tableName,
			@Param("idField")String idField , 
			@Param("nameField")String nameField , 
			@Param("where")String where );

	
	
}
