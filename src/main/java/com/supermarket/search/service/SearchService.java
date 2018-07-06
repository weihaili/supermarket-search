package com.supermarket.search.service;

import com.supermarket.common.pojo.SearchResult;

public interface SearchService {
	
	SearchResult search(String queryStr ,int page,int rows)throws Exception;

}
