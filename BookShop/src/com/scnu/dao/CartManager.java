package com.scnu.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.scnu.po.Book;
import com.scnu.po.Cart;
import com.scnu.util.DBHelper;
/**
 * @author Liu
 *	购物车数据库操作类
 */
public class CartManager {
	private DBHelper db=new DBHelper();
	/**
	 * 添加购物车
	 * @throws Exception 
	 */
	public boolean addCart(Cart c) throws Exception{
		String userNumber = c.getUserNumber();
		double totalPrice=c.getTotalPrice();
		HashMap<Book,Integer> books=c.getBooks();//要将图书集合转化成 图书id列表和数量列表才好保存到数据库
		String book_id="";
		String book_Number="";
		Set<Book> keys= books.keySet();//获得键的集合（Book）
		Iterator<Book> it = keys.iterator();//获取迭代器
		while (it.hasNext()) {//得到book_id和book_Number
			Book book=it.next();
			if(it.hasNext()){
				book_id =book_id+book.getId()+"|";
				book_Number=book_Number+books.get(book)+"|";
			}else{
				book_id =book_id+book.getId();
				book_Number=book_Number+books.get(book);
			}
		}
		Object arg[]={userNumber,book_id,book_Number,totalPrice};
		String sql="insert into cart (userNumber,book_id,book_Number,totalPrice) values(?,?,?,?)";
		db.updatedata(sql, arg);
		return true;
	}
	/**
	 * 删除购物车
	 * @throws Exception 
	 */
	public boolean delCart(String colName,Object value) throws Exception{
		String sql="delete from cart where "+colName+" =?";
		Object arg[]={value};
		db.updatedata(sql, arg);
		return true;
	}
	/**
	 * 更新购物车信息
	 * @throws Exception 
	 */
	public boolean updateCart(Cart c) throws Exception{
		String userNumber = c.getUserNumber();
		double totalPrice=c.getTotalPrice();
		HashMap<Book,Integer> books=c.getBooks();//要将图书集合转化成 图书id列表和数量列表才好保存到数据库
		String book_id="";
		String book_Number="";
		Set<Book> keys= books.keySet();//获得键的集合（Book）
		Iterator<Book> it = keys.iterator();//获取迭代器
		while (it.hasNext()) {//得到book_id和book_Number
			Book book=it.next();
			if(it.hasNext()){
				book_id =book_id+book.getId()+"|";
				book_Number=book_Number+books.get(book)+"|";
			}else{
				book_id =book_id+book.getId();
				book_Number=book_Number+books.get(book);
			}
		}
		Object arg[]={userNumber,book_id,book_Number,totalPrice,userNumber};
		String sql="update cart set userNumber=?,book_id=?,book_Number=?,totalPrice=? where userNumber=?";
		db.updatedata(sql, arg);
		return true;
	}
	/**
	 * @param colName 列名（userNumber）
	 * @param value 值
	 * @return cart
	 * @throws Exception 
	 */
	public Cart selectCart(String colName,Object value) throws Exception{
		Object arg[]={value};
		String sql="select * from cart where "+colName+"=?";
		ResultSet rs=db.query(sql, arg);
		int id=0;String userNumber=null;double totalPrice=0.0;
		HashMap<Book,Integer> books = new HashMap<Book, Integer>();
		while(rs.next()){
			id=rs.getInt("id");
			userNumber=rs.getString("userNumber");
			totalPrice=rs.getDouble("totalPrice");
			String book_id=rs.getString("book_id");
			String book_Number=rs.getString("book_Number");
			String bookids[]=book_id.split("\\|");
			String booknumbers[]=book_Number.split("\\|");
			BookManager bm=new BookManager();
			for (int j = 0; j < bookids.length; j++) {
				if(!booknumbers[j].equals("")){
					Book book=bm.selectbook("id", bookids[j]);
					books.put(book, Integer.parseInt(booknumbers[j]));
					System.out.println("购物车图书id:"+bookids[j]+"购物车图书数量:"+ Integer.parseInt(booknumbers[j]));
					
				}
			}
		}
		Cart c=new Cart(id,books,totalPrice,userNumber);
		return c;
	}
	/**
	 * @return 表中所有的购物车
	 * @throws Exception 
	 */
	public ArrayList<Cart> selectCarts() throws Exception{
		ArrayList<Cart> cartlist=new ArrayList<Cart>();
		String sql="select * from cart";
		Object arg[]={};
		ResultSet rs=db.query(sql, arg);
		int id=0;String userNumber=null;double totalPrice=0.0;
		HashMap<Book,Integer> books = new HashMap<Book, Integer>();
		while(rs.next()){
			id=rs.getInt("id");
			userNumber=rs.getString("userNumber");
			totalPrice=rs.getDouble("totalPrice");
			String book_id=rs.getString("book_id");
			String book_Number=rs.getString("book_Number");
			String bookids[]=book_id.split("|");
			String booknumbers[]=book_Number.split("|");
			BookManager bm=new BookManager();
			for (int j = 0; j < bookids.length; j++) {
				Book book=bm.selectbook("id", bookids[j]);
				books.put(book, Integer.parseInt(booknumbers[j]));
			}
			Cart c=new Cart(id,books,totalPrice,userNumber);
			cartlist.add(c);
		}
		return cartlist;
	}
	
//	public static void main(String[] args) throws Exception {
//		CartManager cm=new CartManager();
//		String userNumber="2012000";
//		Cart c=cm.selectCart("userNumber", userNumber);
//		//购物商品集合
//		Set<Map.Entry<Book, Integer>> books= c.getBooks().entrySet();
//		for(Map.Entry<Book, Integer> obj:books){
//			System.out.println(obj);
//		}
//		HashMap<Book,Integer> books=c.getBooks();
//		Set<Book> keys= books.keySet();//获得键的集合（Book）
//		Iterator<Book> it = keys.iterator();//获取迭代器
//		while (it.hasNext()) {
//			Book book=it.next();
//			int bookNumbers=books.get(book);
//			System.out.println(book.getId()+book.getBookName()+book.getBook_Description()+bookNumbers);
//		}
//		System.out.println("购物车总金额："+c.getTotalPrice());
//	}
	
}
