package com.scnu.po;
/**
 *图书数据[书名，出版社，出版日期，ISBN，图书描述，图书封面，单价]
 */
public class Book {
	private int id;
	private String bookName;
	private String publishing_Company;
	private String date;
	private String ISBN;
	private String book_Description;
	private String imgUrl;
	private double price;
	
	public Book(){};
	public Book(String bookName, String publishing_Company, String date,
			String iSBN, String book_Description, String imgUrl, double price) {
		super();
		this.bookName = bookName;
		this.publishing_Company = publishing_Company;
		this.date = date;
		ISBN = iSBN;
		this.book_Description = book_Description;
		this.imgUrl = imgUrl;
		this.price = price;
	}
	public Book(int id,String bookName, String publishing_Company, String date,
			String iSBN, String book_Description, String imgUrl, double price) {
		super();
		this.id = id;
		this.bookName = bookName;
		this.publishing_Company = publishing_Company;
		this.date = date;
		ISBN = iSBN;
		this.book_Description = book_Description;
		this.imgUrl = imgUrl;
		this.price = price;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getPublishing_Company() {
		return publishing_Company;
	}
	public void setPublishing_Company(String publishing_Company) {
		this.publishing_Company = publishing_Company;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getBook_Description() {
		return book_Description;
	}
	public void setBook_Description(String book_Description) {
		this.book_Description = book_Description;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String toString(){
		return "商品ID:"+this.getId()+" 商品名称 ："+this.getBookName();
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.getId()+this.getBookName().hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(this==obj){
			return true;
		}
		if(obj instanceof Book){
			Book book=(Book)obj;
			if(this.hashCode()==book.hashCode()){
				return true;
			}else {
				return false;
			}
		}else{
			return false;
		}
	}
	
}
