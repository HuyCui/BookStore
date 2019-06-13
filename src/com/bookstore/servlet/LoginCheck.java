package com.bookstore.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.service.LoginService;
import com.bookstore.web.User;
@WebServlet("/loginCheck")
public class LoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id =request.getParameter("id");
		request.getSession().setAttribute("id", id);
		response.sendRedirect(request.getContextPath()+"/login.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		LoginService ls = new LoginService();
		User u = new User();
		u.setAccount(Integer.valueOf(account));
		/*try{
			
		}catch(NumberFormatException e) {
			
		}*/

		u.setPassword(password);
		u = ls.canLogin(u);
		if(u==null) {
			response.sendRedirect(request.getContextPath()+"/login.jsp");
			return;
			}
		request.getSession().setAttribute("user", u);
		request.getRequestDispatcher("/shelfSelect.jsp").forward(request,response);
	}

}
