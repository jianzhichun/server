package fin.info.server.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;


import fin.info.server.dao.AbstractDao;
import fin.info.server.dao.WsDocumentDao;
import fin.info.server.model.WsDocument;

@Repository("wsDocumentDao")
public class WsDocumentDaoImpl extends AbstractDao<String, WsDocument> implements WsDocumentDao {

	@Override
	public WsDocument findById(String id) {
		return getByKey(id);
	}

	@Override
	public void saveWsDocument(WsDocument user) {
		persist(user);
	}

	@Override
	public void deleteWsDocument(String id) {
		Query query = getSession().createSQLQuery("delete from wstable where id = :id");
		query.setString("id", id);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WsDocument> findAllWsDocuments() {
		Criteria criteria = createEntityCriteria();
		return (List<WsDocument>)criteria.list();
	}
}
