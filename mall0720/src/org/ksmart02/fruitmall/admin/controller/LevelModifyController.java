package org.ksmart02.fruitmall.admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ksmart02.fruitmall.admin.service.AdminService;


@WebServlet("/LevelModifyController")
public class LevelModifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AdminService adminService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LevelModifyController의 doGet실행");
		response.sendRedirect("/WEB-INF/Views/admin/levelModifyForm.jsp");
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			adminService = new AdminService();
			adminService.levelModifyForm();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		doGet(request, response);
	}

}
