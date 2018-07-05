package com.supermarket.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.supermarket.common.utils.KklResult;
import com.supermarket.search.service.ItemService;

/**
 * @author Admin
 * index library maintenance
 */
@Controller
public class ItemController {
	
	@Autowired
	private ItemService service;

	/**
	 * import all item data to index library
	 * @return
	 */
	@RequestMapping("/manager/importall")
	@ResponseBody
	public KklResult importAllItemSearchInfo() {
		KklResult result = service.importAllItem();
		return result;
	}
	
}
