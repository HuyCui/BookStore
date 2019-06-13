package com.bookstore.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;


/**
 * 鏁版嵁搴撴搷浣滆緟鍔╃被
 * @version 3.0
 * @author yaohc
 */
public class DBUtil {

	private static Logger logger = Logger.getLogger("DBUtil");
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	//private static Connection conn;
	/**
	 * 璇ヨ鍙ュ繀椤绘槸 SQL INSERT銆乁PDATE 銆丏ELETE 璇彞
	 * 
	 * @param sql 
	 * @return
	 * @throws Exception
	 */
	public int execute(String sql) throws Exception {
		return execute(sql,new Object[]{});
	}

	
	/**
	 * 璇ヨ鍙ュ繀椤绘槸 SQL INSERT銆乁PDATE 銆丏ELETE 璇彞
	 *      insert into table values(?,?,?,?)
	 * @param sql
	 * @param paramList锛氬弬鏁帮紝涓嶴QL璇彞涓殑鍗犱綅绗︿竴
	 * @return
	 * @throws Exception
	 */
	public int execute(String sql,Object[] paramList) throws Exception {
		if (sql == null || sql.trim().equals("")) {
			logger.info("parameter is valid!");
		}

		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			conn = getConnection();
			pstmt = DBUtil.getPreparedStatement(conn, sql);
			setPreparedStatementParam(pstmt, paramList);
			if (pstmt == null) {
				return -1;
			}
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			logger.info(e.getMessage());
			throw new Exception(e);
		} finally {
			closeStatement(pstmt);
			closeConn(conn);
		}

		return result;
	}
	/**
	 * 浜嬬墿澶勭悊绫�
	 * @param connection
	 * @param sql
	 * @param paramList锛氬弬鏁帮紝涓嶴QL璇彞涓殑鍗犱綅绗︿竴
	 * @return
	 * @throws Exception
	 */
	public int execute(Connection conn,String sql,Object[] paramList) throws Exception {
		if (sql == null || sql.trim().equals("")) {
			logger.info("parameter is valid!");
		}

		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = DBUtil.getPreparedStatement(conn, sql);
			setPreparedStatementParam(pstmt, paramList);
			if (pstmt == null) {
				return -1;
			}
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			logger.info(e.getMessage());
			throw new Exception(e);
		} finally {
			closeStatement(pstmt);
		}

		return result;
	}
	
	/**
	 * 鑾峰彇瀹炰綋绫诲瀷鐨勬柟娉曪紝type涓哄疄浣撶被绫诲瀷銆�
	 * @param type
	 * @param sql
	 * @param paramList
	 * @return
	 * @throws Exception
	 */
	public Object getObject(Class<?> type, String sql,Object[] paramList)  throws Exception {
		BeanInfo beanInfo = Introspector.getBeanInfo(type);
		Object obj = type.newInstance();
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		Map	map = getObject(sql, paramList);
		if(map != null){
			for (int i = 0; i< propertyDescriptors.length; i++) {
				PropertyDescriptor descriptor = propertyDescriptors[i];
				String propertyName = descriptor.getName();
				if (map != null && map.containsKey(propertyName)) {
					Object value = map.get(propertyName);
					Object[] args = new Object[1];
					args[0] = value;
					try{
						descriptor.getWriteMethod().invoke(obj, args);
					}catch(Exception e){
						logger.info("妫�娴嬩竴涓婽able鍒楋紝鍜屽疄浣撶被灞炴�э細" + propertyName + ""
								+ "鏄惁涓�鑷达紝骞朵笖鏄惁鏄�" + value.getClass() + "绫诲瀷");
						throw new Exception("妫�娴嬩竴涓婽able鍒楋紝鍜屽疄浣撶被灞炴�э細" + propertyName + ""
								+ "鏄惁涓�鑷达紝骞朵笖鏄惁鏄�" + value.getClass() + "绫诲瀷");
					}
				}
			}
		}else{
			obj = null;
		}
		return obj;
	}
	
	public List<Class<?>> getQueryList(Class<?> type, String sql,Object[] paramList)  throws Exception {
		BeanInfo beanInfo = Introspector.getBeanInfo(type);
		
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		List<Map<String,Object>> list = getQueryList(sql, paramList);
		List beanList = new ArrayList();
		
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Map<String, Object> map = (Map<String, Object>) iterator.next();
			Object obj = type.newInstance();
			for (int i = 0; i< propertyDescriptors.length; i++) {
				PropertyDescriptor descriptor = propertyDescriptors[i];
				String propertyName = descriptor.getName();
				if (map != null && map.containsKey(propertyName)) {
					Object value = map.get(propertyName);
					Object[] args = new Object[1];
					args[0] = value;
					try{
						descriptor.getWriteMethod().invoke(obj, args);
					}catch(Exception e){
						logger.info("妫�娴嬩竴涓婽able鍒楋紝鍜屽疄浣撶被灞炴�э細" + propertyName + ""
								+ "鏄惁涓�鑷达紝骞朵笖鏄惁鏄�" + value.getClass() + "绫诲瀷");
						throw new Exception("妫�娴嬩竴涓婽able鍒楋紝鍜屽疄浣撶被灞炴�э細" + propertyName + ""
								+ "鏄惁涓�鑷达紝骞朵笖鏄惁鏄�" + value.getClass() + "绫诲瀷");					}
				}
			}
			beanList.add(obj);
		}
		
		return beanList;
	}
	
	
	/**
	 * 灏嗘煡璇㈡暟鎹簱鑾峰緱鐨勭粨鏋滈泦杞崲涓篗ap瀵硅薄
	 * 
	 * @param sql锛氭煡璇�
	 * @return
	 */
	public List<Map<String, Object>> getQueryList(String sql) throws Exception {
		return getQueryList(sql,new Object[]{});
	}

	/**
	 * 灏嗘煡璇㈡暟鎹簱鑾峰緱鐨勭粨鏋滈泦杞崲涓篗ap瀵硅薄
	 * 
	 * @param sql锛氭煡璇�
	 * @param paramList锛氬弬鏁�
	 * @return
	 */
	public List<Map<String, Object>> getQueryList(String sql, Object[] paramList) throws Exception {
		if (sql == null || sql.trim().equals("")) {
			logger.info("parameter is valid!");
			return null;
		}

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Map<String, Object>> queryList = null;
		try {
			conn = getConnection();
			pstmt = DBUtil.getPreparedStatement(conn, sql);
			setPreparedStatementParam(pstmt, paramList);
			if (pstmt == null) {
				return null;
			}
			rs = getResultSet(pstmt);
			queryList = getQueryList(rs);
		} catch (RuntimeException e) {
			logger.info(e.getMessage());
			System.out.println("parameter is valid!");
			throw new Exception(e);
		} finally {
			closeResultSet(rs);
			closeStatement(pstmt);
			closeConn(conn);
		}
		return queryList;
	}

	/**
	 * 鍒嗛〉鏌ヨ
	 * @param sql 
	 * @param params 鏌ヨ鏉′欢鍙傛暟
	 * @param page  鍒嗛〉淇℃伅
	 * @return
	 */
	public Page getQueryPage(Class<?> type, String sql,Object[] params,Page page){
		int totalPages = 0;	//椤垫暟
		Long rows = 0l;//鏁版嵁璁板綍鏁�
		
		//鍒嗛〉宸ュ叿绫�
		List<Class<?>> list = null;
		Map countMap = null;
		try {
			list = this.getQueryList(type,sql
					+ " limit " 
					+ (page.getCurPage()-1)*page.getPageNumber() 
					+" , "+page.getPageNumber(),params);			
			countMap = this.getObject(" "
					+ "select count(*) c from ("+ sql +") as t ");
			rows =  (Long)countMap.get("c");
			//姹備綑鏁�
			if(rows % page.getPageNumber() ==0){
				totalPages = rows.intValue() / page.getPageNumber();
			}else{
				totalPages = rows.intValue() / page.getPageNumber() + 1;
			}
			
			page.setRows(rows.intValue());
			page.setDate(list);
			page.setTotalPage(totalPages);
		}catch(Exception e){
			e.printStackTrace();
		}
		return page;
	}
	
	/**
	 * 灏嗘煡璇㈡暟鎹簱鑾峰緱鐨勭粨鏋滈泦杞崲涓篗ap瀵硅薄
	 * @param sql锛氭煡璇�
	 * @return
	 */
	public Map<String, Object> getObject(String sql) throws Exception {
		return getObject(sql,new Object[]{});
	}
	
	/**
	 * 灏嗘煡璇㈡暟鎹簱鑾峰緱鐨勭粨鏋滈泦杞崲涓篗ap瀵硅薄
	 * 
	 * @param sql锛氭煡璇�
	 * @param paramList锛氬弬鏁�
	 * @return
	 */
	public Map<String, Object> getObject(String sql, Object[] paramList) throws Exception {
		if (sql == null || sql.trim().equals("")) {
			logger.info("parameter is valid!");
			return null;
		}

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Map map = new HashMap<String,Object>();
		try {
			conn = getConnection();
			pstmt = DBUtil.getPreparedStatement(conn, sql);
			setPreparedStatementParam(pstmt, paramList);
			if (pstmt == null) {
				return null;
			}
			rs = getResultSet(pstmt);
			List list = getQueryList(rs);
			if(list.isEmpty()){
				return null;
			}
			map = (HashMap) list.get(0);
		} catch (RuntimeException e) {
			logger.info(e.getMessage());
			logger.info("parameter is valid!");
			throw new Exception(e);
		} finally {
			closeResultSet(rs);
			closeStatement(pstmt);
			closeConn(conn);
		}
		return map;
	}

	
	private static PreparedStatement getPreparedStatement(Connection conn, String sql) throws Exception {
		if (conn == null || sql == null || sql.trim().equals("")) {
			return null;
		}
		PreparedStatement pstmt = conn.prepareStatement(sql.trim());
		return pstmt;
	}

	private void setPreparedStatementParam(PreparedStatement pstmt, Object[] paramList) throws Exception {
		if (pstmt == null || paramList == null) {
			return;
		}
		DateFormat df = DateFormat.getDateTimeInstance();
		for (int i = 0; i < paramList.length; i++) {
			//-
			if (paramList[i] instanceof Integer) {
				int paramValue = ((Integer) paramList[i]).intValue();
				pstmt.setInt(i + 1, paramValue);
			} else if (paramList[i] instanceof Float) {
				float paramValue = ((Float) paramList[i]).floatValue();
				pstmt.setFloat(i + 1, paramValue);
			} else if (paramList[i] instanceof Double) {
				double paramValue = ((Double) paramList[i]).doubleValue();
				pstmt.setDouble(i + 1, paramValue);
			} else if (paramList[i] instanceof Date) {
				pstmt.setString(i + 1, df.format((Date) paramList[i]));
			} else if (paramList[i] instanceof Long) {
				long paramValue = ((Long) paramList[i]).longValue();
				pstmt.setLong(i + 1, paramValue);
			} else if (paramList[i] instanceof String) {
				pstmt.setString(i + 1, (String) paramList[i]);
			}
			//= pstmt.setObject(i + 1, paramList[i]);
		}
		return;
	}

	/**
	 * 鑾峰緱鏁版嵁搴撴煡璇㈢粨鏋滈泦
	 * 
	 * @param pstmt
	 * @return
	 * @throws Exception
	 */
	private ResultSet getResultSet(PreparedStatement pstmt) throws Exception {
		if (pstmt == null) {
			return null;
		}
		ResultSet rs = pstmt.executeQuery();
		return rs;
	}

	/**
	 * @param rs
	 * @return
	 * @throws Exception
	 */
	private List<Map<String, Object>> getQueryList(ResultSet rs) throws Exception {
		if (rs == null) {
			return null;
		}
		ResultSetMetaData rsMetaData = rs.getMetaData();
		int columnCount = rsMetaData.getColumnCount();
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		while (rs.next()) {
			Map<String, Object> dataMap = new HashMap<String, Object>();
			for (int i = 0; i < columnCount; i++) {
				dataMap.put(rsMetaData.getColumnLabel(i + 1), rs.getObject(i + 1));
			}
			dataList.add(dataMap);
		}
		return dataList;
	}

	/**
	 * 鍏抽棴鏁版嵁搴�
	 * 
	 * @param conn
	 */
	private void closeConn(Connection conn) {
		if (conn == null) {
			return;
		}
		try {
			conn.close();
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}
	}

	/**
	 * 鍏抽棴
	 * 
	 * @param stmt
	 */
	private void closeStatement(Statement stmt) {
		if (stmt == null) {
			return;
		}
		try {
			stmt.close();
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}
	}

	/**
	 * 鍏抽棴
	 * 
	 * @param rs
	 */
	private void closeResultSet(ResultSet rs) {
		if (rs == null) {
			return;
		}
		try {
			rs.close();
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}
	}

	/**
	 * 鍙互閫夋嫨涓変釜涓嶅悓鐨勬暟鎹簱杩炴帴
	 * 
	 * @param JDBC
	 *            ,JNDI锛堜緷璧杦eb瀹瑰櫒 DBCP
	 * @return
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception {
		Connection conn = tl.get();
		if(conn ==null){
			conn = DBDataSource.getConnectionDbcp();
		}
		return conn;
	}
	/***********浜嬪姟澶勭悊鏂规硶************/
	/**
	 * 寮�鍚簨鍔�
	 */
	public static void beginTranscation() throws SQLException{
		Connection conn = tl.get();
		if(conn != null){
			logger.info("浜嬪姟宸茬粡寮�濮嬶紒");
			throw new SQLException("浜嬪姟宸茬粡寮�濮嬶紒");
		}
		conn = DBDataSource.getConnectionDbcp();
		conn.setAutoCommit(false);
		tl.set(conn);
	}
	/**
	 * 缁撴潫浜嬪姟
	 * @throws SQLException
	 */
	public static void endTranscation() throws SQLException{
		Connection conn = tl.get();
		if(conn == null){
			logger.info("褰撳墠娌℃湁浜嬪姟锛�");
			throw new SQLException("褰撳墠娌℃湁浜嬪姟锛�");
		}
		conn.commit();
	}
	/**
	 * 鍥炴粴
	 * @throws SQLException
	 */
	public static void rollback() throws SQLException{
		Connection conn = tl.get();
		if(conn == null){
			logger.info("褰撳墠娌℃湁浜嬪姟,涓嶈兘鍥炴粴锛�");
			throw new SQLException("褰撳墠娌℃湁浜嬪姟,涓嶈兘鍥炴粴锛�");
		}
		conn.rollback();
	}
	
	/**
	 * 浜嬪姟澶勭悊锛屽叧闂祫婧�
	 * @throws SQLException
	 */
	public static void closeConn() throws SQLException{
		Connection conn = tl.get();
		if(conn == null){
			logger.info("褰撳墠娌℃湁杩炴帴锛屼笉闇�瑕佸叧闂瑿onnection銆�");
			throw new SQLException("褰撳墠娌℃湁杩炴帴锛屼笉闇�瑕佸叧闂瑿onnection銆�");
		}
		conn.close();
		tl.remove();
	}
	
}
