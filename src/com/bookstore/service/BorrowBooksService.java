package com.bookstore.service;

import java.util.List;

import com.bookstore.dao.BorrowBooksDao;
import com.bookstore.web.Book;
import com.bookstore.web.Shelf;

public class BorrowBooksService implements IBorrowBooksService {
     BorrowBooksDao bb = new BorrowBooksDao();

	@Override
	public List<Book> findBooks(List<Shelf> list) {
		// TODO Auto-generated method stub
		return bb.findBooks(list);
	}

	@Override
	public List<Shelf> findShelf(int id) {
		// TODO Auto-generated method stub
		return bb.findShelf(id);
	}

	@Override
	public Book findBook(long id) {
		// TODO Auto-generated method stub
		return bb.findBook(id);
	}

	@Override
	public void modBook(long id) {
		// TODO Auto-generated method stub
		 bb.modBook(id);
	}

	@Override
	public void modShelf(int id,int shelfId) {
		// TODO Auto-generated method stub
		bb.modShelf(id,shelfId);
	}

	@Override
	public void modUser(long id,int uid) {
		// TODO Auto-generated method stub
		bb.modUser(id,uid);
	}

	public void modMoney(Long id, int account) {
		// TODO Auto-generated method stub
	bb.modMoney(id,account);
	}
}
