package fin.info.server.dao;

import java.util.HashSet;
import java.util.Set;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import fin.info.server.model.Authority;
import fin.info.server.model.SearchItem;
import fin.info.server.model.User;

@Rollback(false)
public class DaoTest extends AbstractDaoTest {
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private AuthorityDao authorityDao;
	@Autowired
	private SearchItemDao searchItemDao;
	@Autowired
	private WsDocumentDao wsDocumentDao;
	
//	@Test
	public void find(){
		userDao.findByName("haha");
		authorityDao.findByName("aa1");
		searchItemDao.findByName("aa");
		wsDocumentDao.findAllWsDocuments();
		int i = 0;
	}
	@Test
	public void save(){
//		saveUser();
//		saveSearchItems();
//		User u = userDao.findByName("haha");
//		int i = 0;
//		saveAuthority();
//		User u = new User();
//		u.setId("asdfsadf");
//		u.setName("aaaa");
//		u.setJoiningDate(new LocalDate());
//		userDao.saveUser(u);
		User u = new User();
		u.setName("hahaaaaa");
		u.setId("aaaaaaaaaaaaaaa");
		u.setJoiningDate(new LocalDate());
		userDao.saveUser(u);
	}
	private void saveAuthority() {
		Authority authority = new Authority();
		authority.setName("aa");
		authorityDao.saveAuthority(authority);
	}
	private void saveSearchItems() {
		SearchItem searchItem = null;
		User u = userDao.findByName("haha");
		for(int i=0;i<10;i++){
			searchItem = new SearchItem();
			searchItem.setName("aa");
			searchItem.setQuerytime(new LocalDateTime());
			searchItem.setUser(u);
			searchItemDao.saveSearchItem(searchItem);
		}
	}
	private void saveUser() {
		User user = new User();
		user.setName("haha");
		user.setJoiningDate(new LocalDate());
		user.setAuthorities(getAuthorities(user));
		user.setSearchItems(getSearchItems());
		userDao.saveUser(user);
		userDao.saveUser(user);
	}
	private Set<SearchItem> getSearchItems() {
		Set<SearchItem> ss = new HashSet<SearchItem>();
		SearchItem searchItem = null;
		for(int i=0;i<10;i++){
			searchItem = new SearchItem();
			searchItem.setName("aa");
			searchItem.setQuerytime(new LocalDateTime());
			ss.add(searchItem);
		}
		return ss;
	}
	private Set<Authority> getAuthorities(User user) {
		Set<Authority> ss = new HashSet<Authority>();
		Authority authority = null;
		for(int i=0;i<10;i++){
			authority = new Authority();
			authority.setName("aa"+i);
			authority.getUsers().add(user);
			authorityDao.saveAuthority(authority);
			ss.add(authority);
		}
		return ss;
	}
	@Test
	public void delete(){
		
	}
	@Test
	public void list(){
		
	}
}
