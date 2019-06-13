package com.bookstore.web;

public class Shelf {
 private int id;
 private long book_id;
 private boolean stat;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public long getBook_id() {
	return book_id;
}
public void setBook_id(long book_id) {
	this.book_id = book_id;
}
public boolean isStat() {
	return stat;
}
public void setStat(boolean stat) {
	this.stat = stat;
}
public Shelf() {
	super();
}
}
