package com.intfocus.hdk.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {

	public Connection getConnection(){
		
		Connection conn = null ; 
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			conn = DriverManager.getConnection("jdbc:mysql://123.59.75.85:8880/yonghuibi?characterEncoding=utf8&amp;useUnicode=true&allowMultiQueries=true"
																						,"root"
																						,"yh123@YH123");
//			conn = DriverManager.getConnection("jdbc:mysql://172.16.149.178:3306/yonghuibi?characterEncoding=utf8&amp;useUnicode=true&allowMultiQueries=true"
//					,"root"
//					,"");
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return conn ; 
	}
	
	public Statement getStatemente(Connection conn){
		Statement stmt = null ;
		
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return stmt ;
	}
	public ResultSet getResultSet(Statement stmt , String sql ){
		ResultSet rs = null ;
				
		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rs ;
	}
	public void close(Connection conn){
		
		try {
			if(null != conn){
				conn.close();
				conn = null ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void close(Statement stmt){
		
		try {
			if(null != stmt){
				stmt.close();
				stmt = null ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void close(ResultSet rs){
		
		try {
			if(null != rs){
				rs.close();
				rs = null ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
