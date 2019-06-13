package com.bookstore.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.bookstore.service.BackBooksService;
import com.bookstore.service.IBackBooksService;
import com.bookstore.service.IShareBooksService;
import com.bookstore.service.ShareBooksService;
import com.bookstore.util.ServletSocket;
import com.bookstore.util.WebUtils;
import com.bookstore.web.Book;
import com.bookstore.web.User;

/**
 * Servlet implementation class SharedServlet
 */
@WebServlet("/sharedServlet")
public class SharedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BackBooksService bss = new BackBooksService();
    IBackBooksService bs = new BackBooksService();
    IShareBooksService sb = new ShareBooksService();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<FileItem> list =null;
		DiskFileItemFactory factory = new  DiskFileItemFactory();
	   /* factory.setSizeThreshold(1024*1024*2);
		factory.setRepository(new File(request.getServletContext().getRealPath("/temp")));*/
	    ServletFileUpload upload = new ServletFileUpload(factory);
		//upload.setFileSizeMax(1024*1024*25);
		upload.setHeaderEncoding("utf-8");
		 try {
			 list= upload.parseRequest(request);
		}
		 catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 Map map = new HashMap();
		  for(FileItem item:list) {
			  if(item.isFormField()) {
				  String name = item.getFieldName();
				  String value = item.getString("utf-8");
				map.put(name,value);
			  }
			  else {
				  InputStream in = item.getInputStream();
				  int len = 0;
				  String name = item.getName().substring(item.getName().lastIndexOf("\\")+1);
				  FileOutputStream out = new FileOutputStream(request.getServletContext().getRealPath("/image")+File.separator+name);
				  byte []buffer = new byte[1024];
				  while((len=in.read(buffer))>0) {
					  out.write(buffer,0,len);
				  }
				  map.put("image",name);
				  in.close();
				  out.close();
				  //item.delete();
			  }
		  }
		  map.put("shelfId",request.getSession().getAttribute("id")); //Integer.parseInt((String) request.getSession().getAttribute("id")));
		 System.out.println(map.get("shelfId"));
		int shelf_Id = bss.findShelf(Integer.valueOf((String) request.getSession().getAttribute("id")));
		if(shelf_Id<=0) {
			 request.getSession().removeAttribute("id");
			 response.sendRedirect(request.getContextPath()+"/bookNull.jsp");
			   return;
		}
		 map.put("save",shelf_Id);
		 Book book= new Book();
			try {
				book = WebUtils.request22bean(map, Book.class);
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ServletSocket so = new ServletSocket();
			so.setMsg("#"+book.getId()+"+"+book.getSave());
			so.con();
			int id = Integer.parseInt((String) request.getSession().getAttribute("id"));
			book.setShelfId(id);
		   sb.addBooks(book );
		   User u = (User) request.getSession().getAttribute("user");
	       sb.addShareBook(book.getId(),u.getAccount());
	       bss.modShelf(book.getShelfId(),book.getSave(),book.getId());
	      response.sendRedirect(request.getContextPath()+"/shareBook.jsp");
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
