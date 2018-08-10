package com.bridgelabz.microservices.user.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.microservices.user.exception.ActivationException;
import com.bridgelabz.microservices.user.exception.LoginException;
import com.bridgelabz.microservices.user.exception.RegistrationException;
import com.bridgelabz.microservices.user.exception.UserNotFoundException;
import com.bridgelabz.microservices.user.model.LoginDTO;
import com.bridgelabz.microservices.user.model.PasswordDTO;
import com.bridgelabz.microservices.user.model.RegistrationDTO;
import com.bridgelabz.microservices.user.model.ResponseDTO;
import com.bridgelabz.microservices.user.model.User;
import com.bridgelabz.microservices.user.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	
	//-------------Get All Users--------------------------
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	//-----------Activate account for registration----------------
	
	@RequestMapping(value = "/activateaccount", method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO> activateAccount(HttpServletRequest request) throws RegistrationException {

		String token = request.getQueryString();
		System.out.println(token);

		ResponseDTO response = new ResponseDTO();

			response.setMessage("Account activated successfully");
			response.setStatus(1);

			return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	//----------------Activate USer Using RequestParam------------
	
	@RequestMapping(value = "/activate", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> activateAcc(@RequestParam(value="token") String token) throws RegistrationException, UserNotFoundException {

		userService.activate(token);
		
		ResponseDTO response = new ResponseDTO();

			response.setMessage("Account activated successfully");
			response.setStatus(1);

			return new ResponseEntity<>(response, HttpStatus.OK);
	
	}

	//-----------------------Registration------------------------
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> registerUser(@RequestBody RegistrationDTO user) throws RegistrationException {

		userService.registerUser(user);

		ResponseDTO response = new ResponseDTO();
		response.setMessage("User with email "+user.getEmail()+" registered successfully");
		response.setStatus(1);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	//------------------------Delete a User-------------------
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseDTO> deleteUser(@RequestParam String email) throws UserNotFoundException {

		ResponseDTO response = new ResponseDTO();
		
		userService.deleteUser(email);

		response.setMessage("User with email id " + email + " successfully deleted");
		response.setStatus(1);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	//---------------------------Update User-----------------------
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<ResponseDTO> updateUser(@RequestBody User user) throws UserNotFoundException {

		userService.updateUser(user);

		ResponseDTO response = new ResponseDTO();
		response.setMessage("User with email " + user.getId() + " successfully updated");
		response.setStatus(1);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
     //--------------------------Login User-------------------------
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> loginUser(@RequestBody LoginDTO user,HttpServletResponse res) throws LoginException, UserNotFoundException, ActivationException {
		
        String token=userService.loginUser(user);
        res.setHeader("token",token);
        
		ResponseDTO response = new ResponseDTO();
		response.setMessage("User Sucessfully logged in");
		response.setStatus(2);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	//-----------------------Forgot password------------------------
	
	@RequestMapping(value = "/forgotpassword", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> forgetPassword(@RequestParam(value="email") String email) throws UserNotFoundException {

	    userService.forgetPassword(email);
	    System.out.println(email);
		ResponseDTO response = new ResponseDTO();
		response.setMessage("link sent to email,pls check and verify");
		response.setStatus(1);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
    //----------------------Reset password----------------------------
	
	@RequestMapping(value = "/resetpassword", method = RequestMethod.PUT)
	public ResponseEntity<ResponseDTO> resetPassword(@RequestHeader(value="token") String token,
			@RequestBody PasswordDTO passwordDto) throws Exception {

		userService.passwordReset(token, passwordDto);

		ResponseDTO response = new ResponseDTO();
		response.setMessage("Password is successfully changed");
		response.setStatus(4);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
