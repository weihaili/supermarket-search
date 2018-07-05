package com.supermarket.search.dao;

import org.apache.solr.client.solrj.SolrQuery;

import com.supermarket.search.pojo.SearchResult;

public interface SearchDao {
	
	SearchResult search(SolrQuery query) throws Exception;

}
