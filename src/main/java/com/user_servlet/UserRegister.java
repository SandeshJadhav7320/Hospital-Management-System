package com.user_servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.UserDAO;
import com.db.DBConnect;
import com.entity.User;

@WebServlet("/user_register")
public class UserRegister  extends HttpServlet
{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		try
		{
			String name=req.getParameter("name");
			String email=req.getParameter("email");
			String password=req.getParameter("password");
			
			User u=new User(name,email,password);
			
			UserDAO dao=new UserDAO(DBConnect.getConn());
			
			HttpSession session=req.getSession();
			
			boolean f=dao.register(u);
			
			if(f)
			{
				session.setAttribute("succcMSG", "Register Successfuly");
				resp.sendRedirect("signup.jsp");
			}
			else
			{
				session.setAttribute("errorMSG", "Something wrong on server");
				resp.sendRedirect("signup.jsp");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	private void getSession() {
		// TODO Auto-generated method stub
		
	}
	
}
