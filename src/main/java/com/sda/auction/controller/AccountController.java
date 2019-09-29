package com.sda.auction.controller;

import com.sda.auction.dto.BidForm;
import com.sda.auction.dto.ItemForm;
import com.sda.auction.service.BidService;
import com.sda.auction.service.ItemService;
import com.sda.auction.validator.BidFormValidator;
import java.util.List;
import javax.validation.Valid;
import org.hibernate.annotations.AttributeAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("account")
public class AccountController {

	@Autowired
	private ItemService itemService;
	@Autowired
	private BidService bidService;
	@Autowired
	private BidFormValidator bidFormValidator;

	@RequestMapping(value = {"/home",}, method = RequestMethod.GET)
	public ModelAndView accountHome() {
		ModelAndView modelAndView = new ModelAndView();

		List<ItemForm> itemList = itemService.findAvailableItems();
		modelAndView.addObject("itemList", itemList);

		modelAndView.setViewName("account/home");
		return modelAndView;
	}

	@RequestMapping(value = {"/item/{itemId}",}, method = RequestMethod.GET)
	public ModelAndView viewItemPage(
			@PathVariable(value = "itemId") String itemId) {
		ModelAndView modelAndView = new ModelAndView();

		ItemForm itemForm = itemService.findItemFormById(itemId);
		modelAndView.addObject(itemForm);
		modelAndView.addObject(new BidForm());

		modelAndView.setViewName("account/viewItem");
		return modelAndView;
	}


	@RequestMapping(value = {"/item/{itemId}"}, method = RequestMethod.POST)
	public ModelAndView viewItemPagePost(
			@PathVariable(value = "itemId") String itemId,
			@Valid BidForm bidForm) {
		ModelAndView modelAndView = new ModelAndView();
		System.out.println("!!!!!!!!!!!!!!!!!!!!" + bidForm);
		if (bidFormValidator.isValid(bidForm, itemId)) {
			bidService.save(bidForm, itemId);
			modelAndView.setViewName("redirect:/account/item/" + itemId);

		} else {
			modelAndView.addObject("errorMessage",
					"Bid not valid!");
			ItemForm itemForm = itemService.findItemFormById(itemId);
			modelAndView.addObject(itemForm);
			modelAndView.setViewName("account/viewItem");
		}

		return modelAndView;
	}
}
