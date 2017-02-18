package org.wang.tools.vo; import  java.sql.ResultSet;
import  java.sql.Connection;
import  java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import com.alibaba.fastjson.JSONObject;
 public class Kpi_budget_group_profit_json  {
  public String to_Kpi_budget_group_profit_json( Connection conn) throws SQLException {
   Statement st = conn.createStatement();
  ResultSet rs = st.executeQuery("select * from `yonghuibi_s`.kpi_budget_group_profit t1 where 1=1 and  t1.store_name is null");
  List<Kpi_budget_group_profit> records = new ArrayList<Kpi_budget_group_profit>();
  Kpi_budget_group_profit vo = null;
 while(rs.next()){
       vo =  new Kpi_budget_group_profit();vo.setStore_id(rs.getString("store_id"));
vo.setStore_name(rs.getString("store_name"));
vo.setStore_flag(rs.getString("store_flag"));
vo.setStore_class(rs.getString("store_class"));
vo.setStore_group(rs.getString("store_group"));
vo.setCat1_ID(rs.getInt("cat1_ID"));
vo.setCat2_ID(rs.getInt("cat2_ID"));
vo.setCat3_ID(rs.getInt("cat3_ID"));
vo.setCat1_name(rs.getString("cat1_name"));
vo.setCat2_name(rs.getString("cat2_name"));
vo.setCat3_name(rs.getString("cat3_name"));
vo.setYear(rs.getInt("year"));
vo.setYearmonth(rs.getInt("yearmonth"));
vo.setSales_date(rs.getString("sales_date"));
vo.setProfit_MTD(rs.getString("profit_MTD"));
vo.setBackprofit(rs.getString("backprofit"));
vo.setComplexprofit(rs.getString("complexprofit"));
vo.setBudget(rs.getString("budget"));
vo.setBackprofit_budget(rs.getString("backprofit_budget"));
vo.setComplex_budget(rs.getString("complex_budget"));
vo.setLastmonthtotal(rs.getString("lastmonthtotal"));
vo.setLastmonthtotal_back(rs.getString("lastmonthtotal_back"));
vo.setLastmonthtotal_complex(rs.getString("lastmonthtotal_complex"));
vo.setComp_rate(rs.getString("comp_rate"));
vo.setLead_rate(rs.getString("lead_rate"));
vo.setComp_rate_back(rs.getString("comp_rate_back"));
vo.setLead_rate_back(rs.getString("lead_rate_back"));
vo.setComp_rate_complex(rs.getString("comp_rate_complex"));
vo.setLead_rate_complex(rs.getString("lead_rate_complex"));
vo.setUpdatetime(rs.getString("updatetime"));
 records.add(vo);
}
JSONObject json = new JSONObject(); json.put("rows", records);json.put("total", records.size());return json.toJSONString();
}public class Kpi_budget_group_profit{
  private String store_id ;
   public void setStore_id( String store_id){
  this.store_id = store_id;
}
  public String getStore_id(){
  return this.store_id ;
}
  private String store_name ;
   public void setStore_name( String store_name){
  this.store_name = store_name;
}
  public String getStore_name(){
  return this.store_name ;
}
  private String store_flag ;
   public void setStore_flag( String store_flag){
  this.store_flag = store_flag;
}
  public String getStore_flag(){
  return this.store_flag ;
}
  private String store_class ;
   public void setStore_class( String store_class){
  this.store_class = store_class;
}
  public String getStore_class(){
  return this.store_class ;
}
  private String store_group ;
   public void setStore_group( String store_group){
  this.store_group = store_group;
}
  public String getStore_group(){
  return this.store_group ;
}
  private int cat1_ID ;
   public void setCat1_ID( int cat1_ID){
  this.cat1_ID = cat1_ID;
}
  public int getCat1_ID(){
  return this.cat1_ID ;
}
  private int cat2_ID ;
   public void setCat2_ID( int cat2_ID){
  this.cat2_ID = cat2_ID;
}
  public int getCat2_ID(){
  return this.cat2_ID ;
}
  private int cat3_ID ;
   public void setCat3_ID( int cat3_ID){
  this.cat3_ID = cat3_ID;
}
  public int getCat3_ID(){
  return this.cat3_ID ;
}
  private String cat1_name ;
   public void setCat1_name( String cat1_name){
  this.cat1_name = cat1_name;
}
  public String getCat1_name(){
  return this.cat1_name ;
}
  private String cat2_name ;
   public void setCat2_name( String cat2_name){
  this.cat2_name = cat2_name;
}
  public String getCat2_name(){
  return this.cat2_name ;
}
  private String cat3_name ;
   public void setCat3_name( String cat3_name){
  this.cat3_name = cat3_name;
}
  public String getCat3_name(){
  return this.cat3_name ;
}
  private int year ;
   public void setYear( int year){
  this.year = year;
}
  public int getYear(){
  return this.year ;
}
  private int yearmonth ;
   public void setYearmonth( int yearmonth){
  this.yearmonth = yearmonth;
}
  public int getYearmonth(){
  return this.yearmonth ;
}
  private String sales_date ;
   public void setSales_date( String sales_date){
  this.sales_date = sales_date;
}
  public String getSales_date(){
  return this.sales_date ;
}
  private String profit_MTD ;
   public void setProfit_MTD( String profit_MTD){
  this.profit_MTD = profit_MTD;
}
  public String getProfit_MTD(){
  return this.profit_MTD ;
}
  private String backprofit ;
   public void setBackprofit( String backprofit){
  this.backprofit = backprofit;
}
  public String getBackprofit(){
  return this.backprofit ;
}
  private String complexprofit ;
   public void setComplexprofit( String complexprofit){
  this.complexprofit = complexprofit;
}
  public String getComplexprofit(){
  return this.complexprofit ;
}
  private String budget ;
   public void setBudget( String budget){
  this.budget = budget;
}
  public String getBudget(){
  return this.budget ;
}
  private String backprofit_budget ;
   public void setBackprofit_budget( String backprofit_budget){
  this.backprofit_budget = backprofit_budget;
}
  public String getBackprofit_budget(){
  return this.backprofit_budget ;
}
  private String complex_budget ;
   public void setComplex_budget( String complex_budget){
  this.complex_budget = complex_budget;
}
  public String getComplex_budget(){
  return this.complex_budget ;
}
  private String lastmonthtotal ;
   public void setLastmonthtotal( String lastmonthtotal){
  this.lastmonthtotal = lastmonthtotal;
}
  public String getLastmonthtotal(){
  return this.lastmonthtotal ;
}
  private String lastmonthtotal_back ;
   public void setLastmonthtotal_back( String lastmonthtotal_back){
  this.lastmonthtotal_back = lastmonthtotal_back;
}
  public String getLastmonthtotal_back(){
  return this.lastmonthtotal_back ;
}
  private String lastmonthtotal_complex ;
   public void setLastmonthtotal_complex( String lastmonthtotal_complex){
  this.lastmonthtotal_complex = lastmonthtotal_complex;
}
  public String getLastmonthtotal_complex(){
  return this.lastmonthtotal_complex ;
}
  private String comp_rate ;
   public void setComp_rate( String comp_rate){
  this.comp_rate = comp_rate;
}
  public String getComp_rate(){
  return this.comp_rate ;
}
  private String lead_rate ;
   public void setLead_rate( String lead_rate){
  this.lead_rate = lead_rate;
}
  public String getLead_rate(){
  return this.lead_rate ;
}
  private String comp_rate_back ;
   public void setComp_rate_back( String comp_rate_back){
  this.comp_rate_back = comp_rate_back;
}
  public String getComp_rate_back(){
  return this.comp_rate_back ;
}
  private String lead_rate_back ;
   public void setLead_rate_back( String lead_rate_back){
  this.lead_rate_back = lead_rate_back;
}
  public String getLead_rate_back(){
  return this.lead_rate_back ;
}
  private String comp_rate_complex ;
   public void setComp_rate_complex( String comp_rate_complex){
  this.comp_rate_complex = comp_rate_complex;
}
  public String getComp_rate_complex(){
  return this.comp_rate_complex ;
}
  private String lead_rate_complex ;
   public void setLead_rate_complex( String lead_rate_complex){
  this.lead_rate_complex = lead_rate_complex;
}
  public String getLead_rate_complex(){
  return this.lead_rate_complex ;
}
  private String updatetime ;
   public void setUpdatetime( String updatetime){
  this.updatetime = updatetime;
}
  public String getUpdatetime(){
  return this.updatetime ;
}
}

}