package com.bookstore.util;



import java.util.ArrayList;
import java.util.List;

/**
 * 鍒嗛〉绫伙紝灏佽鍒嗛〉鍩烘湰淇℃伅銆傞椤甸粯璁�1
 * @author Administrator
 * @version v1.0
 */
public class Page {
	//褰撳墠椤�
	private int curPage = 1;
	//鎬婚〉鏁�
	private int totalPage;
	//鏁版嵁搴撹褰曟暟
	private int rows;
	//姣忛〉鏁版嵁閲�
	private int pageNumber = 5;
	//瑕佸睍绀虹殑List鏁版嵁
	private List date = new ArrayList();
	
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public List getDate() {
		return date;
	}
	public void setDate(List date) {
		this.date = date;
	}
	
}
