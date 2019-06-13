package com.bookstore.service;

import java.util.List;

import com.bookstore.web.Book;

public interface IBackBooksService {
public List<Book> findBooks(int  uid);
public void modUser(int uid,long id);
public void modBook(long id,int shelf_Id,long pid);
public int findShelf(int id);
public void modShelf(int id,int shelf_id,long book_id);
public double checkMoney(int id);
}
