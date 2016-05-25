package fin.info.server.service;

import fin.info.server.model.SuggestResult;

public interface SuggestService {

	SuggestResult suggest(String query);
	
}
