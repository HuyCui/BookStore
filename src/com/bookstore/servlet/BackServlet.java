package com.bookstore.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.service.BackBooksService;
import com.bookstore.service.BorrowBooksService;
import com.bookstore.web.Book;
import com.bookstore.web.User;

/**
 * Servlet implementation class BackServlet
 */
@WebServlet("/backServlet")
public class BackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   BackBooksService bs = new BackBooksService();
   BorrowBooksService bb = new BorrowBooksService();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("book_Id");
		Book book = bb.findBook(Long.valueOf(id));
		request.setAttribute("book", book);
	    request.getRequestDispatcher("/backBook.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = (String) request.getSession().getAttribute("id");
		if(id==null||id.equals(" ")) {
              response.sendRedirect("/shelfSelect.jsp");
		}
        User u = (User)request.getSession().getAttribute("user");
		List bookList = bs.findBooks(u.getAccount());
		if(bookList==null) {
			 request.getSession().removeAttribute("id");
			response.sendRedirect(request.getContextPath()+"/bookNull.jsp");
		}
		request.setAttribute("bookList", bookList);
		request.getRequestDispatcher("/backBooks.jsp").forward(request,response);

	}

}
