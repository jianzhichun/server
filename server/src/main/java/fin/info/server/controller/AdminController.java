package fin.info.server.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fin.info.server.annotation.AdminAuth;
import fin.info.server.model.SearchItem;
import fin.info.server.service.AdminService;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@AdminAuth
	@RequestMapping(value="/query", method= RequestMethod.GET)
    public String list(HttpServletRequest request, HttpServletResponse response,ModelMap map) {
		
		int start = Integer.parseInt(request.getParameter("start") == null ? "1"  
                : request.getParameter("start")); 
		int num = Integer.parseInt(request.getParameter("num") == null ? "15"  
                : request.getParameter("num")); 
		String q = request.getParameter("q");
		q = null==q?"":q;
		map.addAttribute("pb", adminService.getUsersByQ(q,start,num));
		return "admin";
    }
	
	@AdminAuth
	@RequestMapping(value="/showUser", method= RequestMethod.GET)
	@ResponseBody
    public List<SearchItem> lookUser(@RequestParam String userid, ModelMap map) {
		List<SearchItem> items = adminService.getSearchItemsByUserid(userid);
		for(SearchItem st:items){
			st.setUser(null);
		}
		return items;
    }
	
}
