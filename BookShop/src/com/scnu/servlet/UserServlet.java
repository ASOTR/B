package com.scnu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scnu.dao.UserManager;
import com.scnu.po.User;

public class UserServlet extends HttpServlet {
	private String action=null;
	private int authority=0;
	private static int pageSize=8;//每页显示多少条记录
	private int pageNow;//希望显示第几页
	private int pageCount;//一共有多少页
	private int rowCount;//一共有多少条记录
	/**
	 * Constructor of the object.
	 */
	public UserServlet() {
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
			this.authority=Integer.parseInt(request.getParameter("authority"));
			if(action.equals("show")){//展示用户列表
				showUserList(request,response);
			}
			if(action.equals("add")){//添加用户
				addUser(request,response);
			}
			if(action.equals("delete")){//删除用户
				deleteUser(request,response);
			}
			if(action.equals("change")){//修改用户信息
				changeUser(request,response);
			}
			
		}
		
		
	}
	public boolean showUserList(HttpServletRequest request, HttpServletResponse response){
		UserManager um=new UserManager();
		ArrayList<User> userlist=new ArrayList<User>();
		if(request.getParameter("pageNow")!=null){
			pageNow=Integer.parseInt(request.getParameter("pageNow"));
		}else{
			pageNow=1;
		}
		try {
			userlist=um.selectUsers(authority);
			rowCount=userlist.size();//一共有多少条记录
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
			request.setAttribute("userlist", userlist);
			if(authority==0){
				request.getRequestDispatcher("/admin/UserManager.jsp").forward(request, response);
			}else{
				request.getRequestDispatcher("/admin/SysUserManager.jsp").forward(request, response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
	public boolean addUser(HttpServletRequest request, HttpServletResponse response){
		String userName=request.getParameter("userName");
		String userNumber=request.getParameter("userNumber");
		String password=request.getParameter("password");
		User user=new User(userName,userNumber,password,authority);
		UserManager um=new UserManager();
		try {
			um.addUser(user);
			showUserList(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}	
	public boolean deleteUser(HttpServletRequest request, HttpServletResponse response){
		UserManager um=new UserManager();
		int id =Integer.parseInt(request.getParameter("id"));
		try {
			um.delUser("id", id, authority);
			showUserList(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}	
	public boolean changeUser(HttpServletRequest request, HttpServletResponse response){
		UserManager um=new UserManager();
		int id =Integer.parseInt(request.getParameter("changeid"));
		String userName=request.getParameter("userName");
		String userNumber=request.getParameter("userNumber");
		String password=request.getParameter("password");
		User user=new User(id,userName,userNumber,password,authority);//修改的用户信息
		try {
			System.out.println(id);
			um.updateUser(user, id);
			showUserList(request,response);
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
