package com.bookstore.web;

import java.util.Date;

public class User {
       private int account ;
       private String password;
       private String borrowBooks;
       private String sharedBooks;
       private double money;
       private Date borrowTime;
       
	public User() {
		super();
	}
	public int getAccount() {
		return account;
	}
	public void setAccount(int account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getBorrowBooks() {
		return borrowBooks;
	}
	public void setBorrowBooks(String borrowBooks) {
		this.borrowBooks = borrowBooks;
	}
	public String getSharedBooks() {
		return sharedBooks;
	}
	public void setSharedBooks(String sharedBooks) {
		this.sharedBooks = sharedBooks;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public Date getBorrowTime() {
		return borrowTime;
	}
	public void setBorrowTime(Date borrowTime) {
		this.borrowTime = borrowTime;
	}
       
}
