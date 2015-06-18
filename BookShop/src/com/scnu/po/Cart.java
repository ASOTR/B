package com.scnu.po;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.scnu.dao.BookManager;
import com.scnu.dao.CartManager;

/**
 * 购物车类
 */
public class Cart {
	private int id;
	//图书集合
	private HashMap<Book,Integer> books;//图书列表(含数量)
	//总金额
	private double totalPrice;
	private String userNumber;//该购物车的用户
	public Cart(){
		books = new HashMap<Book, Integer>();
		totalPrice=0.0;
		userNumber=null;
	}
	public Cart(HashMap<Book, Integer> books, double totalPrice,String userNumber) {
		super();
		this.books = books;
		this.totalPrice = totalPrice;
		this.userNumber = userNumber;
	}
	
	public Cart(int id, HashMap<Book, Integer> books, double totalPrice,
			String userNumber) {
		super();
		this.id = id;
		this.books = books;
		this.totalPrice = totalPrice;
		this.userNumber = userNumber;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public HashMap<Book, Integer> getBooks() {
		return books;
	}
	public void setBooks(HashMap<Book, Integer> books) {
		this.books = books;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}
	//添加商品进购物车
	public boolean addBookInCart(Book book,int number){
		boolean flag=false;
		Set<Book> keys= books.keySet();//获得键的集合（Book）
		Iterator<Book> it = keys.iterator();//获取迭代器
		while (it.hasNext()) {
			Book bookin=it.next();
			if(bookin.hashCode()==book.hashCode()){
				flag=true;
			}
		}
		if(flag){
			int newnumber=books.get(book)+number;
			System.out.println("原购物车中已有该图书"+books.get(book)+"本");
			books.put(book,newnumber);
		}else{
			System.out.println("原购物车中没有该图书");
			books.put(book, number);
		}
		calTotalPrice();//重新计算总金额
		return true;
	}
	//删除商品
	public boolean removeBookFromCart(Book book){
		books.remove(book);
		calTotalPrice();//重新计算总金额
		return true;
	}
	//计算总金额
	public double calTotalPrice(){
		double sum=0.0;
		Set<Book> keys= books.keySet();//获得键的集合（Book）
		Iterator<Book> it = keys.iterator();//获取迭代器
		while (it.hasNext()) {
			Book book=it.next();
			sum +=book.getPrice()*books.get(book);
		}
		this.setTotalPrice(sum);//设置购物车总金额
		return this.getTotalPrice();
	}
//	public static void main(String[] args) throws Exception {
//		//Cart c=new Cart();
//		//Book book1=new Book(2,"疯狂Android讲义 第1版","电子工业出版社","2013-03-01","978-7-121-19485-6","Android编程基础书","image/andriod.png",68.5);
//		//Book book2=new Book(2,"疯狂Android讲义 第1版","电子工业出版社","2013-03-03","978-7-121-19485-6","Android编程基础书","image/andriod.png",68.5);
//		//c.addBookInCart(book1, 1);
//		//c.addBookInCart(book2, 1);
//		CartManager cm=new CartManager();
//		BookManager bm=new BookManager();
//		Cart c2=cm.selectCart("userNumber", "2012000");//得到用户的购物车
//		Book book=bm.selectbook("id", 2);//得到要购买的图书book
//		Book book21=bm.selectbook("id", 2);//得到要购买的图书book
//		c2.addBookInCart(book, 2);
//		c2.addBookInCart(book21, 3);
//		//购物商品集合
//		Set<Map.Entry<Book, Integer>> books= c2.getBooks().entrySet();
//		for(Map.Entry<Book, Integer> obj:books){
//			System.out.println(obj);
//		}
//		System.out.println("购物车总金额："+c2.getTotalPrice());
//	}
}
