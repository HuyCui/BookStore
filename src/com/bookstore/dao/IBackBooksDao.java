package com.bookstore.dao;

import java.util.List;

import com.bookstore.web.Book;

public interface IBackBooksDao {
public List<Book> findBooks(int uid);
public void modUser(int uid,long id);
public void modBook(long id,int shelf_Id,long pid);
public int findShelf(int id);
public void modShelf(int id,int shelf_Id,long book_Id);
public double checkMoney(int id);
}
