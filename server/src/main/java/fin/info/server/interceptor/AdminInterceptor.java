package fin.info.server.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import fin.info.server.annotation.AdminAuth;
import fin.info.server.model.Admin;
import fin.info.server.service.LoginService;

/**
 * interceptor for authority
 * @author Administrator
 *
 */
public class AdminInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private LoginService loginService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(handler instanceof HandlerMethod){
			AdminAuth adminAuth = ((HandlerMethod) handler).getMethod().getAnnotation(AdminAuth.class); 
			if(null!=adminAuth){
				HttpSession httpSession = request.getSession();
				Admin ad = (Admin) httpSession.getAttribute("admin");
				if(ad==null)return false;
				else{
					Admin ad1 = loginService.login(ad);
					if(ad1==null)return false;
					httpSession.setAttribute("admin", ad1);
				}
			}
		}
		return true;
	}
	
	
}
