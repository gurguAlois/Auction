package com.sda.auction.mapper;

import com.sda.auction.dto.ItemForm;
import com.sda.auction.model.Item;
import com.sda.auction.util.DateParser;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

	private final DateParser dateParser;

	@Autowired
	public ItemMapper(DateParser dateParser) {
		this.dateParser = dateParser;
	}

	public Item map(ItemForm itemForm) {
		Item item = new Item();
		item.setName(itemForm.getName());
		item.setDescription(itemForm.getDescription());
		item.setStartingPrice(itemForm.getStartingPrice());

		Date startDate = dateParser.parse(itemForm.getStartDate());
		item.setStartDate(startDate);

		Date endDate = dateParser.parse(itemForm.getEndDate());
		item.setEndDate(endDate);

		return item;
	}
}
