package com.bookstore.util;


import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 鏁版嵁搴撹繛鎺ユ睜 
 * @author Administrator
 *
 */
public class DBDataSource {
	
	//淇敼 鏁版嵁搴撹繛鎺ュ弬
	public static String DRIVER;
	public static String URL;
	public static String USER;
	public static String PWD;
	public static int maxPoolSize;
	public static int minPoolSize;
	public static int initialSize;
	public static int minIdle;
	public static int maxIdle;
	public static int maxWait;
	public static int maxActive;	
	
	private static DataSource dataSource;
	private static ComboPooledDataSource cpDataSource = null;
	
	//鍔犺浇椹卞姩
	static {
		try {
			//璇诲彇閰嶇疆鏂囦欢锛屽姞杞絁DBC鍥涘ぇ鍙傛暟
			Properties config = new Properties();
			config.load(new FileReader(DBDataSource.class.getClassLoader().getResource("db.properties").getPath()));
			DRIVER = config.getProperty("drivername");
			URL = config.getProperty("url");
			USER = config.getProperty("username");
			PWD	= config.getProperty("password");

			maxPoolSize	= Integer.parseInt(config.getProperty("maxPoolSize"));
			minPoolSize	= Integer.parseInt(config.getProperty("minPoolSize"));
			initialSize	= Integer.parseInt(config.getProperty("initialSize"));
			minIdle	= Integer.parseInt(config.getProperty("minIdle"));
			maxIdle	= Integer.parseInt(config.getProperty("maxIdle"));
			maxWait	= Integer.parseInt(config.getProperty("maxWait"));
			maxActive = Integer.parseInt(config.getProperty("maxActive"));
			//鍔犺浇椹卞姩
			Class.forName(DRIVER);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**************** c3p0 鏁版嵁搴撹繛鎺ユ睜 鍚姩鏂规硶******************/
	private static void c3p0DataSource() throws Exception {
		cpDataSource = new ComboPooledDataSource();
		cpDataSource.setDriverClass(DRIVER);
		cpDataSource.setJdbcUrl(URL);
		cpDataSource.setUser(USER);
		cpDataSource.setPassword(PWD);
		cpDataSource.setMaxPoolSize(maxPoolSize);
		cpDataSource.setMinPoolSize(minPoolSize);
	}

	/*************** DBCP 鏁版嵁搴撹繛鎺ユ睜 鍚姩鏂规硶 ***************/
	public static void initDataSource() {
		BasicDataSource bds = new BasicDataSource();

		bds.setUrl(URL);
		bds.setDriverClassName(DRIVER);
		bds.setUsername(USER);
		bds.setPassword(PWD);
		bds.setInitialSize(initialSize);
		bds.setMaxActive(maxActive);
		bds.setMinIdle(minIdle);
		bds.setMaxIdle(maxIdle);
		bds.setMaxWait(maxWait);

		dataSource = bds;
	}

	/**
	 * jdbc鏁版嵁搴撹繛鎺� 
	 * @return
	 * @throws Exception
	 */
	public static Connection getConnectionJdbc() throws Exception {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PWD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * DBCP鏁版嵁搴撹繛鎺ュ叆
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnectionDbcp() throws SQLException {
		if (dataSource == null) {
			initDataSource();
		}
		Connection conn = null;
		if (dataSource != null) {
			conn = dataSource.getConnection();
		}
		return conn;
	}

	/**
	 * c3p0鏁版嵁搴撹繛鎺ュ叆
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Connection getConnectionC3P0() throws Exception {
		if (cpDataSource == null) {
			c3p0DataSource();
		}
		Connection conn = null;
		if (cpDataSource != null) {
			conn = cpDataSource.getConnection();
		}
		return conn;
	}

}
