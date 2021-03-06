package com.supermarket.search.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.supermarket.common.pojo.Item;
import com.supermarket.common.pojo.SearchResult;
import com.supermarket.search.dao.SearchDao;


/**
 * @author Admin
 * item search dao
 */
@Repository
public class SearchDaoImpl implements SearchDao {
	
	@Autowired
	@Qualifier("cloudSolrServer")
	private SolrServer solrServer;

	@Override
	public SearchResult search(SolrQuery query) throws Exception {
		SearchResult result=new SearchResult();
		List<Item> items=new ArrayList<>();
		result.setItemList(items);
		QueryResponse response = solrServer.query(query);
		SolrDocumentList solrDocumentList = response.getResults();
		result.setRecordCount(solrDocumentList.getNumFound());
		//highlighted
		Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
		for (SolrDocument solrDocument : solrDocumentList) {
			Item item = new Item();
			item.setId((String) solrDocument.get("id"));
			item.setSell_point((String) solrDocument.get("item_sell_point"));
			String title="";
			//get highlighted result
			List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
			if (list!=null && list.size()>0) {
				title=list.get(0);
			}else {
				title=(String) solrDocument.get("item_title");
			}
			item.setTitle(title);
			item.setPrice((long) solrDocument.get("item_price"));
			item.setImage((String) solrDocument.get("item_image"));
			item.setCategory_name((String) solrDocument.get("item_category_name"));
			item.setItem_desc((String) solrDocument.get("item_desc"));
			items.add(item);
		}
		return result;
	}

}
