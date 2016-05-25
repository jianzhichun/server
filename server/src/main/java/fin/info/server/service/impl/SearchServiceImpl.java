package fin.info.server.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fin.info.server.constants.SearchConstants;
import fin.info.server.helper.solrUtils;
import fin.info.server.jstl.tag.PageBean;
import fin.info.server.model.AdvanceQuery;
import fin.info.server.model.WsDocument;
import fin.info.server.service.SearchService;

@Service("searchService")
public class SearchServiceImpl implements SearchService {

	@Autowired
	private SolrClient solrClient;

	@Override
	public Map<String, Object> defaultSearch(String q, int start, boolean needfacet) {
		try {
			Map<String, Object> maps = new HashMap<String, Object>();
			SolrQuery solrQuery = generateDefaultSolrQuery(q, start, SearchConstants.pageSize,q.matches(".*\\p{Alpha}.*"), needfacet);
			QueryResponse queryResponse = solrClient.query(solrQuery);
			generateResultMap(maps, start, queryResponse, needfacet);
			return maps;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private SolrQuery generateDefaultSolrQuery(String q, int start, int pageSize, boolean hasABC, boolean needfacet) {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setParam("q", q);
		solrQuery.setParam("qt", hasABC?"/selectpy":"/select");
		if(needfacet){
			solrQuery.setFacet(true);
			solrQuery.addFacetField("authorString");
			solrQuery.setFacetLimit(10);
			solrQuery.setFacetMissing(false);
			solrQuery.setFacetMinCount(1);
		}
		start = (start - 1) * pageSize;
		solrQuery.setStart(start);
		solrQuery.setRows(pageSize);
		setHighlight(solrQuery);
		if(StringUtils.isNotBlank(q)){
			solrQuery.addSort("score", SolrQuery.ORDER.desc);
		}else{
			solrQuery.addSort("time", SolrQuery.ORDER.desc);
		}
		return solrQuery;
	}
	
	private void generateResultMap(Map<String, Object> maps, int start, QueryResponse queryResponse, boolean needfacet) {

		List<WsDocument> docItems = new ArrayList<WsDocument>();

		int qtime = queryResponse.getQTime();// 查询花费时间

		SolrDocumentList solrDocumentList = queryResponse.getResults();// 获取查询结果集
		
		// 获取高亮内容 第一个Map的键是文档的ID，第二个Map的键是高亮显示的字段名
		Map<String, Map<String, List<String>>> highlightingMaps = queryResponse.getHighlighting();

		long totalRows = solrDocumentList.getNumFound();// 查询到的总记录数

		if (!solrDocumentList.isEmpty()) {

			Iterator<SolrDocument> it = solrDocumentList.iterator();
			while (it.hasNext()) {

				SolrDocument solrDocument = it.next();
				// 获取文档id
				String id = solrDocument.getFieldValue("id").toString();
				// 处理高亮
				Map<String, List<String>> highlightFieldMap = highlightingMaps.get(id);

				if (!highlightFieldMap.isEmpty()) {
					List<String> highlightTitle = highlightFieldMap.get("title");
					List<String> highlightDescription = highlightFieldMap.get("description");
					List<String> highlightContent = highlightFieldMap.get("content");
					if (highlightTitle != null && !highlightTitle.isEmpty()) {
						String title = highlightTitle.get(0);
						// 将文档结果集中的title设置为高亮后的title
						solrDocument.setField("title", title);
					}
					if (highlightDescription != null && !highlightDescription.isEmpty()) {
						String description = highlightDescription.get(0);
						// 将文档结果集中的description设置为高亮后的description
						solrDocument.setField("description", description);
					}
					if (highlightContent != null && !highlightContent.isEmpty()) {
						String content = highlightContent.get(0);
						// 将文档结果集中的content设置为高亮后的content
						solrDocument.setField("content", content);
					}
				}
				// 调用solrDocument转java bean
				docItems.add(solrUtils.doc2bean(WsDocument.class, solrDocument));
			}
		}
		maps.put("qtime", qtime);
		maps.put("pb", new PageBean<WsDocument>(SearchConstants.pageSize, totalRows, start, docItems));
		if(needfacet){
			List<FacetField> facets = queryResponse.getFacetFields();// 返回的facet列表  
	        for (FacetField facet : facets) {  
	            List<Count> counts = facet.getValues();  
	            maps.put("frs",counts);
	            break;
	        }  
		}
	}

	

	@Override
	public Map<String, Object> advanceSearch(AdvanceQuery advanceQuery, boolean needfacet) {
		try {
			Map<String, Object> maps = new HashMap<String, Object>();
			SolrQuery solrQuery = generateAdvanceSolrQuery(advanceQuery, SearchConstants.pageSize, needfacet);
			QueryResponse queryResponse = solrClient.query(solrQuery);
			generateResultMap(maps, advanceQuery.getStart(), queryResponse, needfacet);
			return maps;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private SolrQuery generateAdvanceSolrQuery(AdvanceQuery advanceQuery, int pageSize, boolean needfacet) {
		SolrQuery solrQuery = new SolrQuery();
		String timeRange = advanceQuery.getTimeRange();
		String q = advanceQuery.getQ()+(StringUtils.isNotBlank(timeRange)?" AND time:"+timeRange:"");
		solrQuery.setParam("qt", "/select");
		solrQuery.setParam("qf", advanceQuery.getQf());
		solrQuery.setParam("q", q);
		if(needfacet){
			solrQuery.setFacet(true);
			solrQuery.addFacetField("authorString");
			solrQuery.setFacetLimit(10);
			solrQuery.setFacetMissing(false);
			solrQuery.setFacetMinCount(1);
		}
		String order = advanceQuery.getOrder();
		String sortField = advanceQuery.getSortField();
		if(StringUtils.isNotBlank(sortField)){
			if("desc".equals(order)){
				solrQuery.addSort(sortField,SolrQuery.ORDER.desc);
			}else{
				solrQuery.addSort(sortField,SolrQuery.ORDER.asc);
			}
		}else if(StringUtils.isBlank(q)){
			solrQuery.addSort("time", SolrQuery.ORDER.desc);
		}else{
			solrQuery.addSort("score", SolrQuery.ORDER.desc);
		}
		int start = (advanceQuery.getStart() - 1) * pageSize;
		solrQuery.setStart(start);
		solrQuery.setRows(pageSize);
		setHighlight(solrQuery);
			
		return solrQuery;
	}



	private void setHighlight(SolrQuery solrQuery) {
		solrQuery.setHighlight(true);
		solrQuery.addHighlightField("title");
		solrQuery.addHighlightField("description");
		solrQuery.addHighlightField("author");
		solrQuery.setHighlightFragsize(200);
		solrQuery.setHighlightSimplePre("<font color='red'>");
		solrQuery.setHighlightSimplePost("</font>");
	}

}
