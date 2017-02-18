package org.wang.tools.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.wang.tools.dao.DB;
import org.wang.tools.dao.Procdure;
import org.wang.tools.vo.PocedureVo;

public class ProcedureUtil {

	public static List<PocedureVo> getProcedureBodys(String procedureNameLike , Procdure p){
		
		List<PocedureVo> pvs =  p.getProcedureInfo(procedureNameLike);
		
		for (PocedureVo pocedureVo : pvs) {
			pocedureVo.setProBody(getProcedureBody(pocedureVo.getProName()));
		}
		
		return pvs ;
	}
	
	public static String getProcedureBody(String procedureName){
		DB db = new DB();
		String body = "" ;
		Connection conn = db.getConnection();
		Statement stmt = db.getStatemente(conn);
		ResultSet rs = db.getResultSet(stmt, " show create procedure " + procedureName);
		
		try {
			while(rs.next()){
				body = rs.getString("Create Procedure");
				body = body.substring(body.toLowerCase().indexOf("begin") ,body .toLowerCase().lastIndexOf("end"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.close(conn);
			db.close(stmt);
			db.close(rs);
		}
		return body ;
	}
	
}
