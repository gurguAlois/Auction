package com.sda.auction.service.impl;

import com.sda.auction.dto.ItemForm;
import com.sda.auction.mapper.ItemMapper;
import com.sda.auction.model.Item;
import com.sda.auction.model.User;
import com.sda.auction.repository.ItemRepository;
import com.sda.auction.service.ItemService;
import com.sda.auction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

	private ItemMapper itemMapper;
	private ItemRepository itemRepository;
	private UserService userService;

	@Autowired
	public ItemServiceImpl(ItemMapper itemMapper, ItemRepository itemRepository,
			UserService userService) {
		this.itemMapper = itemMapper;
		this.itemRepository = itemRepository;
		this.userService = userService;
	}

	@Override
	public void saveItem(ItemForm itemForm, String authenticatedEmail) {
		Item item = itemMapper.map(itemForm);
		setUserByEmail(authenticatedEmail, item);
		itemRepository.save(item);
	}

	private void setUserByEmail(String authenticatedEmail, Item item) {
		User user = userService.findByEmail(authenticatedEmail);
		item.setUser(user);
	}
}
