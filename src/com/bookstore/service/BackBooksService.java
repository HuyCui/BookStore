package com.bookstore.service;

import java.util.List;

import com.bookstore.dao.BackBooksDao;
import com.bookstore.web.Book;

public class BackBooksService implements IBackBooksService {
    BackBooksDao bd = new BackBooksDao();
	@Override
	public List<Book> findBooks(int uid) {
		// TODO Auto-generated method stub
		return bd.findBooks(uid);
	}
	@Override
	public void modUser(int uid, long id) {
		// TODO Auto-generated method stub
		bd.modUser(uid,id);
	}
	@Override
	public void modBook(long id,int shelf_Id,long pid) {
		// TODO Auto-generated method stub
		bd.modBook(id,shelf_Id,pid);
	}
	@Override
	public int findShelf(int id) {
		// TODO Auto-generated method stub
		return bd.findShelf(id);
	}
	@Override
	public void modShelf(int id, int shelf_id, long book_id) {
		// TODO Auto-generated method stub
		bd.modShelf(id, shelf_id, book_id);
	}
	@Override
	public double checkMoney(int id) {
		// TODO Auto-generated method stub
	      return	bd.checkMoney(id);
	}

}
