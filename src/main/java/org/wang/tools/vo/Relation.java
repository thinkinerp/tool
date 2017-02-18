package org.wang.tools.vo;

public class Relation {

	private String kpiInfo ;
	private String pre_kpi_tables;
	private String pre_report_data_tables;
	private String procedureName ;
	public String getProcedureName() {
		return procedureName;
	}
	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}
	public String getKpiInfo() {
		return kpiInfo;
	}
	public void setKpiInfo(String kpiInfo) {
		this.kpiInfo = kpiInfo;
	}
	public String getPre_kpi_tables() {
		return pre_kpi_tables;
	}
	public void setPre_kpi_tables(String pre_kpi_tables) {
		this.pre_kpi_tables = pre_kpi_tables;
	}
	public String getPre_report_data_tables() {
		return pre_report_data_tables;
	}
	public void setPre_report_data_tables(String pre_report_data_tables) {
		this.pre_report_data_tables = pre_report_data_tables;
	}
	
}
