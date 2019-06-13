package com.bookstore.service;

import java.util.List;

import com.bookstore.web.Book;
import com.bookstore.web.Shelf;

public interface IBorrowBooksService {
	public List<Book> findBooks(List<Shelf> list);
	public List<Shelf> findShelf(int  id);
	public Book findBook(long id);
	public void modBook(long id);
	public void modShelf(int id,int shelfId);
	public void modUser(long id,int uid);
	public void modMoney(Long id, int account);
}
