package com.sda.auction.mapper;

import com.sda.auction.dto.ItemForm;
import com.sda.auction.model.Item;
import com.sda.auction.util.DateConverter;
import com.sda.auction.util.ImageUtil;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

	private final DateConverter dateConverter;

	@Autowired
	public ItemMapper(DateConverter dateConverter) {
		this.dateConverter = dateConverter;
	}

	public Item map(ItemForm itemForm) {
		Item item = new Item();
		item.setName(itemForm.getName());
		item.setDescription(itemForm.getDescription());
		item.setStartingPrice(itemForm.getStartingPrice());
		item.setCategory(itemForm.getCategory());

		Date startDate = dateConverter.parse(itemForm.getStartDate());
		item.setStartDate(startDate);

		Date endDate = dateConverter.parse(itemForm.getEndDate());
		item.setEndDate(endDate);

		item.setImage(ImageUtil.toBlob(itemForm.getFile()));

		return item;
	}


	public ItemForm map(Item item) {
		ItemForm itemForm = new ItemForm();
		itemForm.setName(item.getName());
		itemForm.setDescription(item.getDescription());
		itemForm.setStartingPrice(item.getStartingPrice());
		itemForm.setCategory(item.getCategory());
		itemForm.setId(item.getId());
		itemForm.setCurrentPrice(item.currentPrice());
		itemForm.setAuctioned(!item.getBids().isEmpty());
		itemForm.setOwnerName(item.getUserName());

		String startDate = dateConverter.format(item.getStartDate());
		itemForm.setStartDate(startDate);

		String endDate = dateConverter.format(item.getEndDate());
		itemForm.setEndDate(endDate);

		return itemForm;
	}

	public List<ItemForm> map(List<Item> itemList) {
		List<ItemForm> result = new ArrayList<>();
		for (Item item : itemList) {
			ItemForm itemForm = map(item);
			result.add(itemForm);
		}
		return result;
	}
}
