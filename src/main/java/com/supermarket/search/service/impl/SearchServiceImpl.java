package com.supermarket.search.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supermarket.common.pojo.SearchResult;
import com.supermarket.search.dao.SearchDao;
import com.supermarket.search.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService {
	
	@Autowired
	private SearchDao searchDao;

	@Override
	public SearchResult search(String queryStr, int page, int rows)throws Exception {
		SearchResult result = null;
		SolrQuery query = new SolrQuery();
		if (StringUtils.isBlank(queryStr)) {
			//set default query condition
		}
		query.setQuery(queryStr);
		query.setStart((page-1)*rows);
		query.setRows(rows);
		query.set("df", "item_keywords");
		query.setHighlight(true);
		query.addHighlightField("item_title");
		query.setHighlightSimplePre("<em stype=\"color:red\">");
		query.setHighlightSimplePost("</em>");
		result = searchDao.search(query);
		long recordCount = result.getRecordCount();
		long pageCount=(long) Math.ceil(recordCount/rows);
		result.setPageCount(pageCount);
		result.setCurrentPage(page);
		return result;
	}

}
