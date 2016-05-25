package fin.info.server.dao;


import fin.info.server.model.Admin;

public interface AdminDao {

	Admin findByName(String name);
	
	void saveAdmin(Admin admin);

}
