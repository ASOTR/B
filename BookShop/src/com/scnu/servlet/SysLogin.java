package com.scnu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.scnu.dao.CheckLogin;
import com.scnu.dao.UserManager;
import com.scnu.po.User;

public class SysLogin extends HttpServlet {
	private String action;
	/**
	 * Constructor of the object.
	 */
	public SysLogin() {
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
		PrintWriter out=response.getWriter();
		String userNumber=request.getParameter("user");
		String password=request.getParameter("password");
		action=request.getParameter("action");
		System.out.println("账号："+userNumber);
		System.out.println("密码："+password);
		CheckLogin CL=new CheckLogin();
		try {
			if(action.equals("admin")){
				String flag=CL.checklogin(userNumber, password);
				if(flag=="1"){//登录成功
					System.out.println("登录成功");
					HttpSession session=request.getSession();
					session.setAttribute("isLogin","1");
					UserManager um=new UserManager();
					User user = um.selectUser("userNumber", userNumber, 1);
					session.setAttribute("user", (User)user);
				}
				out.write(flag);
			}else{
				String flag=CL.checkCustomerlogin(userNumber, password);
				if(flag=="1"){//登录成功
					System.out.println("登录成功");
					HttpSession session=request.getSession();
					session.setAttribute("isLogin","1");
					UserManager um=new UserManager();
					User user = um.selectUser("userNumber", userNumber, 0);
					session.setAttribute("user", (User)user);
				}
				out.write(flag);
			}

			
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
