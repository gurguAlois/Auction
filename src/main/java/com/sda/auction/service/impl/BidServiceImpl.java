package com.sda.auction.service.impl;

import com.sda.auction.dto.BidForm;
import com.sda.auction.dto.ItemForm;
import com.sda.auction.model.Bid;
import com.sda.auction.model.Item;
import com.sda.auction.model.User;
import com.sda.auction.service.BidService;
import com.sda.auction.service.ItemService;
import com.sda.auction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BidServiceImpl implements BidService {

	private ItemService itemService;
	private UserService userService;

	@Autowired
	public BidServiceImpl(UserService userService, ItemService itemService) {
		this.userService = userService;
		this.itemService = itemService;
	}

	@Override
	public void save(BidForm bidForm, String itemId) {
		Bid bid = new Bid();
		bid.setValue(bidForm.getValue());

		String userEmail = userService.getAuthenticatedEmail();
		User user = userService.findByEmail(userEmail);
		bid.setUser(user);

		Item item = itemService.findItemById(itemId);
		bid.setItem(item);

		//dupa pauza :))


	}
}
