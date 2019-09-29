package com.sda.auction.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

@Data
@ToString
public class ItemForm {

	private int id;

	@NotEmpty(message = "{error.item.name.empty}")
	private String name;

	@NotEmpty(message = "{error.item.description.empty}")
	private String description;

	@Positive(message = "{error.item.startingPrice.positive}")
	private int startingPrice;

	@NotEmpty(message = "{error.item.startDate.empty}")
	private String startDate;

	@NotEmpty(message = "{error.item.endDate.empty}")
	private String endDate;

	private String category;

	private int loggedUserBidValue;

	private int currentPrice;

	private boolean auctioned;

	private String ownerName;

	private String imagePath;
}
