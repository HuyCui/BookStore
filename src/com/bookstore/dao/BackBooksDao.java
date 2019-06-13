package com.bookstore.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bookstore.util.DBUtil;
import com.bookstore.web.Book;

public class BackBooksDao implements IBackBooksDao {
       DBUtil db = new DBUtil();
	@Override
	public List<Book> findBooks(int uid) {
		// TODO Auto-generated method stub
		String sql = "select borrowBooks from user where account ="+uid;
		Map book = null;
		try {
			book=db.getObject(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(book==null) {
			return null;
		}
		String books = (String) book.get("borrowBooks");
		String sql2 = "select * from book where FIND_IN_SET(book.id,'"+books+"')";
		List bookList = new ArrayList();
		try {
			bookList = db.getQueryList(Book.class, sql2, new Object[]{});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookList;
	}
	@Override
	public void modUser(int uid, long id) {
		// TODO Auto-generated method stub
		String sql = " select borrowBooks from user where account="+uid;
		Map ll= null;
		try {
			ll = db.getObject(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringBuffer ss = new StringBuffer((String) ll.get("borrowBooks"));
		int length = (new Long(id)).toString().length();
		int last = ss.indexOf(id+"");
		if(last==0) {
			if(length==ss.toString().length()) {
			ss = ss.delete(0,length);
			}
			else {
				ss = ss.delete(last,length+1);
			}
				
		}
		else{
			ss = ss.delete(last-1,last+length);
		}
		if(ss.indexOf(",")==0) {
			ss.deleteCharAt(0);
		}
		String sql1 = "update user set money=money+1,borrowBooks='"+ss.toString()+"' where account="+uid;
		try {
			db.execute(sql1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void modBook(long id,int shelf_Id,long pid) {
		// TODO Auto-generated method stub
		String sql = "update book set shelfId="+shelf_Id+" ,stat=true,save ="+pid+" where id="+id;
		try {
			db.execute(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public int findShelf(int id) {
		// TODO Auto-generated method stub
		String sql = "select name from shelfind where shelfId="+id;
		Map shelf = null;
		try {
			shelf = db.getObject(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(shelf==null) {
			return -1;
		}
		String name  = (String) shelf.get("name");
		String sql1 = "select id from "+name+" where stat=false limit 1";
		int shelf_id;
		Map shelfs = new HashMap();
		try {
			shelfs = db.getObject(sql1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(shelfs==null) {
			return -1;
		}
		shelf_id  = (int) shelfs.get("id");
		return shelf_id;
	}
	@Override
	public void modShelf(int id, int shelf_Id, long book_Id) {
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
		String sql1 = "update "+name+" set book_Id="+book_Id+",stat=true where id="+shelf_Id;
		try {
			db.execute(sql1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public double checkMoney(int id) {
		String sql = "select money from user where account="+id;
		Map map = new HashMap();
		try {
			map = db.getObject(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double money = 0;
		try {
		    money =(((Number)map.get("money")).doubleValue());
		    System.out.println("ceshizhuanyong");
		}
		catch(Exception e) {
		}
		return money;
		// TODO Auto-generated method stub
		
	}

	
	}
