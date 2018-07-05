package com.supermarket.search.service;

import com.supermarket.search.pojo.SearchResult;

public interface SearchService {
	
	SearchResult search(String queryStr ,int page,int rows)throws Exception;

}
