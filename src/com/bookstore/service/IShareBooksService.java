package com.bookstore.service;

import com.bookstore.web.Book;

public interface IShareBooksService {

	public void addBooks(Book book);

	public void addShareBook(long id, int account);

}