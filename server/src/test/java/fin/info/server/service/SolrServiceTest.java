package fin.info.server.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrRequest.METHOD;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.SpellCheckResponse;
import org.apache.solr.client.solrj.response.SpellCheckResponse.Collation;
import org.apache.solr.client.solrj.response.SpellCheckResponse.Correction;
import org.apache.solr.client.solrj.response.SpellCheckResponse.Suggestion;
import org.apache.solr.client.solrj.response.SuggesterResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import fin.info.server.AbstractBaseTestCase;
import fin.info.server.model.AdvanceQuery;

public class SolrServiceTest extends AbstractBaseTestCase {

	@Autowired
	private SolrClient solrClient;

//	@Test
	public void suggest() throws SolrServerException, IOException {
		SolrQuery sq = new SolrQuery();
		sq.setParam("qt", "/suggest");
		sq.setParam("q", "gao");
		QueryResponse resp = solrClient.query(sq);
		SpellCheckResponse spellCheckResponse = resp.getSpellCheckResponse();
//		SuggesterResponse sr = resp.getSuggesterResponse();
//		Map<String, List<String>> a = sr.getSuggestedTerms();
//		Map<String, List<Suggestion>> k = sr.getSuggestions();
		List<Suggestion> suggestionList = spellCheckResponse.getSuggestions();  
        for (Suggestion suggestion : suggestionList) {  
            System.out.println("Suggestions NumFound: " + suggestion.getNumFound());  
            System.out.println("Token: " + suggestion.getToken());  
            System.out.print("Suggested: ");  
            List<String> suggestedWordList = suggestion.getAlternatives();  
            for (String word : suggestedWordList) {  
                System.out.println(word + ", ");  
            }  
            System.out.println();  
        }  System.out.println();  
        Map<String, Suggestion> suggestedMap = spellCheckResponse.getSuggestionMap();  
        for (Map.Entry<String, Suggestion> entry : suggestedMap.entrySet()) {  
            System.out.println("suggestionName: " + entry.getKey());  
            Suggestion suggestion = entry.getValue();  
            System.out.println("NumFound: " + suggestion.getNumFound());  
            System.out.println("Token: " + suggestion.getToken());  
            System.out.print("suggested: ");  

            List<String> suggestedList = suggestion.getAlternatives();  
            for (String suggestedWord : suggestedList) {  
                System.out.print(suggestedWord + ", ");  
            }  
            System.out.println("\n\n");  
        }  

        
      

    // System.out.println("response = " + response);  
    // System.out.println(response.getStatus());  

}  

	

//	@Test
	public void query() throws SolrServerException, IOException {
		SolrQuery sq = new SolrQuery();
		sq.setParam("qt", "/select");
		sq.setParam("q", "*:*");
		QueryResponse resp = solrClient.query(sq);
		SolrDocumentList hits = resp.getResults();

		for (SolrDocument doc : hits) {
			for (String fieldName : doc.getFieldNames()) {
				System.out.println(fieldName + " : " + doc.getFieldValue(fieldName) + "  ");
			}
			System.out.println("------------------------Next Document--------------------------------");
		}
	}
	
	@Autowired
	private SearchService searchService;
	@Test
	public void advanceQuery() throws SolrServerException, IOException {
		Map<String, Object> map = searchService.defaultSearch("高", 1, true);
		int i = 0;
	}
}
