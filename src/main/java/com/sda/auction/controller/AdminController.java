package com.sda.auction.controller;

import com.sda.auction.dto.BidForm;
import com.sda.auction.dto.ItemForm;
import com.sda.auction.model.User;
import com.sda.auction.service.ItemService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("admin")
public class AdminController {

	@Autowired
	private ItemService itemService;


	@RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/admin/home");
		return modelAndView;
	}


	@RequestMapping(value = {"/home",}, method = RequestMethod.GET)
	public ModelAndView adminHome() {
		ModelAndView modelAndView = new ModelAndView();

		List<ItemForm> itemList = itemService.findAll();
		modelAndView.addObject("itemList", itemList);

		modelAndView.setViewName("admin/home");
		return modelAndView;
	}

	@RequestMapping(value = {"/newItem",}, method = RequestMethod.GET)
	public ModelAndView newItem() {
		;

		ModelAndView modelAndView = new ModelAndView();
		ItemForm itemForm = new ItemForm();
		modelAndView.addObject(itemForm);
		modelAndView.setViewName("admin/newItem");
		return modelAndView;
	}


	@RequestMapping(value = {"/newItem",}, method = RequestMethod.POST)
	public ModelAndView newItem(@Valid ItemForm itemForm, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		if (!bindingResult.hasErrors()) {

			itemService.saveItem(itemForm);
			modelAndView.addObject("successMessage",
					"Good job! Item saved!");
			modelAndView.addObject(new ItemForm());
		}

		modelAndView.setViewName("admin/newItem");
		return modelAndView;
	}
	@RequestMapping(value = {"/item/{itemId}",}, method = RequestMethod.GET)
	public ModelAndView viewItemPage(
			@PathVariable(value = "itemId") String itemId) {
		ModelAndView modelAndView = new ModelAndView();

		ItemForm itemForm = itemService.findItemFormById(itemId);
		modelAndView.addObject(itemForm);
		modelAndView.addObject(new BidForm());

		modelAndView.setViewName("admin/viewItem");
		return modelAndView;
	}

}


