package com.bookstore.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.service.BackBooksService;
import com.bookstore.service.BorrowBooksService;
import com.bookstore.service.IBackBooksService;
import com.bookstore.util.ServletSocket;
import com.bookstore.web.User;

/**
 * Servlet implementation class borrowServlet
 */
@WebServlet("/borrowServlet")
public class BorrowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       BorrowBooksService bb = new BorrowBooksService();
       IBackBooksService bs = new BackBooksService();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = (String) request.getSession().getAttribute("id");
		if(id==null||id=="") {
				response.sendRedirect("/shelfSelect.jsp");
	}	
	   List shelfList = bb.findShelf(Integer.valueOf(id));
	   if(shelfList==null) {
		   request.getSession().removeAttribute("id");
		   response.sendRedirect(request.getContextPath()+"/bookNull.jsp");
		   return;
	   }
	   List bookList = bb.findBooks(shelfList);
	   if(bookList==null) {
		   request.getSession().removeAttribute("id");
		   response.sendRedirect(request.getContextPath()+"/bookNull.jsp");
		   return;
	   }
	   request.setAttribute("bookList", bookList);
	   request.getRequestDispatcher("/borrowBooks.jsp").forward(request,response);
	}


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		   double money =  bs.checkMoney(((User)request.getSession().getAttribute("user")).getAccount());
		   if(money<2) {
			   response.sendRedirect(request.getContextPath()+"/moneyLess.jsp");
			   return;
		   }
		   String book = request.getParameter("book");
		   ServletSocket so = new ServletSocket();
		   so.setMsg("*"+book);
		   so.con();
		    String []ss = book.split("\\+");
			/*String book_Id = request.getParameter("book_Id");
			String shelf_Id = request.getParameter("shelf_Id");*/
		    String id = (String) request.getSession().getAttribute("id");
			bb.modBook(Long.valueOf(ss[0]));
			bb.modShelf(Integer.valueOf(id),Integer.valueOf(ss[1]));
			bb.modUser(Long.valueOf(ss[0]),((User)request.getSession().getAttribute("user")).getAccount());
			bb.modMoney(Long.valueOf(ss[0]),((User)request.getSession().getAttribute("user")).getAccount());
			List shelfList = bb.findShelf(Integer.valueOf(id));
			 if(shelfList==null) {
				 request.getSession().removeAttribute("id");
				   response.sendRedirect(request.getContextPath()+"/bookNull.jsp");
				   return;
			   }
			List bookList = bb.findBooks(shelfList);
			  if(bookList==null) {
				  request.getSession().removeAttribute("id");
				   response.sendRedirect(request.getContextPath()+"/bookNull.jsp");
				   return;
			   }
			request.setAttribute("bookList", bookList);

            request.getRequestDispatcher("/borrowBooks.jsp").forward(request,response);
         }
	
}
