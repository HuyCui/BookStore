package com.bookstore.dao;

import com.bookstore.util.DBUtil;
import com.bookstore.web.Book;

public class ShareBooksDao implements IShareBooksDao {
	DBUtil db = new DBUtil();
  /* (non-Javadoc)
 * @see com.bookstore.dao.IShareBooksDao#addBooks(com.bookstore.web.Book)
 */
@Override
public void addBooks(Book book){
	  String sql = "insert into book(id,name,description,image,shelfId,save,stat)values(?,?,?,?,?,?,1)";
	  try {
		db.execute(sql, new Object[] {book.getId(),book.getName(),book.getDescription(),book.getImage(),book.getShelfId(),book.getSave()});
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
@Override
public void addShareBook(long id, int account) {
	// TODO Auto-generated method stub
	String sql = "insert into sharebook(book_id,user_id)values("+id+","+account+")";
	try {
		db.execute(sql);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
