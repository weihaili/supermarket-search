package com.supermarket.search.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supermarket.common.utils.ExceptionUtil;
import com.supermarket.common.utils.KklResult;
import com.supermarket.search.mapper.ItemMapper;
import com.supermarket.search.pojo.Item;
import com.supermarket.search.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemMapper itemMapper;
	
	@Autowired
	private SolrServer solrServer;

	@Override
	public KklResult importAllItem(){
		//query item list
		List<Item> list = itemMapper.getItemList();
		//write the list to index library
		try {
			for (Item item : list) {
				//create solrInputDocument object
				SolrInputDocument document = new SolrInputDocument();
				document.addField("id", item.getId());
				document.addField("item_title", item.getTitle());
				document.addField("item_sell_point", item.getSell_point());
				document.addField("item_price", item.getPrice());
				document.addField("item_image", item.getImage());
				document.addField("item_category_name", item.getCategory_name());
				String desc=item.getItem_desc()==null?"":item.getItem_desc();
				document.addField("item_desc", desc);
				//write the document to solr index library
				solrServer.add(document);
			}
			solrServer.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return KklResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return KklResult.ok();
	}

}
