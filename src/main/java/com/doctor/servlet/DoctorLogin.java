package com.doctor.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.DoctorDao;
//import com.dao.UserDAO;
import com.db.DBConnect;
import com.entity.Doctor;
//import com.entity.User;
@WebServlet("/doctorLogin")
public class DoctorLogin extends HttpServlet
{
	protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws IOException,ServletException
	{
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		
		HttpSession session =req.getSession();
		DoctorDao dao =new DoctorDao(DBConnect.getConn());
		
		Doctor doctor=dao.login(email,password);
		
		if(doctor!=null)
		{
			session.setAttribute("doctObj", doctor);
			
			resp.sendRedirect("doctor/index.jsp");
		}
		else
		{
			session.setAttribute("errorMSG","Invalid email and password" );
			resp.sendRedirect("Doctor_Login_page.jsp");
		}
	}
}

