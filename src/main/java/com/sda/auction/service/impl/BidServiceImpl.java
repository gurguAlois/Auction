package com.sda.auction.service.impl;

import com.sda.auction.dto.BidForm;
import com.sda.auction.dto.ItemForm;
import com.sda.auction.model.Bid;
import com.sda.auction.model.Item;
import com.sda.auction.model.User;
import com.sda.auction.repository.BidRepository;
import com.sda.auction.service.BidService;
import com.sda.auction.service.ItemService;
import com.sda.auction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BidServiceImpl implements BidService {

	private ItemService itemService;
	private UserService userService;
	private BidRepository bidRepository;

	@Autowired
	public BidServiceImpl(UserService userService, ItemService itemService,
			BidRepository bidRepository) {
		this.userService = userService;
		this.itemService = itemService;
		this.bidRepository = bidRepository;
	}

	@Override
	public void save(BidForm bidForm, String itemId) {
		Bid bid = new Bid();
		bid.setValue(bidForm.getValue());

		User user = userService.getLoggedInUser();
		bid.setUser(user);

		Item item = itemService.findItemById(itemId);
		bid.setItem(item);

		bidRepository.save(bid);
	}
}
