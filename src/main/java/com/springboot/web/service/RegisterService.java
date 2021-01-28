package com.springboot.web.service;


import com.springboot.web.model.UserCrudRepo;
import com.springboot.web.model.UserEntity;

//import org.springframework.stereotype.Service;

//@Service
public class RegisterService {

	UserCrudRepo userCrudRepo;

	public String validateInputs(String username, String password, String email) {

		
		UserEntity user = new UserEntity();

		user.setUsername(username);
		user.setPasword(password);
		user.setEmail(email);

		userCrudRepo.save(user);

		return "Save successful";
	}

	// Check if username/password/email doesn't contain SQL statement
	public boolean checkforSQLstatements(String username, String password, String email) {

		return true;
	}

	// Check email has a domain
	public boolean checkEmailHasADomain(String email) {

		return true;
	}

}
