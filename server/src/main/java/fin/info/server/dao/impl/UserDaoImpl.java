package fin.info.server.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;


import fin.info.server.dao.AbstractDao;
import fin.info.server.dao.UserDao;
import fin.info.server.jstl.tag.PageBean;
import fin.info.server.model.User;

@Repository("userDao")
@SuppressWarnings("unchecked")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

	
	
	@Override
	public void saveOrU(User user) {
		saveOrUpdate(user);
	}

	@Override
	public User findByName(String name) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("name", name));
		return (User)criteria.uniqueResult();
	}

	@Override
	public void saveUser(User user) {
		persist(user);
	}

	@Override
	public void deleteUser(String name) {
		Query query = getSession().createSQLQuery("delete from user where name = :name");
		query.setString("name", name);
		query.executeUpdate();
	}

	@Override
	public List<User> findAllUsers() {
		Criteria criteria = createEntityCriteria();
		return (List<User>)criteria.list();
	}

	@Override
	public User mergeUser(User user) {
		return merge(user);
	}

	
	@Override
	public PageBean<User> getUsersByQ(String q, int start, int num) {
		Criteria criteria = createEntityCriteria();
		if(StringUtils.isNotBlank(q))
			criteria.add(Restrictions.like("name", "%"+q+"%"));
//		criteria.createAlias("authorities", "authorities");
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		int totalRows = criteria.list().size();
		criteria.setFirstResult(totalRows - (start * num)<0 ? 0 : totalRows - (start * num));
		criteria.setMaxResults(num);
		return new PageBean<User>(num, totalRows, start, criteria.list());
	}
}
