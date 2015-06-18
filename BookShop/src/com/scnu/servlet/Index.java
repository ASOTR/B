package com.scnu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scnu.dao.BookManager;
import com.scnu.po.Book;

public class Index extends HttpServlet {
	private static int pageSize=8;//每页显示多少条记录
	private int pageNow;//希望显示第几页
	private int pageCount;//一共有多少页
	private int rowCount;//一共有多少条记录
	/**
	 * Constructor of the object.
	 */
	public Index() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		BookManager bm=new BookManager();
		ArrayList<Book> booklist=new ArrayList<Book>();
		if(request.getParameter("pageNow")!=null){
			pageNow=Integer.parseInt(request.getParameter("pageNow"));
		}else{
			pageNow=1;
		}
		try {
			booklist=bm.selectbooks();
			rowCount=booklist.size();//一共有多少条记录
			if(rowCount%pageSize==0){
				pageCount=rowCount/pageSize;
			}else{
				pageCount=rowCount/pageSize+1;//一共有多少页
			}
			int rowNow=(pageNow-1)*pageSize;//当前第一条记录是总记录的第几条
			int pagebefore=1;//上一页
			int pagenext=1;//下一页
			if(pageNow>1){
				pagebefore=pageNow-1;
			}else if(pageNow==1){
				pagebefore=-1;
			}
			if(pageNow<pageCount){//下一页
				pagenext=pageNow+1;
			}else if(pageNow==pageCount){
				pagenext=-1;
			}
			request.setAttribute("pagebefore", pagebefore);
			request.setAttribute("pagenext", pagenext);
			request.setAttribute("pageNowNew", pageNow);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("rowNow", rowNow);
			request.setAttribute("booklist", booklist);
			request.getRequestDispatcher("/ShopIndex.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
