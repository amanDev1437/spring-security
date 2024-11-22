package com.aman.springsecdemo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;


@Data
@Document(collection = "UserDetails")
public class User {
	
	@Id
	private String id;
	private String username;
	private String password;
	private Set<String> roles;
	
}
