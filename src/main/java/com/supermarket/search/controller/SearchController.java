package com.supermarket.search.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.supermarket.common.pojo.SearchResult;
import com.supermarket.common.utils.ExceptionUtil;
import com.supermarket.common.utils.KklResult;
import com.supermarket.search.service.SearchService;

/**
 * @author Admin
 * item search controller
 */
@Controller
public class SearchController {
	
	@Autowired
	private SearchService service;

	
	/** /search/query?q={查询条件}&page={page}&rows={rows}
	 * @return
	 */
	@RequestMapping(value="/query",method=RequestMethod.GET)
	@ResponseBody
	public KklResult search(@RequestParam("q")String queryStr,@RequestParam(defaultValue="1")Integer page,@RequestParam(defaultValue="60")Integer rows) {
		if (StringUtils.isBlank(queryStr)) {
			return KklResult.build(400, "the query condition must at least have a value");
		}
		SearchResult searchResult=null;
		try {
			//solve get request garbled
			queryStr=new String(queryStr.getBytes("iso-8859-1"),"UTF-8");
			searchResult=service.search(queryStr, page, rows);
		} catch (Exception e) {
			e.printStackTrace();
			KklResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return KklResult.ok(searchResult);
	}
}
