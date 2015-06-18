package com.scnu.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.scnu.po.Book;
import com.scnu.util.DBHelper;
/**
 * 书籍管理工具类
 *
 */
public class BookManager {
	private DBHelper db=new DBHelper();
	/**
	 * 添加书本
	 * @param book
	 */
	public boolean addBook(Book book) throws Exception{
		String bookName=book.getBookName();
		String publishing_Company=book.getPublishing_Company();
		String date=book.getDate();
		String ISBN=book.getISBN();
		String book_Description=book.getBook_Description();
		String imgUrl=book.getImgUrl();
		double price=book.getPrice();
		Object arg[]={bookName,publishing_Company,date,ISBN,book_Description,imgUrl,price};
		String sql="insert into book (bookName,publishing_Company,date,ISBN,book_Description,imgUrl,price) values(?,?,?,?,?,?,?)";
		db.updatedata(sql, arg);
		return true;
	}
	/**
	 * 删除书本
	 * @param ISBN 根据列名删除书本
	 * @return
	 */
	public boolean delBook(String colName,Object value) throws Exception{
		String sql="delete from book where "+colName+" =?";
		Object arg[]={value};
		db.updatedata(sql, arg);
		return true;
	}
	/**
	 * 更新图书信息
	 * @param book 
	 * @param id 根据id号更改book信息
	 * @return
	 */
	public boolean updateBook(Book book,int id) throws Exception{
		String bookName=book.getBookName();
		String publishing_Company=book.getPublishing_Company();
		String date=book.getDate();
		String ISBN=book.getISBN();
		String book_Description=book.getBook_Description();
		String imgUrl=book.getImgUrl();
		double price=book.getPrice();
		Object arg[]={bookName,publishing_Company,date,ISBN,book_Description,imgUrl,price,id};
		String sql="update book set bookName=?,publishing_Company=?,date=?,ISBN=?,book_Description=?,imgUrl=?,price=? where id=?";
		db.updatedata(sql, arg);
		return true;
	}
	/**
	 * 根据列名和对应的值查询某一本book
	 * @param colName 列名
	 * @param value 对应的值
	 */
	public Book selectbook(String colName,Object value) throws Exception{
		Object arg[]={value};
		String sql="select * from book where "+colName+"=?";
		ResultSet rs=db.query(sql, arg);
		String bookName = null,publishing_Company = null,date = null,ISBN = null,book_Description = null,imgUrl = null;
		double price = 0;
		int id=0;
		while(rs.next()){
			id=rs.getInt("id");
			bookName=rs.getString("bookName");
			publishing_Company=rs.getString("publishing_Company");
			date=rs.getString("date");
			ISBN=rs.getString("ISBN");
			book_Description=rs.getString("book_Description");
			imgUrl=rs.getString("imgUrl");
			price=rs.getDouble("price");
		}
		Book book=new Book(id,bookName,publishing_Company,date,ISBN,book_Description,imgUrl,price);
		return book;
	}
	/**
	 * ArrayList返回所有的书籍
	 */
	public ArrayList<Book> selectbooks() throws Exception{
		ArrayList<Book> booklist=new ArrayList<Book>();
		String sql="select * from book";
		Object arg[]={};
		ResultSet rs=db.query(sql, arg);
		String bookName = null,publishing_Company = null,date = null,ISBN = null,book_Description = null,imgUrl = null;
		double price = 0;
		int id=0;
		while(rs.next()){
			id=rs.getInt("id");
			bookName=rs.getString("bookName");
			publishing_Company=rs.getString("publishing_Company");
			date=rs.getString("date");
			ISBN=rs.getString("ISBN");
			book_Description=rs.getString("book_Description");
			imgUrl=rs.getString("imgUrl");
			price=rs.getDouble("price");
			Book book=new Book(id,bookName,publishing_Company,date,ISBN,book_Description,imgUrl,price);
			booklist.add(book);
		}
		return booklist;
	}
//	public static void main(String[] args) throws Exception {
//		BookManager BM=new BookManager();
//		//Book book=new Book("疯狂Android讲义 第2版","电子工业出版社","2013-03-01","978-7-121-19485-6","Android编程基础书","img/andriod.png",68.8);
//		//Book book=new Book();
//		ArrayList<Book> booklist=new ArrayList<Book>();
//		booklist=BM.selectbooks();
//		System.out.println(booklist.get(0).getBookName());
//	}
}
