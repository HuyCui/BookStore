package com.bookstore.service;

import com.bookstore.dao.IShareBooksDao;
import com.bookstore.dao.ShareBooksDao;
import com.bookstore.web.Book;

public class ShareBooksService implements IShareBooksService {
    IShareBooksDao sd = new ShareBooksDao();
	/* (non-Javadoc)
	 * @see com.bookstore.service.IShareBooksService#addBooks(com.bookstore.web.Book)
	 */
	@Override
	public  void addBooks(Book book) {
       sd.addBooks(book);		
	}
	@Override
	public void addShareBook(long id, int account) {
		// TODO Auto-generated method stub
		sd.addShareBook(id,account);
	}

}
