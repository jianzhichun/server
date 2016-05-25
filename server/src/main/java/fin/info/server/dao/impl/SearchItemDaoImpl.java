package fin.info.server.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import fin.info.server.dao.AbstractDao;
import fin.info.server.dao.SearchItemDao;
import fin.info.server.model.SearchItem;

@Repository("searchItemDao")
@SuppressWarnings("unchecked")
public class SearchItemDaoImpl extends AbstractDao<Integer, SearchItem> implements SearchItemDao {

	@Override
	public List<SearchItem> findByName(String name) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("name", name));
		return (List<SearchItem>) criteria.list();
	}

	@Override
	public void saveSearchItem(SearchItem searchItem) {
		persist(searchItem);
	}

	@Override
	public void deleteSearchItem(String name) {
		Query query = getSession().createSQLQuery("delete from searchitem where name = :name");
		query.setString("name", name);
		query.executeUpdate();
	}

	@Override
	public List<SearchItem> findAllSearchItems() {
		Criteria criteria = createEntityCriteria();
		return (List<SearchItem>) criteria.list();
	}

	@Override
	public List<SearchItem> findByUser(String userId) {
		return this.getSession().createSQLQuery("select * from searchitem where userid=\""+userId+"\" order by querytime desc")
				.addEntity(SearchItem.class).list();
	}
}
