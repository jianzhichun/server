package fin.info.server.service.impl;

import java.util.UUID;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fin.info.server.dao.UserDao;
import fin.info.server.model.User;
import fin.info.server.service.IdentifyService;

@Service("identifyService")
public class IdentifyServiceImpl implements IdentifyService {
	@Autowired
	private UserDao userDao;

	@Override
	@Transactional
	public User identifyUser(String name) {

		User user = userDao.findByName(name);
		if(null == user){
			user = new User();
			user.setName(name);
			user.setId(UUID.randomUUID().toString());
			user.setJoiningDate(new LocalDate());
			userDao.saveUser(user);
		}
		return user;
	}

}
