package controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.GioHang;
import model.bean.KhachHang;
import model.bo.ShowKhachHangBO;
import model.bo.UpdateChiTietHoaDonBO;
import model.bo.UpdateHoaDonBO;

public class OrderServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("text/html");
		
		RequestDispatcher rd = null;
		HttpSession session = request.getSession();
		
		GioHang gioHang = (GioHang) session.getAttribute("cart");
		KhachHang khachHang = (KhachHang) session.getAttribute("user");
		int check;
		String message;
		
		UpdateHoaDonBO updateHoaDonBO = new UpdateHoaDonBO();
		ShowKhachHangBO showKhachHangBO = new ShowKhachHangBO();
		
		khachHang = showKhachHangBO.getAccount(khachHang.getTenDangNhap());
		Date thoiGian = new Date(System.currentTimeMillis());
		
		check = updateHoaDonBO.insertHoaDon(khachHang.getId(), thoiGian, "Dang chuan bi hang");
		
		if (check == 6) {
			message = "Đã xảy ra lỗi, vui lòng thử lại sau!";
			
			rd = request.getRequestDispatcher("views/user/order.jsp");
			rd.forward(request, response);
		}
		
		UpdateChiTietHoaDonBO updateChiTietHoaDonBO = new UpdateChiTietHoaDonBO();
		for (int i = 0; i < gioHang.getMatHang().size(); i++) {
			updateChiTietHoaDonBO.insertChiTietHoaDon(gioHang.getMatHang().get(i).getSanPham(), gioHang.getMatHang().get(i).getSoLuong());
		
		}
		
		rd = request.getRequestDispatcher("views/user/thanksyou.jsp");
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
