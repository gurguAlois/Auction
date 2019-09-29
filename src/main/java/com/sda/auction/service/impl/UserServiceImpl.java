package com.sda.auction.service.impl;

import com.sda.auction.dto.UserForm;
import com.sda.auction.mapper.UserMapper;
import com.sda.auction.model.Item;
import com.sda.auction.model.Role;
import com.sda.auction.model.User;
import com.sda.auction.repository.RoleRepository;
import com.sda.auction.repository.UserRepository;
import com.sda.auction.service.UserService;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private UserMapper userMapper;
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	public UserServiceImpl(UserRepository userRepository,
			UserMapper userMapper, BCryptPasswordEncoder passwordEncoder,
			RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
		this.passwordEncoder = passwordEncoder;
		this.roleRepository = roleRepository;
	}


	@Override
	public void saveUser(UserForm userForm) {
		User user = userMapper.map(userForm);
		encodePassword(userForm, user);
		assignUserRoles(user);
		makeUserActive(user);
		userRepository.save(user);
	}

	private void makeUserActive(User user) {
		user.setActive(1);
	}

	private void assignUserRoles(User user) {
//		Role adminRole = roleRepository.findByRole("ADMIN");
		Role userRole = roleRepository.findByRole("USER");
//		user.addRole(adminRole);
		user.addRole(userRole);
	}

	private void encodePassword(UserForm userForm, User user) {
		String encodedPassword = passwordEncoder.encode(userForm.getPassword());
		user.setPassword(encodedPassword);
	}

	@Override
	public User findByEmail(String email) {
		System.out.println("find by email");

		return userRepository.findByEmail(email);
	}

	public String getAuthenticatedEmail() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		return authentication.getName();
	}

	@Override
	public User getLoggedInUser() {
		String userEmail = getAuthenticatedEmail();
		return findByEmail(userEmail);
	}

	@Override
	public boolean isLoggedUserAdmin() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		return authorities.contains(new SimpleGrantedAuthority("ADMIN"));

	}


}
