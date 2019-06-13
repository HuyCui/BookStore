package com.bookstore.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bookstore.util.DBUtil;
import com.bookstore.web.Book;
import com.bookstore.web.Shelf;

public class BorrowBooksDao implements IBorrowBooksDao {
    DBUtil db = new DBUtil();
	
	@Override
	public List<Shelf> findShelf(int id) {
		// TODO Auto-generated method stub
		String sqll = "select name from shelfind where shelfId="+id;
		Map<String, Object> findShelf = new HashMap();
		List bookList = null;
		String name = null;
		try {
			 findShelf = db.getObject(sqll); 
		}
		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(findShelf!=null) {
			 name = (String) findShelf.get("name");
			 String sql = "select * from "+name+" where "+name+".stat=true";
			 try {
				bookList = db.getQueryList(Shelf.class, sql,new Object[]{});
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 //return bookList;
		}
		return bookList;
	}



	@Override
	public List<Book> findBooks(List<Shelf> list) {
		// TODO Auto-generated method stub
		List bookList = null;
		StringBuffer sb = new StringBuffer();
		String ss = null;
		for(Shelf ll:list) {
			sb.append(ll.getBook_id());
			sb.append(",");
		}if(sb.length()==1) {
			ss=null;
		}
		else if(sb.length()==0) {
			return null;
		}
		else {
		 ss = sb.substring(0, sb.length()-1);
		}
		if(ss!=null) {
		String sql = "select * from book where FIND_IN_SET(book.id,'"+ss+"')";
		try {
			bookList = db.getQueryList(Book.class, sql, new Object[]{});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return bookList;
	}



	@Override
	public Book findBook(long id) {
		// TODO Auto-generated method stub
		String sql = "select * from book where book.id="+id;
		Book b = new Book();
        try {
			b =  (Book) db.getObject(Book.class, sql, new Object[]{});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}



	@Override
	public void modBook(long id) {
		// TODO Auto-generated method stub
		String sql = "update book set shelfId=0 ,stat=true,save=0 where id="+id;
		try {
			db.execute(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	@Override
	public void modShelf(int id, int shelfId) {
		// TODO Auto-generated method stub
		String sql = "select name from shelfind where shelfId="+id;
		Map shelf = new HashMap();
		try {
			shelf = db.getObject(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String name  = (String) shelf.get("name");
		String sql1 = "update "+name+" set book_Id=0,stat=false where id="+shelfId;
		try {
			db.execute(sql1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	@Override
	public void modUser(long id,int uid) {
		// TODO Auto-generated method stub
		String sql = " select borrowBooks from user where account="+uid;
		Map ll= null;
		StringBuffer ss=null;
		try {
			ll = db.getObject(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ss = new StringBuffer((String) ll.get("borrowBooks"));
		ss.append(",");
		ss.append(id);
		if(ss.indexOf(",")==0) {
			ss.deleteCharAt(0);
		}
		System.out.println(ss.toString()+"���ǽ���");
		String sql1 = "update user set money=money-2,borrowBooks='"+ss.toString()+"' where account="+uid;
		try {
			db.execute(sql1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	public void modMoney(Long id, int account) {
		// TODO Auto-generated method stub
	  String sql = "select user_id from sharebook where book_id="+id;
	  Map map = new HashMap();
	  try {
		map = db.getObject(sql);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  try{
	  if(map.get("user_id")!=null) {
		 int ids = Integer.parseInt((String) map.get("user_id"));
		  sql = "update user set money=money+1 where user_id="+ids;
		  db.execute(sql);
	  }
	}
	catch(Exception e) {
	}
	
	}
	

}
