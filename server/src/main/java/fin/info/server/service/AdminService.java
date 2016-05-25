package fin.info.server.service;

import java.util.List;

import fin.info.server.jstl.tag.PageBean;
import fin.info.server.model.SearchItem;
import fin.info.server.model.User;

public interface AdminService {

	PageBean<User> getUsersByQ(String q, int start, int num);
	
	List<SearchItem> getSearchItemsByUserid(String useid);

}
