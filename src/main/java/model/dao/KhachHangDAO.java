package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.bean.KhachHang;

public class KhachHangDAO extends BaseDAO {

	public boolean isValidAccount(String tenDangNhap, String matKhau) {
		Connection connection = getConnection();
        String sql = "SELECT * FROM KHACHHANG WHERE TenDangNhap = ? AND MatKhau = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
        	pstmt = connection.prepareStatement(sql);
        	pstmt.setString(1, tenDangNhap);
        	pstmt.setString(2, matKhau);
        	rs = pstmt.executeQuery();
        	
        	if (rs.next()) {
        		return true;
        	} else {
    			return false;
        	}

        } catch (SQLException e) {	
        	e.printStackTrace();
        } finally {
        	closeConnection(connection, pstmt, rs);
        }

        return false;
	}
	
	public KhachHang getAccount(String tenDangNhap) {
		Connection connection = getConnection();
        String sql = "SELECT * FROM KHACHHANG WHERE TenDangNhap = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        KhachHang khachHang = null;

        try {
        	pstmt = connection.prepareStatement(sql);
        	pstmt.setString(1, tenDangNhap);
        	rs = pstmt.executeQuery();
        	
        	if (rs.next()) {
        		khachHang = new KhachHang();
        		
        		khachHang.setId(rs.getString("id"));
        		khachHang.setTenDangNhap(rs.getString("TenDangNhap"));
        		khachHang.setHoTen(rs.getString("HoTen"));
        		khachHang.setSoDienThoai(rs.getString("SoDienThoai"));
        		khachHang.setMatKhau(rs.getString("MatKhau"));
        		khachHang.setEmail(rs.getString("Email"));
        		khachHang.setGioiTinh(rs.getString("GioiTinh"));
        		khachHang.setNgaySinh(rs.getDate("NgaySinh")); 		
        	}

        } catch (SQLException e) {	
        	e.printStackTrace();
        } finally {
        	closeConnection(connection, pstmt, rs);
        }

        return khachHang;
	}

	public int insertKhachHang(String id, String tenDangNhap, String hoTen, String soDienThoai, String matKhau, 
			String email, String gioiTinh, Date date) {
		Connection connection = getConnection();
        String sql = "INSERT INTO KHACHHANG (Id, TenDangNhap, HoTen, SoDienThoai, MatKhau, Email, GioiTinh, NgaySinh)"
        		+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = null;

        try {
        	pstmt = connection.prepareStatement(sql);
        	pstmt.setString(1, id);
        	pstmt.setString(2, tenDangNhap);
        	pstmt.setString(3, hoTen);
        	pstmt.setString(4, soDienThoai);
        	pstmt.setString(5, matKhau);
        	pstmt.setString(6, email);
        	pstmt.setString(7, gioiTinh);
        	pstmt.setDate(8, date);
        	pstmt.executeUpdate();

        } catch (SQLException e) {	
        	e.printStackTrace();
        	return 6;
        } finally {
        	closeConnection(connection, pstmt, null);
        }
		
		return 0;
	}

	public String getLastestMaKH() {
		Connection connection = getConnection();
        String sql = "SELECT TOP 1 Id FROM KHACHHANG ORDER BY Id DESC";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
        	pstmt = connection.prepareStatement(sql);
        	rs = pstmt.executeQuery();
        	
        	if (rs.next()) {
        		return rs.getString("Id");
        	}

        } catch (SQLException e) {	
        	e.printStackTrace();
        } finally {
        	closeConnection(connection, pstmt, rs);
        }
        
        return "";
	}

}
