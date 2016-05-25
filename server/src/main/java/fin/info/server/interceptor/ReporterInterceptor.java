package fin.info.server.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import fin.info.server.annotation.Reporter;
import fin.info.server.model.User;
import fin.info.server.service.ReporterService;

/**
 * interceptor for reporter
 * @author Administrator
 *
 */
public class ReporterInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private ReporterService reporterService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		if(handler instanceof HandlerMethod){
			Reporter reporter = ((HandlerMethod) handler).getMethod().getAnnotation(Reporter.class); 
			if(null!=reporter){
				String q = request.getParameter("q");
				HttpSession httpSession = request.getSession();
				User user = (User) httpSession.getAttribute("user");
				if(null==user){
					return true;
				}
				reporterService.recordSearchItem(q, user);
			}
		}
		return true;
	}
	
	
}
