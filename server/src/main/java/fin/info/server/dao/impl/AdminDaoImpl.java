package fin.info.server.dao.impl;


import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;


import fin.info.server.dao.AbstractDao;
import fin.info.server.dao.AdminDao;
import fin.info.server.model.Admin;

@Repository("adminDao")
public class AdminDaoImpl extends AbstractDao<String, Admin> implements AdminDao {


	@Override
	public Admin findByName(String name) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("name", name));
		return (Admin)criteria.uniqueResult();
	}

	@Override
	public void saveAdmin(Admin admin) {
		save(admin);
		
	}
}
