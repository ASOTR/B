package com.scnu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scnu.dao.BookManager;
import com.scnu.dao.UserManager;
import com.scnu.po.Book;
import com.scnu.po.User;

public class BookServlet extends HttpServlet {
	private String action=null;
	private static int pageSize=6;//每页显示多少条记录
	private int pageNow;//希望显示第几页
	private int pageCount;//一共有多少页
	private int rowCount;//一共有多少条记录
	/**
	 * Constructor of the object.
	 */
	public BookServlet() {
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
		if(request.getParameter("action")!=null){
			this.action=request.getParameter("action");
			if(action.equals("show")){//展示图书列表
				showBookList(request,response);
			}
			if(action.equals("add")){//添加图书
				addBook(request,response);
			}
			if(action.equals("delete")){//删除图书
				deleteBook(request,response);
			}
			if(action.equals("change")){//修改图书信息
				changeBook(request,response);
			}
		}
	}
	public boolean showBookList(HttpServletRequest request, HttpServletResponse response){
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
			request.getRequestDispatcher("/admin/BookManager.jsp").forward(request, response);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
	
	public boolean addBook(HttpServletRequest request, HttpServletResponse response){
		String bookName=request.getParameter("bookName");
		String publishing_Company=request.getParameter("publishing_Company");
		String date="0001-01-01";
		if(!request.getParameter("date").equals("")){
			date=request.getParameter("date");
		}
		String ISBN=request.getParameter("ISBN");
		String book_Description=request.getParameter("book_Description");
		String imgUrl=request.getParameter("imgUrl");
		double price=0.0;
		if(!request.getParameter("price").equals("")){
			price=Double.parseDouble(request.getParameter("price"));
		}
		Book book=new Book(bookName,publishing_Company,date,ISBN,book_Description,imgUrl,price);
		BookManager bm=new BookManager();
		try {
			bm.addBook(book);
			showBookList(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}	
	public boolean deleteBook(HttpServletRequest request, HttpServletResponse response){
		BookManager bm=new BookManager();
		int id =Integer.parseInt(request.getParameter("id"));
		try {
			bm.delBook("id", id);
			showBookList(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	public boolean changeBook(HttpServletRequest request, HttpServletResponse response){
		BookManager bm=new BookManager();
		int id =Integer.parseInt(request.getParameter("changeid"));
		String bookName=request.getParameter("bookName");
		String publishing_Company=request.getParameter("publishing_Company");
		String date=request.getParameter("date");
		String ISBN=request.getParameter("ISBN");
		String book_Description=request.getParameter("book_Description");
		String imgUrl=request.getParameter("imgUrl");
		double price=Double.parseDouble(request.getParameter("price"));
		Book book=new Book(id,bookName,publishing_Company,date,ISBN,book_Description,imgUrl,price);//修改的图书信息
		try {
			System.out.println(id);
			bm.updateBook(book, id);
			showBookList(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
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
