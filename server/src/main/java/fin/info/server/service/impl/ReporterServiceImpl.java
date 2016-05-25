package fin.info.server.service.impl;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fin.info.server.dao.SearchItemDao;
import fin.info.server.model.SearchItem;
import fin.info.server.model.User;
import fin.info.server.service.ReporterService;

@Transactional
@Service("reporterService")
public class ReporterServiceImpl implements ReporterService {

	@Autowired
	private SearchItemDao searchItemDao;
	
	@Override
	public void recordSearchItem(String q, User user) {
		SearchItem searchItem = new SearchItem();
		searchItem.setName(q);
		searchItem.setQuerytime(new LocalDateTime());
		searchItem.setUser(user);
		searchItemDao.saveSearchItem(searchItem);
	}

}
