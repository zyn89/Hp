package com.joyque.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.Action;






public class DispatchAction implements  Action, ServletResponseAware,ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String url;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}


	//需要访问的HttpServletResponse对象
	private HttpServletResponse response;
	private HttpServletRequest request;
	
	public HttpServletResponse getRespone() {
	   return response;
	}
	public HttpServletRequest getRequest() {
		   return request;
	}

	
	public void setRespone(HttpServletResponse response) {
	   this.response = response;
	}
	public void setRequest(HttpServletRequest request) {
		   this.request = request;
	}
	
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
		
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		 this.request = request;
	}
	

	@Override
	public String execute() throws Exception {
		
			request.getRequestDispatcher("/WEB-INF/jsp/"+url).forward(request, response);
			
			return null;
		
	}

	

}
