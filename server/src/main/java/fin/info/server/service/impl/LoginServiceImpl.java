package fin.info.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fin.info.server.dao.AdminDao;
import fin.info.server.model.Admin;
import fin.info.server.service.LoginService;

@Service("loginService")
@Transactional
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private AdminDao adminDao; 

	@Override
	public Admin login(Admin admin) {
		Admin ad = adminDao.findByName(admin.getName());
		if(null==ad)return null;
		if(ad.getPass().equals(admin.getPass()))return ad;
		return null;
	}

}
