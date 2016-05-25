package fin.info.server.interceptor;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import fin.info.server.annotation.Auth;
import fin.info.server.model.Authority;
import fin.info.server.model.User;

/**
 * interceptor for authority
 * @author Administrator
 *
 */
public class AuthorityInterceptor extends HandlerInterceptorAdapter {
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(handler instanceof HandlerMethod){
			Auth auth = ((HandlerMethod) handler).getMethod().getAnnotation(Auth.class); 
			if(null!=auth){
				HttpSession httpSession = request.getSession();
				User user = (User) httpSession.getAttribute("user");
				if(null==user){
					return false;
				}
				if(StringUtils.isNotBlank(auth.value())){
					Set<Authority> authorities = user.getAuthorities();
					if(!authorities.contains(auth.value())){
						return false;
					}
				}
			}
		}
		return true;
	}
	
	
}
