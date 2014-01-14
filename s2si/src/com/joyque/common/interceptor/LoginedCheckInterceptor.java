package com.joyque.common.interceptor;

import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

import com.joyque.common.util.ActionManage;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
/** session过期、登录有效性及操作的权限验证拦截器 */
public class LoginedCheckInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = -7783406579812598510L;

	/** 拦截请求并进行登录有效性验证 */
	public String intercept(ActionInvocation ai) throws Exception {
		//取得请求的URL
		String url = ServletActionContext.getRequest().getRequestURL().toString();
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setHeader("Pragma","No-cache");          
		response.setHeader("Cache-Control","no-cache");   
		response.setHeader("Cache-Control", "no-store");   
		response.setDateHeader("Expires",0);
		String user = null;
		int find = 0;
		for(int i = 0; i < ActionManage.UserUrl.length; i ++)
		{
			if(url.indexOf(ActionManage.UserUrl[i] + ".action") != -1)
			{
				find = 1;
			}
			if(url.indexOf(ActionManage.AdminUrl[i] + ".action") != -1)
			{
				find = 2;
			}
		}
		
		//对登录与注销请求直接放行,不予拦截
		if (find == 0){
			return ai.invoke();
		}
		else{
			//验证Session是否过期
			if(!ServletActionContext.getRequest().isRequestedSessionIdValid()){
				//session过期,转向session过期提示页,最终跳转至登录页面
				if(find == 1)
				    return "tologin";
				else
					//return "adminLogin";
					return ai.invoke();
			}
			else{
				user = (String)ServletActionContext.getRequest().getSession().getAttribute("uid");
				//验证是否已经登录
				if (user==null){
					//尚未登录,跳转至登录页面
					if(find == 1)
					    return "tologin";
					else
						//return "adminLogin";
						return ai.invoke();
				}else{					
					return ai.invoke();
								
				}				
			}			
		}
	}
}

