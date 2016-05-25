package fin.info.server.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fin.info.server.model.Admin;
import fin.info.server.service.LoginService;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value = "/admin", method= RequestMethod.GET)
    public String loginAdmin(@ModelAttribute Admin admin, HttpSession httpSession, ModelMap map) {
		Admin ad = (Admin) httpSession.getAttribute("admin");
		if(null != ad) return "admin";
		ad = loginService.login(admin);
		if(null == ad){
			map.addAttribute("error","U CANNOT ACCESS ADMIN!");
			return "error";
		}
		map.addAttribute("admin",ad);
		httpSession.setAttribute("admin", ad);
		return "admin";
    }
	
}
