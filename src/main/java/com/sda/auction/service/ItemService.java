package com.sda.auction.service;

import com.sda.auction.dto.ItemForm;

public interface ItemService {

	void saveItem(ItemForm itemForm, String authenticatedEmail);



}
