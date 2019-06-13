package com.bookstore.service;

import com.bookstore.dao.LoginDao;
import com.bookstore.web.User;

public class LoginService implements ILoginService {
    LoginDao ld = new LoginDao();
	@Override
	public User canLogin(User u) {
		// TODO Auto-generated method stub
		return ld.canLogin(u);
	}

}
