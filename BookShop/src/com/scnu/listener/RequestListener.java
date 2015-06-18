package com.scnu.listener;

import java.sql.ResultSet;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.scnu.po.User;
import com.scnu.util.DBHelper;

@WebListener
public class RequestListener implements ServletRequestListener{

	@Override
	public void requestDestroyed(ServletRequestEvent arg0) {
		// TODO Auto-generated method stub
	}
	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		HttpServletRequest request = (HttpServletRequest)sre.getServletRequest();
		HttpSession session=request.getSession();
		//获取sessionID
		String sessionId=session.getId();
		//获取访问的IP和正在访问的页面
		String ip=request.getRemoteAddr();
		String page=request.getRequestURI();
		String user=null;
		if(session.getAttribute("user")!=null){
			User MyUser=(User) session.getAttribute("user");
			user=MyUser.getUserName();
		}
		//未登录用户当游客处理
		user=(user==null)?"游客":user;
		DBHelper db = new DBHelper();
		String sql="select * from online_inf where session_id=?";
		Object args[]={sessionId};
		try {
			ResultSet rs=db.query(sql,args);
			if(rs.next()){
				//更新记录
				Object arg[]={sessionId,user,ip,page,System.currentTimeMillis(),sessionId};
				String sql1="update online_inf set session_id=?,user=?,ip=?,page=?,currentTime=? where session_id=?";
				db.updatedata(sql1, arg);
			}else{
				//插入该用户的在线信息
				String sql2="insert into online_inf (session_id,user,ip,page,currentTime)values(?,?,?,?,?)";
				Object arg[]={sessionId,user,ip,page,System.currentTimeMillis()};
				db.updatedata(sql2, arg);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
