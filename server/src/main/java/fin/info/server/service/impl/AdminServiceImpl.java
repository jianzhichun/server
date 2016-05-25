package fin.info.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fin.info.server.dao.SearchItemDao;
import fin.info.server.dao.UserDao;
import fin.info.server.jstl.tag.PageBean;
import fin.info.server.model.SearchItem;
import fin.info.server.model.User;
import fin.info.server.service.AdminService;

@Transactional
@Service("adminService")
public class AdminServiceImpl implements AdminService{

	@Autowired
	private UserDao userDao;
	@Autowired
	private SearchItemDao searchItemDao;
	
	@Override
	public PageBean<User> getUsersByQ(String q, int start, int num) {
		
		return userDao.getUsersByQ(q,start,num);
	}

	@Override
	public List<SearchItem> getSearchItemsByUserid(String userid) {
		
		return searchItemDao.findByUser(userid);
	}

}
