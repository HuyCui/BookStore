package com.bookstore.dao;

import com.bookstore.web.Book;

public interface IShareBooksDao {

	public void addBooks(Book book);

	public void addShareBook(long id, int account);

}