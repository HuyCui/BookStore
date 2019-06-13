package com.bookstore.dao;

import com.bookstore.util.DBUtil;
import com.bookstore.web.User;

public class LoginDao implements ILoginDao{
   private DBUtil db = new DBUtil();
	@Override
	public User canLogin(User u) {
		// TODO Auto-generated method stub
		String sql = "select * from user where account="+u.getAccount()+" and  password='"+u.getPassword()+"'";
		User ut = null;
		try {
		 ut = (User) db.getObject(User.class,sql, new Object[]{});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ut;
	}
    
}
