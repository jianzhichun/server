package fin.info.server.dao;

import java.util.List;

import fin.info.server.jstl.tag.PageBean;
import fin.info.server.model.User;

public interface UserDao {

	User findByName(String name);

	void saveUser(User user);
	
	User mergeUser(User user);
	
	void deleteUser(String name);
	
	void saveOrU(User user);
	
	List<User> findAllUsers();

	PageBean<User> getUsersByQ(String q, int start, int num);

}
