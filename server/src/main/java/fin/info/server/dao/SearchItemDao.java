package fin.info.server.dao;

import java.util.List;

import fin.info.server.model.SearchItem;

public interface SearchItemDao {

	List<SearchItem> findByName(String name);
	
	List<SearchItem> findByUser(String userid);

	void saveSearchItem(SearchItem searchItem);
	
	void deleteSearchItem(String name);
	
	List<SearchItem> findAllSearchItems();

}
