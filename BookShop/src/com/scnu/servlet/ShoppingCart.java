package com.scnu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scnu.dao.BookManager;
import com.scnu.dao.CartManager;
import com.scnu.po.Book;
import com.scnu.po.Cart;

public class ShoppingCart extends HttpServlet {
	private String action=null;
	/**
	 * Constructor of the object.
	 */
	public ShoppingCart() {
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
			if(action.equals("show")){//展示购物车
				//showCart(request,response);
			}
			if(action.equals("add")){//往购物车添加图书
				try {
					addBook(request,response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(action.equals("delete")){//从购物车中删除图书
				
			}
		}
	}
	public boolean addBook(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String userNumber=request.getParameter("userNumber");
		int bookNumber=1;
		if(request.getParameter("bookNumber")!=null){
			bookNumber=Integer.parseInt(request.getParameter("bookNumber"));
		}
		BookManager bm=new BookManager();
		CartManager cm=new CartManager();
		Cart c=cm.selectCart("userNumber", userNumber);//得到用户的购物车
		boolean userHaveCart=false;
		if(c.getUserNumber()!=null){//检查该用户是否第一次使用购物车
			userHaveCart=true;
		}
		Book book=new Book();
		if(request.getParameter("id")!=null){//根据id从数据库中选图书
			int id=Integer.parseInt(request.getParameter("id"));
			book=bm.selectbook("id", id);//得到要购买的图书book
		}else if(request.getParameter("ISBN")!=null){//根据ISBN选图书
			String ISBN=request.getParameter("ISBN");
			book=bm.selectbook("ISBN", ISBN);
		}
		System.out.println("用户账户"+userNumber);
		System.out.println("要购买的图书："+book.getBookName());
		System.out.println("原购物车总金额"+c.getTotalPrice());
		c.addBookInCart(book, bookNumber);//将图书添加进购物车
		//购物商品集合
		Set<Map.Entry<Book, Integer>> books= c.getBooks().entrySet();
		for(Map.Entry<Book, Integer> obj:books){
			System.out.println(obj);
		}
		BigDecimal bg = new BigDecimal(c.getTotalPrice());
		double TotalPrice = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		c.setTotalPrice(TotalPrice);
		System.out.println("添加后购物车总金额"+c.getTotalPrice());
		if(userHaveCart){//用户有购物车了
			System.out.println("更新购物车");
			cm.updateCart(c);//添加完更新数据库中的购物车
		}else{//数据库中没有购物车，就添加一个
			System.out.println("添加购物车");
			c.setUserNumber(userNumber);
			cm.addCart(c);
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
