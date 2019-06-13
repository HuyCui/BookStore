package com.bookstore.util;


import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

public class WebUtils {

	public WebUtils() {
		// TODO Auto-generated constructor stub
	}
	public static <T> T request2bean(HttpServletRequest request,Class<T> beanclass) throws InstantiationException, IllegalAccessException {
		T u = beanclass.newInstance();
		ConvertUtils.register(new Converter() {
			
			@Override
			public Object convert(Class arg0, Object arg1) {
				// TODO Auto-generated method stub
				String st = (String) arg1;
				SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
				try {
					simple.parse(st);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return st;
			}
			
		}, Date.class);
		try {
			BeanUtils.populate(u, request.getParameterMap());
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		return u;
	}
	public static <T> T request22bean(Map map,Class<T> beanclass) throws InstantiationException, IllegalAccessException {
		T u = beanclass.newInstance();
		ConvertUtils.register(new Converter() {
			@Override
			public Object convert(Class arg0, Object arg1) {
				// TODO Auto-generated method stub
				String st = (String) arg1;
				SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
				try {
					simple.parse(st);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return st;
			}
			
		}, Date.class);
		try {
			BeanUtils.populate(u, map);
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		return u;
	}

}
