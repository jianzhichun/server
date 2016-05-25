package fin.info.server.dao;

import org.hibernate.FlushMode;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import fin.info.server.AbstractBaseTestCase;


@Transactional(transactionManager="transactionManager")
public abstract class AbstractDaoTest extends AbstractBaseTestCase{
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session session;
	
	@Before
	public void openSession(){
		session = sessionFactory.openSession();
		session.setFlushMode(FlushMode.AUTO);
	}
	
	@After
	public void closeSession(){
		session.close();
	}
}
