package com.springboot.web.service;

import com.springboot.web.model.*;

import java.util.regex.*;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@SessionScope
public class DatabaseService {

	@Autowired
	UserCrudRepo userCrudRepo;

	// SAVE to DATABASE ----------------last minute checks
	// -------------------REGISTER
	public int saveNewUser(String username, String password, String email) {
		String[] inputs = { username, password, email };

		// 1 - inputs saved
		// 2 - empty/blank strings (checks for blanks again)
		// 3 - could not save

		for (String check : inputs) {
			if (checkForBlanks(check)) {
				return 2;
			}
		}

		try {
			UserEntity user = new UserEntity();
			user.setUsername(username);
			user.setPassword(password);
			user.setEmail(email);

			userCrudRepo.save(user);

			return 1;

		} catch (Exception e) {
			return 3;
		}

	}

	public boolean checkUserNameExists(String username) {
		Iterable<UserEntity> users = userCrudRepo.findAll();

		for (UserEntity user : users) {
			if (user.getUsername().equals(username)) {
					return true;
			}
		}

		return false;
	}

	// Checks if email has a domain --------------------------------------REGISTER
	public boolean checkEmaildomain(String email) {

		String pattern = "[^.]@[^.].[^.]*(?=\\.)";
		Pattern pat = Pattern.compile(pattern);
		Matcher mat = pat.matcher(email);

		return mat.find();

	}

	// Check if user-name/password/email doesn't contain SQL statement
	public boolean checkforSQLstatements(String username, String password, String email) {

		return true;
	}

	// Check for blanks / empty strings / spaces ---------------------------REGISTER
	public boolean checkForBlanks(String input) {

		if (StringUtils.isAllBlank(input)) {
			return true;
		}
		if (StringUtils.isAllEmpty(input)) {
			return true;
		}
		if (StringUtils.isWhitespace(input)) {
			return true;
		}
		if (StringUtils.containsWhitespace(input)) {
			return true;
		}

		return false;
	}

	// Check email has a domain
	// ---------------------------------------------REGISTER
	public boolean checkEmailHasADomain(String email) {

		return true;
	}

	// Check is password and username is correct ----------------------------LOGIN
	public boolean validateUserEntry(String username, String password) {

		Iterable<UserEntity> users = userCrudRepo.findAll();

		for (UserEntity user : users) {
			if (user.getUsername().equals(username)) {
				if (user.getPassword().equals(password)) {
					return true;
				}
			}
		}

		return false;

	}

}
