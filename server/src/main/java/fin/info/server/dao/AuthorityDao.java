package fin.info.server.dao;

import java.util.List;

import fin.info.server.model.Authority;

public interface AuthorityDao {

	Authority findByName(String name);

	void saveAuthority(Authority user);
	
	void deleteAuthority(String name);
	
	List<Authority> findAllAuthorities();

}
