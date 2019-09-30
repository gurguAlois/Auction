package com.sda.auction.service;

import com.sda.auction.dto.ItemForm;
import com.sda.auction.model.Item;
import java.sql.SQLException;
import java.util.List;

public interface ItemService {

	void saveItem(ItemForm itemForm);


	List<ItemForm> findAll();

	List<ItemForm> findAvailableItems();

	Item findItemById(String itemId);

	ItemForm findItemFormById(String itemId);

	byte[] getItemImageByItemId(Integer itemId) throws SQLException;
}
