package org.ksmart02.fruitmall.admin.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.ksmart02.fruitmall.util.ConnectionPool;

public class AdminDao {

	Connection conn;
	PreparedStatement pstmt;
	String sql;
	ResultSet rs;
	
	public void levelModifyForm() throws Exception {
		
		conn 	= ConnectionPool.getConnection();
		sql = "select ";
		pstmt = conn.prepareStatement(sql);
		pstmt.executeQuery();
	}

}
