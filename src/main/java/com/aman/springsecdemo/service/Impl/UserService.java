package com.aman.springsecdemo.service.Impl;

import com.aman.springsecdemo.repository.UserRepo;
import com.aman.springsecdemo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class UserService {
	
@Autowired
private UserRepo repo;
private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);

	public User saveUser(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		user.setRoles(Collections.singleton("USER"));
		return repo.save(user) ;
		
	}

	public List<User> getAllUser(){
		return repo.findAll();
	}

	public void updateRoles(String role,String username){

		User user = repo.findByUsername(username);
		user.setRoles(Collections.singleton(role));
		repo.save(user);

	}
}
