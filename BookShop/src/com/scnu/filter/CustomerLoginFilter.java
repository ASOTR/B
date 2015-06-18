package com.scnu.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CustomerLoginFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		//实现对 url为 /admin/*  和/adminIndex.jsp的登录认证
		//设置request编码用的字符集
		request.setCharacterEncoding("utf-8");	//①
		HttpServletRequest requ = (HttpServletRequest)request;
		HttpServletResponse resp= (HttpServletResponse)response;
		HttpSession session = requ.getSession(true);
		//获取客户请求的页面
		String requestPath = requ.getServletPath();
		//如果session范围的user为null，即表明没有登录
		//且用户请求的不是登录页面
		if(session.getAttribute("user") == null
			&&!requestPath.endsWith("/customerLogin.jsp")
			&&session.getAttribute("isLogin")==null){ //重定向到登录页面
			resp.sendRedirect(requ.getContextPath()+"/customerLogin.jsp");
		}//"放行"请求
		else{chain.doFilter(request, response);}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
