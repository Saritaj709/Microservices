package com.bridgelabz.microservices.user.services;

import java.util.List;

import com.bridgelabz.microservices.user.exception.ActivationException;
import com.bridgelabz.microservices.user.exception.LoginException;
import com.bridgelabz.microservices.user.exception.RegistrationException;
import com.bridgelabz.microservices.user.exception.UserNotFoundException;
import com.bridgelabz.microservices.user.model.LoginDTO;
import com.bridgelabz.microservices.user.model.PasswordDTO;
import com.bridgelabz.microservices.user.model.RegistrationDTO;
import com.bridgelabz.microservices.user.model.User;

public interface UserService {
	public List<User> getAllUsers();

	public String registerUser(RegistrationDTO user) throws RegistrationException;

	public void getUserById(String id) throws UserNotFoundException;

	public String loginUser(LoginDTO loginDto) throws LoginException, UserNotFoundException, ActivationException;

	public String updateUser(User user) throws UserNotFoundException;

	public void deleteUser(String email) throws UserNotFoundException;

	public void activateJwt(String token);

	public void forgetPassword(String id) throws UserNotFoundException;
	
	public void passwordReset(String token,PasswordDTO dto) throws RegistrationException, Exception;

	void activate(String token) throws UserNotFoundException;

}
