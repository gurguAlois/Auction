package com.sda.auction.controller;

import com.sda.auction.dto.ItemForm;
import com.sda.auction.model.User;
import com.sda.auction.service.ItemService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("admin")
public class AdminController {

	@Autowired
	private ItemService itemService;

	@RequestMapping(value = {"/home",}, method = RequestMethod.GET)
	public ModelAndView adminHome() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/home");
		return modelAndView;
	}

	@RequestMapping(value = {"/newItem",}, method = RequestMethod.GET)
	public ModelAndView newItem() { ;

		ModelAndView modelAndView = new ModelAndView();
		ItemForm itemForm = new ItemForm();
		modelAndView.addObject(itemForm);
		modelAndView.setViewName("admin/newItem");
		return modelAndView;
	}

	private String getAuthenticatedEmail() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}

	@RequestMapping(value = {"/newItem",}, method = RequestMethod.POST)
	public ModelAndView newItem(@Valid ItemForm itemForm, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		if (!bindingResult.hasErrors()) {

			itemService.saveItem(itemForm, getAuthenticatedEmail());
			modelAndView.addObject("successMessage",
					"Good job! Item saved!");
			modelAndView.addObject(new ItemForm());
		}

		modelAndView.setViewName("admin/newItem");
		return modelAndView;
	}
}
