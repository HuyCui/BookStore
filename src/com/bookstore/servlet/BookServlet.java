package com.bookstore.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.service.BackBooksService;
import com.bookstore.service.BorrowBooksService;
import com.bookstore.util.ServletSocket;
import com.bookstore.web.Book;
import com.bookstore.web.User;

@WebServlet("/bookServlet")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   BorrowBooksService bb = new BorrowBooksService();
   BackBooksService bs = new BackBooksService();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String book_Id=request.getParameter("book_Id");
		//String []ll = request.getRequestURI().split("/");
		/*if(!ll[2].equals("backBooks.jsp")) {*/
		Book book = bb.findBook(Long.valueOf(book_Id));
		request.setAttribute("book", book);
		request.getRequestDispatcher("/borrowBook.jsp").forward(request, response);
		return;
		/*}
		else {
			String id = (String) request.getSession().getAttribute("id");
			int shelf_Id = bs.modShelf(Integer.valueOf((String) request.getSession().getAttribute("id")));
			String ss = "*"+id+"+"+shelf_Id;
			new ServletSocket().con(ss);
			bs.modUser(((User)request.getSession().getAttribute("user")).getAccount(),Integer.valueOf(book_Id));
			bs.modBook(Integer.valueOf(book_Id),Integer.valueOf((String) request.getSession().getAttribute("id")));
			response.sendRedirect("/select.jsp");
		}*/
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String book_Id=request.getParameter("book");
		String id = (String) request.getSession().getAttribute("id");
		int shelf_Id = bs.findShelf(Integer.valueOf((String) request.getSession().getAttribute("id")));
		if(shelf_Id==-1) {
			 request.getSession().removeAttribute("id");
			 response.sendRedirect(request.getContextPath()+"/bookNull.jsp");
			   return;
		}
		String ss = "#"+book_Id+"+"+shelf_Id;
		ServletSocket so = new ServletSocket();
		so.setMsg(ss);
		so.con();
		bs.modUser(((User)request.getSession().getAttribute("user")).getAccount(),Long.valueOf(book_Id));
		bs.modBook(Long.valueOf(book_Id),Integer.valueOf((String) request.getSession().getAttribute("id")),Long.valueOf(shelf_Id));
		bs.modShelf(Integer.valueOf((String) request.getSession().getAttribute("id")),shelf_Id,Long.valueOf(book_Id));
		response.sendRedirect(request.getContextPath()+"/select.jsp");
		/*String book = request.getParameter("book");
	    new ServletSocket().con(book);
	    String []ss = book.split("\\+");
	    request.setAttribute("book_Id",ss[0]);
	    request.setAttribute("shelf_Id",ss[1]);
		response.sendRedirect(request.getContextPath()+"/");*/
	}

}
