package com.bookstore.util;



import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * 鍒嗛〉鏌ヨ鏍囩绫汇��
 * @author Administrator
 *
 */
public class PageTag extends SimpleTagSupport{
	
	//褰撳墠椤�
	private int curPage;
	//鎬婚〉鏁�
	private int totalPage;
	//鏁版嵁搴撹褰曟暟
	private int rows;
	//璁块棶璺緞
	private String url;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public void doTag() throws JspException, IOException {
	 	PageContext pageContext = (PageContext)getJspContext();
	 	JspWriter out = pageContext.getOut();
		out.print("");
		StringBuffer sb = new StringBuffer();
		sb.append("<p>");
		sb.append("鎬诲叡");
		//鎻掑叆rows鍔ㄦ�佹暟鎹�
		sb.append(rows);
		sb.append("璁板綍&nbsp;&nbsp;鍏�");
		//鎻掑叆totalPages鍔ㄦ�佹暟鎹�
		sb.append(totalPage);		
		sb.append("椤�&nbsp;");
		sb.append("&nbsp;褰撳墠鎵�鍦ㄩ〉");
		//鎻掑叆curPage鍔ㄦ�佹暟鎹�
		sb.append(curPage);
		sb.append("<a href=");
		//鎻掑叆url=1
		sb.append(url+"=1");
		sb.append(">棣栭〉</a>&nbsp;");
	  	sb.append("<a href=");
	  	//鎻掑叆涓婁竴椤祏rl=锛�
	  	int prePage = curPage <=1 ? curPage: curPage -1;
	  	int nextPage = curPage >= totalPage? totalPage:curPage +1;
	  	
	  	sb.append(url + "=" + prePage);
	  	sb.append(">涓婁竴椤�</a>&nbsp;");
	  	sb.append("<a href=");
	  	//鎻掑叆涓嬩竴椤祏rl=?
	  	sb.append(url + "=" + nextPage);
	  	sb.append(">涓嬩竴椤�</a>&nbsp;");
	   	sb.append("<a href=");
	   	//鎻掑叆鏈〉
	   	sb.append(url + "=" + totalPage);
	   	sb.append(">鏈〉</a>");
	   	sb.append("</p>");
	   	out.write(sb.toString());
		
	}
	
	
	
	
	
}
