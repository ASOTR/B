package com.scnu.po;

import java.util.HashMap;
/**
 * 定单[图书列表(含数量)，总价，下单日期，结帐日期]
 */
public class OrderForm {
	private String userNumber;//用户
	private String order_date;//下单日期
	private String Closing_date;//结帐日期
	private HashMap<Book,Integer> books;//图书列表(含数量
	private double totalPrice;//总价
	public String getUserNumber() {
		return userNumber;
	}
	public void setUserName(String userNumber) {
		this.userNumber = userNumber;
	}
	public String getOrder_date() {
		return order_date;
	}
	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}
	public String getClosing_date() {
		return Closing_date;
	}
	public void setClosing_date(String closing_date) {
		Closing_date = closing_date;
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
	
}
