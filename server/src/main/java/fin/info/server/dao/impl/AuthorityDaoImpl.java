package fin.info.server.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;


import fin.info.server.dao.AbstractDao;
import fin.info.server.dao.AuthorityDao;
import fin.info.server.model.Authority;

@Repository("authorityDao")
public class AuthorityDaoImpl extends AbstractDao<Integer, Authority> implements AuthorityDao {

	@Override
	public Authority findByName(String name) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("name", name));
		return (Authority)criteria.uniqueResult();
	}

	@Override
	public void saveAuthority(Authority authority) {
		persist(authority);
	}

	@Override
	public void deleteAuthority(String name) {
		Query query = getSession().createSQLQuery("delete from authority where name = :name");
		query.setString("name", name);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Authority> findAllAuthorities() {
		Criteria criteria = createEntityCriteria();
		return (List<Authority>)criteria.list();
	}
}
