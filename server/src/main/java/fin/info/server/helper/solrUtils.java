package fin.info.server.helper;

import org.apache.solr.client.solrj.beans.DocumentObjectBinder;
import org.apache.solr.common.SolrDocument;


public class solrUtils {

	public static <T> T doc2bean(Class<T> clazz, SolrDocument solrDocument) {
		DocumentObjectBinder binder = new DocumentObjectBinder();
		return binder.getBean(clazz, solrDocument);
	}

}
