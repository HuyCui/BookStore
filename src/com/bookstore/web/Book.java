package com.bookstore.web;

public class Book {
   private long id;
   private String name;
   private String description;
   private String image;
   private int shelfId;
   private int save;
   private boolean stat;
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getImage() {
	return image;
}
public void setImage(String image) {
	this.image = image;
}
public int getShelfId() {
	return shelfId;
}
public void setShelfId(int shelfId) {
	this.shelfId = shelfId;
}
public boolean isStat() {
	return stat;
}
public void setStat(boolean stat) {
	this.stat = stat;
}

public int getSave() {
	return save;
}
public void setSave(int save) {
	this.save = save;
}
public Book() {
	super();
}
   
}
