package fin.info.server.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings("unchecked")
public abstract class AbstractDao<PK extends Serializable, T> {

	private final Class<T> persistentClass;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	public AbstractDao(){
		this.persistentClass = (Class<T>) ((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
	}
	
	protected Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	public T getByKey(PK key) {
		return (T) getSession().get(persistentClass, key);
	}

	public void saveOrUpdate(T entity){
		getSession().saveOrUpdate(entity);
	}
	
	public void persist(T entity) {
		getSession().persist(entity);
	}
	
	public void save(T entity) {
		getSession().save(entity);
	}
	
	public T merge(T entity) {
		return (T) getSession().merge(entity);
	}
	
	public void delete(T entity) {
		getSession().delete(entity);
	}
	
	protected Criteria createEntityCriteria(){
		return getSession().createCriteria(persistentClass);
	}
	
	public List<T> find(String strhql, Object[] param) {
		Query query=this.getSession().createQuery(strhql);
		for (int i = 0; i < param.length; i++) {
			query.setParameter(i, param[i]);
		}
		return (List<T>)query.list();
		
	}
}
