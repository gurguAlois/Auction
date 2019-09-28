package com.sda.auction.service.impl;

import com.sda.auction.dto.ItemForm;
import com.sda.auction.mapper.ItemMapper;
import com.sda.auction.model.Item;
import com.sda.auction.model.User;
import com.sda.auction.repository.ItemRepository;
import com.sda.auction.service.ItemService;
import com.sda.auction.service.UserService;
import java.util.List;
import java.util.Optional;
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
	public void saveItem(ItemForm itemForm) {
		Item item = itemMapper.map(itemForm);
		String authenticatedEmail = userService.getAuthenticatedEmail();
		setUserByEmail(authenticatedEmail, item);
		itemRepository.save(item);
	}

	@Override
	public List<ItemForm> findAll() {
		List<Item> itemList = itemRepository.findAll();
		List<ItemForm> itemFormList = itemMapper.map(itemList);
		return itemFormList;
	}

	@Override
	public List<ItemForm> findAvailableItems() {
		List<Item> itemList = itemRepository.findAvailable();
		List<ItemForm> itemFormList = itemMapper.map(itemList);
		return itemFormList;
	}

	@Override
	public ItemForm findItemById(String itemId) {
		Integer id = Integer.parseInt(itemId);
		Optional<Item> optionalItem = itemRepository.findById(id);
		if (optionalItem.isPresent()) {
			Item item = optionalItem.get();
			return itemMapper.map(item);
		}
		throw new RuntimeException();
	}

	private void setUserByEmail(String authenticatedEmail, Item item) {
		User user = userService.findByEmail(authenticatedEmail);
		item.setUser(user);
	}
}
