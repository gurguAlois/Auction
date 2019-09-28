package com.sda.auction.service;

import com.sda.auction.dto.ItemForm;
import java.util.List;

public interface ItemService {

	void saveItem(ItemForm itemForm);


	List<ItemForm> findAll();

	List<ItemForm> findAvailableItems();

	ItemForm findItemById(String itemId);
}
