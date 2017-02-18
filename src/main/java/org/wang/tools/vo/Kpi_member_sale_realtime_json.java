package org.wang.tools.vo; import  java.sql.ResultSet;
import  java.sql.Connection;
import  java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import com.alibaba.fastjson.JSONObject;
 public class Kpi_member_sale_realtime_json  {
  public String to_Kpi_member_sale_realtime_json( Connection conn) throws SQLException {
   Statement st = conn.createStatement();
  ResultSet rs = st.executeQuery("select * from `yonghuibi_s`.`kpi_member_sale_realtime` as t1 where t1.store_name is not null and t1.channelname is null group by t1.store_name order by t1.store_name");
  List<Kpi_member_sale_realtime> records = new ArrayList<Kpi_member_sale_realtime>();
  Kpi_member_sale_realtime vo = null;
 while(rs.next()){
       vo =  new Kpi_member_sale_realtime();vo.setStore_id(rs.getString("store_id"));
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
vo.setWeek_num(rs.getInt("week_num"));
vo.setWeek_name(rs.getString("week_name"));
vo.setSales_date(rs.getString("sales_date"));
vo.setDt(rs.getInt("dt"));
vo.setChannelname(rs.getString("channelname"));
vo.setSalevalue(rs.getString("salevalue"));
vo.setOrderNum(rs.getInt("orderNum"));
vo.setAvg_t(rs.getString("avg_t"));
vo.setUpdatetime(rs.getString("updatetime"));
 records.add(vo);
}
JSONObject json = new JSONObject(); json.put("rows", records);json.put("total", records.size());return json.toJSONString();
}public class Kpi_member_sale_realtime{
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
  private int week_num ;
   public void setWeek_num( int week_num){
  this.week_num = week_num;
}
  public int getWeek_num(){
  return this.week_num ;
}
  private String week_name ;
   public void setWeek_name( String week_name){
  this.week_name = week_name;
}
  public String getWeek_name(){
  return this.week_name ;
}
  private String sales_date ;
   public void setSales_date( String sales_date){
  this.sales_date = sales_date;
}
  public String getSales_date(){
  return this.sales_date ;
}
  private int dt ;
   public void setDt( int dt){
  this.dt = dt;
}
  public int getDt(){
  return this.dt ;
}
  private String channelname ;
   public void setChannelname( String channelname){
  this.channelname = channelname;
}
  public String getChannelname(){
  return this.channelname ;
}
  private String salevalue ;
   public void setSalevalue( String salevalue){
  this.salevalue = salevalue;
}
  public String getSalevalue(){
  return this.salevalue ;
}
  private int orderNum ;
   public void setOrderNum( int orderNum){
  this.orderNum = orderNum;
}
  public int getOrderNum(){
  return this.orderNum ;
}
  private String avg_t ;
   public void setAvg_t( String avg_t){
  this.avg_t = avg_t;
}
  public String getAvg_t(){
  return this.avg_t ;
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