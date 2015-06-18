package com.scnu.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.scnu.util.DBHelper;

@WebListener
public class OnlineListener implements ServletContextListener{
	// 超过10分钟没有访问本网站即认为用户已经离线
	public final int MAX_MILLIS=10*60*1000;
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//每5秒检查一次
		new javax.swing.Timer(1000*5, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DBHelper db=new DBHelper();
				String sql="select * from online_inf";
				Object args[]={};
				try {
					ResultSet rs=db.query(sql, args);
					ArrayList<String> beRemove=new ArrayList<String>();
					while(rs.next()){
						//如果距离上次访问时间超过指定时间
						if((System.currentTimeMillis()-rs.getLong(5))>MAX_MILLIS){
							//将需要被删除的session_id添加进来
							 beRemove.add(rs.getString(1));
						}
					}
					//有需要删除的记录
					if(beRemove.size()!=0){
					    //根据参数列表的大小生成in串
					    StringBuffer buffer=new StringBuffer();
					    for(int i=0;i<beRemove.size();i++){
					        buffer.append("?,");
					    }
					    buffer.deleteCharAt(buffer.length()-1);
						String sql2="delete from online_inf where session_id in ("
            +buffer.toString()+")";
						Object arg[] = new Object[beRemove.size()];
						for(int i=0;i<beRemove.size();i++){
							arg[i]=beRemove.get(i);
						}
						db.updatedata(sql2, arg);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}).start();
	}
}