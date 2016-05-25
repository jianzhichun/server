package fin.info.server.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.SpellCheckResponse;
import org.apache.solr.client.solrj.response.SpellCheckResponse.Suggestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fin.info.server.model.SuggestResult;
import fin.info.server.service.SuggestService;

@Service("suggestService")
public class SuggestServiceImpl implements SuggestService{

	@Autowired
	private SolrClient solrClient;
	
	@Override
	public SuggestResult suggest(String query) {
		SuggestResult result = new SuggestResult();
		try {
			result = generateResult(query);
			return result;
		} catch (Exception e) {
			return result;
		}
	}

	private SuggestResult generateResult(String query) throws SolrServerException, IOException {
		SuggestResult result = new SuggestResult();
		List<String> suggestions = new ArrayList<String>();
		SolrQuery sq = new SolrQuery();
		sq.setParam("qt", "/suggest");
		sq.setParam("q", query);
		QueryResponse resp =  solrClient.query(sq);
		SpellCheckResponse spellCheckResponse = resp.getSpellCheckResponse();
		List<Suggestion> suggestionList = spellCheckResponse.getSuggestions();  
        for (Suggestion suggestion : suggestionList) { 
            List<String> suggestedWordList = suggestion.getAlternatives();  
            for (String word : suggestedWordList) {  
            	suggestions.add(word);
            }  
        }
        result.setSuggestions(suggestions);
		return result;
	}
	
}
