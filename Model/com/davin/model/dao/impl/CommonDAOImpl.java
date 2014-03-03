package com.davin.model.dao.impl;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.davin.common.util.CheckNullEmpty;
import com.davin.common.util.JdbcDriverProperties;
import com.davin.model.dao.CommonDAO;
import com.davin.model.entity.Departure;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

@Component
public class CommonDAOImpl implements CommonDAO{
	
	@Override
	public List<Departure> listDeparture(String sidx, String sord, String search){
		String sql = "SELECT depart2.NAME, depart2.CODE, depart1.NAME AS COUNTRY "+
						"FROM DEPART depart1 "+
						"INNER JOIN DEPART depart2 "+
						"ON depart1.ID = depart2.COUNTRY "+
						"WHERE LOWER(depart2.NAME) LIKE '%"+search+"%' "+
						"ORDER BY "+sidx+" "+sord;
		
		List<Departure> list = new ArrayList<Departure>();
		Connection conn = null;
		PreparedStatement pstmt = null;
 
		try {
			
			conn = (Connection) DriverManager.getConnection(JdbcDriverProperties.DB_URL, JdbcDriverProperties.USER, JdbcDriverProperties.PASS);
			pstmt = (PreparedStatement) conn.prepareStatement(sql); 
			ResultSet rs = pstmt.executeQuery();
			
			if(CheckNullEmpty.isNotNullOrEmpty(rs)){
				while(rs.next()) {
					Departure d = new Departure();
					d.setName(rs.getString("NAME"));
					d.setCode(rs.getString("CODE"));
					d.setCountry(rs.getString("COUNTRY"));
					list.add(d);
				}
			}
			
			return list;
 
		} catch (SQLException e) {
			throw new RuntimeException(e);
 
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	
	@Override
	public List<Departure> listDepartureLimitOffset(int limit, int offset, String sidx, String sord, String search){
		String sql = "SELECT depart2.NAME, depart2.CODE, depart1.NAME AS COUNTRY "+
					"FROM DEPART depart1 "+
					"INNER JOIN DEPART depart2 "+
					"ON depart1.ID = depart2.COUNTRY "+
					"WHERE LOWER(depart2.NAME) LIKE '%"+search+"%' "+
					"ORDER BY "+sidx+" "+sord+" "+
					"LIMIT ? OFFSET ?";
		List<Departure> list = new ArrayList<Departure>();
		Connection conn = null;
		PreparedStatement pstmt = null;
 
		try {
			
			conn = (Connection) DriverManager.getConnection(JdbcDriverProperties.DB_URL, JdbcDriverProperties.USER, JdbcDriverProperties.PASS);
			pstmt = (PreparedStatement) conn.prepareStatement(sql); 
			pstmt.setInt(1, limit);
			pstmt.setInt(2, offset);
			ResultSet rs = pstmt.executeQuery();
			
			if(CheckNullEmpty.isNotNullOrEmpty(rs)){
				while(rs.next()){
					Departure d = new Departure();
					d.setName(rs.getString("NAME"));
					d.setCode(rs.getString("CODE"));
					d.setCountry(rs.getString("COUNTRY"));
					list.add(d);
				}
			}
			return list;
 
		} catch (SQLException e) {
			throw new RuntimeException(e);
 
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
}
