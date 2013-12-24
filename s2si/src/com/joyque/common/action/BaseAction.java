package com.joyque.common.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class BaseAction extends ActionSupport{
	/**
	 * Ajax返回数据
	 * @param ajaxRS
	 * @throws IOException
	 */
	public void ajaxReturn(String ajaxRS) throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/plain");//设置输出为文字流    
		response.setCharacterEncoding("UTF-8");     
		PrintWriter out=response.getWriter();
		out.print(ajaxRS);
		out.flush();
		out.close();		
	}
	/**
	 * 获取当前用户Session信息
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map getSession(){
		ActionContext actionContext = ActionContext.getContext();
	    Map session = actionContext.getSession();
	    return session;
	}
}
