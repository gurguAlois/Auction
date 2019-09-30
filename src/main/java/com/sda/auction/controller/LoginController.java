package com.sda.auction.controller;


import com.sda.auction.dto.UserForm;
import com.sda.auction.model.User;
import com.sda.auction.service.UserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;


	@RequestMapping(value = {"/", "/login",}, method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@RequestMapping(value = {"/registration",}, method = RequestMethod.GET)
	public ModelAndView register() {
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject(new UserForm());

		modelAndView.setViewName("registration");
		return modelAndView;
	}

	@RequestMapping(value = {"/registration",}, method = RequestMethod.POST)
	public ModelAndView registerPost(@Valid UserForm userForm, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		System.out.println("Ce am primit: " + userForm);
		if (bindingResult.hasErrors()) {
			System.out.println("eroare");
		} else {
			User existingUser = userService.findByEmail(userForm.getEmail());
			if (existingUser != null) {
				//eroare
				bindingResult.rejectValue("email", "error.user",
						"There is already a user registered with this email");
			} else {
				userService.saveUser(userForm);
				modelAndView.addObject(new UserForm());
				modelAndView.addObject("successMessage",
						"Good job! Looking forward to spend your money!");
			}
		}
		modelAndView.setViewName("registration");
		return modelAndView;
	}


	@RequestMapping(value = {"/successfulLogin",}, method = RequestMethod.GET)
	public ModelAndView loginSuccessful() {
		ModelAndView modelAndView = new ModelAndView();
		if (userService.isLoggedUserAdmin()) {
			modelAndView.setViewName("redirect:/admin/home");
		} else {
			modelAndView.setViewName("redirect:/account/home");
		}
		return modelAndView;
	}

}
