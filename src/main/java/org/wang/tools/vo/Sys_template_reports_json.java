package org.wang.tools.vo; import  java.sql.ResultSet;
import  java.sql.Connection;
import  java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import com.alibaba.fastjson.JSONObject;
 public class Sys_template_reports_json  {
  public String to_Sys_template_reports_json( Connection conn) throws SQLException {
   Statement st = conn.createStatement();
  ResultSet rs = st.executeQuery("select * from sys_template_reports");
  List<Sys_template_reports> records = new ArrayList<Sys_template_reports>();
  Sys_template_reports vo = null;
 while(rs.next()){
       vo =  new Sys_template_reports();vo.setId(rs.getInt("id"));
vo.setReport_id(rs.getInt("report_id"));
vo.setTemplate_id(rs.getInt("template_id"));
vo.setContent(rs.getString("content"));
vo.setCreated_at(rs.getString("created_at"));
vo.setUpdated_at(rs.getString("updated_at"));
vo.setTitle(rs.getString("title"));
vo.setRemark(rs.getString("remark"));
vo.setAudio_content(rs.getString("audio_content"));
vo.setHas_audio(rs.getInt("has_audio"));
 records.add(vo);
}
return JSONObject.toJSONString(records);
}public class Sys_template_reports{
  private int id ;
   public void setId( int id){
  this.id = id;
}
  public int getId(){
  return this.id ;
}
  private int report_id ;
   public void setReport_id( int report_id){
  this.report_id = report_id;
}
  public int getReport_id(){
  return this.report_id ;
}
  private int template_id ;
   public void setTemplate_id( int template_id){
  this.template_id = template_id;
}
  public int getTemplate_id(){
  return this.template_id ;
}
  private String content ;
   public void setContent( String content){
  this.content = content;
}
  public String getContent(){
  return this.content ;
}
  private String created_at ;
   public void setCreated_at( String created_at){
  this.created_at = created_at;
}
  public String getCreated_at(){
  return this.created_at ;
}
  private String updated_at ;
   public void setUpdated_at( String updated_at){
  this.updated_at = updated_at;
}
  public String getUpdated_at(){
  return this.updated_at ;
}
  private String title ;
   public void setTitle( String title){
  this.title = title;
}
  public String getTitle(){
  return this.title ;
}
  private String remark ;
   public void setRemark( String remark){
  this.remark = remark;
}
  public String getRemark(){
  return this.remark ;
}
  private String audio_content ;
   public void setAudio_content( String audio_content){
  this.audio_content = audio_content;
}
  public String getAudio_content(){
  return this.audio_content ;
}
  private int has_audio ;
   public void setHas_audio( int has_audio){
  this.has_audio = has_audio;
}
  public int getHas_audio(){
  return this.has_audio ;
}
}

}