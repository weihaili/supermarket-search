package com.supermarket.search;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Before;
import org.junit.Test;

/**   
 * @ClassName: SolrCloudTest   
 * @Description: test solrCloud CRUD   
 * @author: KKL 
 * @date: 2018年7月16日 上午11:27:54      
 */  
public class SolrCloudTest {
	/**
	 * step:
	 * 1. create connection (connect to the solrCloud )
	 * 2. create document object (SolrInputDocument instance)
	 * 3. add field to document
	 * 4. add the document to collection(solrCloud index library)
	 * 5. commit
	 */
	private String zkHost="192.168.117.128:2181,192.168.117.128:2182,192.168.117.128:2183";
	private CloudSolrServer solrServer;
	private SolrInputDocument document;
	
	@Before
	public void init() {
		//parameter zkHost is zookeeper address list,use comma(,) as separator
		solrServer=new CloudSolrServer(zkHost);
		//must set default collection
		solrServer.setDefaultCollection("collection2");
		
		document=new SolrInputDocument();
	}
	
	@Test
	public void add() {
		document.addField("id", "test002 add");
		document.addField("item_title", "test second commodity");
		try {
			solrServer.add(document);
			solrServer.commit();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void deleteById() {
		try {
			solrServer.deleteById("test002 add");
			solrServer.commit();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void deleteByQuery() {
		try {
			solrServer.deleteByQuery("*:*");
			solrServer.commit();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
