package fin.info.server.dao;

import java.util.List;


import fin.info.server.model.WsDocument;

public interface WsDocumentDao {

	WsDocument findById(String id);

	void saveWsDocument(WsDocument user);
	
	void deleteWsDocument(String id);
	
	List<WsDocument> findAllWsDocuments();

}
