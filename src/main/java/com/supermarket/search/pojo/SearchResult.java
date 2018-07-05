package com.supermarket.search.pojo;

import java.io.Serializable;
import java.util.List;

public class SearchResult implements Serializable {

	/**
	 * need net transmit
	 */
	private static final long serialVersionUID = 8833693046052428493L;
	
	private List<Item> itemList;
	
	private long recordCount;
	
	private long pageCount;
	
	private long currentPage;

	public List<Item> getItemList() {
		return itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

	public long getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(long recordCount) {
		this.recordCount = recordCount;
	}

	public long getPageCount() {
		return pageCount;
	}

	public void setPageCount(long pageCount) {
		this.pageCount = pageCount;
	}

	public long getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(long currentPage) {
		this.currentPage = currentPage;
	}
	

}
