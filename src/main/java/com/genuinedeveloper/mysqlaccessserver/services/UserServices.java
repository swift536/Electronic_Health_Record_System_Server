package com.genuinedeveloper.mysqlaccessserver.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.genuinedeveloper.mysqlaccessserver.db_entities.Users;
import com.genuinedeveloper.mysqlaccessserver.repositories.UsersRepository;
import com.genuinedeveloper.mysqlaccessserver.utilities.Authentication;

@Service
public class UserServices {

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private Authentication auth;

	@PostMapping(path = "/users/add")
	public boolean postUser(Users user) {

		boolean response = false;

		try {

			String hashedUsername = auth.encrypt(user.getHashedUsername());
			String hashedPassword = auth.encrypt(user.getHashedPassword());
			
			user.setHashedUsername(hashedUsername);
			user.setHashedPassword(hashedPassword);
			
			usersRepository.save(user);

			response = true;

		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("Error saving user");

		}

		return response;

	}

	// TODO debug
	// Initial query to get security questions
	public String[] forgotPassword(Integer id) {

		String[] strArray = new String[3];

		Users user = usersRepository.findById(id).get();

		strArray[0] = user.getSecurityQuestion1();
		strArray[1] = user.getSecurityQuestion2();
		strArray[2] = user.getSecurityQuestion3();

		return strArray;

	}

	// Posts answers to security questions, returns password unencrypted.
	@PostMapping(path = "/users/security-questions/{id}")
	public char[] postSecurityAnswers(String[] answers, Integer id) {

		char[] response = auth.authenticateUserByAnswers(id, answers);

		return response;

	}

}
