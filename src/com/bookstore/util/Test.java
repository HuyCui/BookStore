package com.bookstore.util;

import java.util.Map;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String ss = "1,2,3,4,5,6";
		String []ids = ss.split("\\,");
		System.out.println(ids.length+"����");
		
      StringBuffer sb = new StringBuffer(ss);
      System.out.println(sb.indexOf("1"));
	}

}
