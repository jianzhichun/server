package fin.info.server.service;

import java.util.Map;

import fin.info.server.model.AdvanceQuery;

public interface SearchService {

	Map<String, Object> defaultSearch(String q, int start, boolean needfacet);

	Map<String, Object> advanceSearch(AdvanceQuery advanceQuery, boolean needfacet);

}
