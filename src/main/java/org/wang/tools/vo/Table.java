package org.wang.tools.vo;

import java.util.List;

public class Table {

	private String tableName;
	private List<Tables> columns ;
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public List<Tables> getColumns() {
		return columns;
	}
	public void setColumns(List<Tables> columns) {
		this.columns = columns;
	}
	
}
