package com.springboot.web.service;

import com.springboot.web.model.*;

import java.util.regex.*;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@SessionScope
public class DatabaseService {

	private static Logger logger = LoggerFactory.getLogger(DatabaseService.class);
	
	@Autowired
	UserCrudRepo userCrudRepo;

	// SAVE to DATABASE ----------------last minute check -------------------REGISTER
	
	public int saveNewUser(String username, String password, String email) {
		logger.info("DATABASE SERVICE -saveNewUser- attempting to save executing....  ");
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
			logger.info("DATABASE SERVICE -saveNewUser- save SUCCESSFUL  ");
			return 1;

		} catch (Exception e) {
			logger.info("DATABASE SERVICE -saveNewUser- save FAILED - Exception occured  ");
			return 3;
		}

	}

	// checks if username exists in the database 
	public boolean checkUserNameExists(String username) {
		logger.info("DATABASE SERVICE -checkUsernameExists- executing....  ");
		
		Iterable<UserEntity> users = userCrudRepo.findAll();

		for (UserEntity user : users) {
			if (user.getUsername().toLowerCase().equals(username.toLowerCase())) {
				logger.info("DATABASE SERVICE -checkUsernameExists- " + username + " does exist.. return TRUE");
				return true;
			}
		}
		
		logger.info("DATABASE SERVICE -checkUsernameExists- " + username + " does NOT exist.. return FALSE");
		return false;
	}

	// Checks if email has a domain --------------------------------------REGISTER
	public boolean checkEmaildomain(String email) {
		logger.info("DATABASE SERVICE -checkForEmailDomain- executing.....  ");
		
		String pattern = "^[-a-z0-9~!$%^&*_=+}{\\'?]+(\\.[-a-z0-9~!$%^&*_=+}{\\'?]+)*@([a-z0-9_][-a-z0-9_]*(\\.[-a-z0-9_]+)*\\.(aero|arpa|biz|com|coop|edu|gov|info|int|mil|museum|name|net|org|pro|travel|mobi|[a-z][a-z])|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,5})?$";
		Pattern pat = Pattern.compile(pattern);
		Matcher mat = pat.matcher(email);

		return mat.find();

	}

	// Check for blanks / empty strings / spaces ---------------------------REGISTER
	public boolean checkForBlanks(String input) {
		logger.info("DATABASE SERVICE -checkForBlanksWhiteSpaces- executing.....  ");
		if (StringUtils.isAllBlank(input) || StringUtils.isAllEmpty(input) || StringUtils.isWhitespace(input) || StringUtils.containsWhitespace(input)) {
			logger.info("DATABASE SERVICE -checkForBlanksWhiteSpaces- found BLANK/WHITESPACE/EMPTY string... return TRUE  ");
			return true;
		}
		
		logger.info("DATABASE SERVICE -checkForBlanksWhiteSpaces- NO BLANK/WHITESPACE/EMPTY string found... return FALSE  ");
		return false;
	}


	// Check is password and username is correct ----------------------------LOGIN
	public boolean validateUserLogin(String username, String password) {

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
	
	// check if password 
	public String returnPassword( String username, String email ) {
		logger.info("DATABASE SERVICE -retrieveingPassword- executing.....  ");
		
		Iterable<UserEntity> users = userCrudRepo.findAll();


		for (UserEntity user : users) {
			if (user.getUsername().equals(username)) {
				if (user.getEmail().equals(email)) {
					logger.info("DATABASE SERVICE -retrieveingPassword- password found.....  ");
					return user.getPassword();
				}
				logger.info("DATABASE SERVICE -retrieveingPassword- email did not match.....  ");
				return "-1";
			}
		}
		
		logger.info("DATABASE SERVICE -retrieveingPassword- username does not exist.....  ");
		return "-2";

	}
	
	// check if username and email is correct 
	public boolean checkUserEmailexists( String username, String email) {
		logger.info("DATABASE SERVICE -checking if Username and Email exists- executing.....  ");
		
		Iterable<UserEntity> users = userCrudRepo.findAll();

		for (UserEntity user : users) {
			if (user.getUsername().equals(username)) {
				if (user.getEmail().equals(email)) {
					logger.info("DATABASE SERVICE -checking if Username and Email exists- successful... return TRUE  ");
					return true;
				}
				logger.info("DATABASE SERVICE -checking if Username and Email exists- email incorrect... return FALSE  ");
				return false;
			}
		}
		
		logger.info("DATABASE SERVICE -checking if Username and Email exists- username incorrect... return FALSE  ");
		return false;
	}
	
	// find entity - copy entity - save new password in new entity - save entity
	public boolean overWritePassword(String username, String password) {
		logger.info("DATABASE SERVICE -overWrite password- executing....  ");
		Iterable<UserEntity> users = userCrudRepo.findAll();
		
		UserEntity newUser = null;
		boolean found = false;
		
		for (UserEntity user : users) {
			if (user.getUsername().equals(username)) {
				logger.info("DATABASE SERVICE -overWrite password- found user entity..copying..  ");
				newUser = user;
				found = true;
				
			}
		}
		
		if (found) {
			logger.info("DATABASE SERVICE -overWrite password- updating new password into new entity....  ");
			newUser.setPassword(password);
			
			try {
				userCrudRepo.save(newUser);
				logger.info("DATABASE Service -success- overwrite successful - new password saved... return TRUE");
				return true;
			} catch (Exception e) {
				logger.info("DATABASE Service -failed- could not overwrite save.... return TRUE");
				return false;
			}
		}
		
		logger.info("DATABASE SERVICE -overWrite password- user entity not found.... return FALSE  ");
		return false;
		
	}

}
