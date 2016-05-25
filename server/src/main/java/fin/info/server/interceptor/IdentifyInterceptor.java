package fin.info.server.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import fin.info.server.model.User;
import fin.info.server.service.IdentifyService;

/**
 * interceptor for authority
 * 
 * @author Administrator
 *
 */
public class IdentifyInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private IdentifyService identifyService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String name = request.getLocalAddr();
		HttpSession httpSession = request.getSession();
		User user = (User) httpSession.getAttribute("user");
		if (null == user) {
			user = identifyService.identifyUser(name);
			httpSession.setAttribute("user", user);
		}
		return true;
	}

}
