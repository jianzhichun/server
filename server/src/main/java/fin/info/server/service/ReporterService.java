package fin.info.server.service;

import fin.info.server.model.User;

public interface ReporterService {
	
	void recordSearchItem(String q, User user);

}
