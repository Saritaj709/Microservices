package com.bridgelabz.microservices.user.services;

import java.util.List;
import java.util.Optional;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.microservices.user.exception.ActivationException;
import com.bridgelabz.microservices.user.exception.LoginException;
import com.bridgelabz.microservices.user.exception.RegistrationException;
import com.bridgelabz.microservices.user.exception.UserNotFoundException;
import com.bridgelabz.microservices.user.model.LoginDTO;
import com.bridgelabz.microservices.user.model.MailDTO;
import com.bridgelabz.microservices.user.model.PasswordDTO;
import com.bridgelabz.microservices.user.model.RegistrationDTO;
import com.bridgelabz.microservices.user.model.User;
import com.bridgelabz.microservices.user.repository.ElasticRepositoryForUser;
import com.bridgelabz.microservices.user.repository.RedisRepository;
import com.bridgelabz.microservices.user.repository.UserRepository;
import com.bridgelabz.microservices.user.utility.UserUtility;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository; // extends mongoRepository

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ProducerService producer;
	
	@Autowired
	private ElasticRepositoryForUser userElasticRepository;
	
	/*@Autowired
	private MailService mailService;*/
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private JwtToken jwtToken;
	
	@Autowired
	private RedisRepository redisRepository; 
	
	@Override
	public List<User> getAllUsers() {

		List<User> userList = userRepository.findAll();
		return userList;
	}

	@Override
	public String registerUser(RegistrationDTO dto) throws RegistrationException {

		UserUtility.validateUser(dto);

		Optional<User> checkUser = userRepository.findByEmail(dto.getEmail());

		if (checkUser.isPresent()) {
			throw new RegistrationException("User email already exists,unable to register");

		}
		User user = new User();
		user.setEmail(dto.getEmail());
		user.setFirstname(dto.getFirstname());
		user.setLastname(dto.getLastname());
		user.setPhoneNo(dto.getPhoneNo());
		user.setPassword(passwordEncoder.encode(dto.getPassword()));
		userRepository.insert(user);
		userElasticRepository.save(user);

		String jwt = jwtToken.tokenGenerator(user.getId());
		
		jwtToken.parseJwtToken(jwt);

		MailDTO mail = new MailDTO();
		mail.setTo(dto.getEmail());
		mail.setSubject("Account activation mail");
		mail.setText(environment.getProperty("accountActivationLink") + jwt);
		producer.sender(mail);
        //mailService.sendMail(mail);
		return jwt;
	}

	@Override
	public void getUserById(String id) throws UserNotFoundException {

		//Optional<User> checkUser = userRepository.findById(id);
		Optional<User> checkUser = userElasticRepository.findById(id);
		
		if (!checkUser.isPresent()) {
			throw new UserNotFoundException("User is not available");
		}
	}

	@Override
	public String loginUser(LoginDTO loginDto) throws LoginException, UserNotFoundException, ActivationException {

		UserUtility.validateLogin(loginDto);

		//Optional<User> checkUser = userRepository.findByEmail(loginDto.getEmail());
		Optional<User> checkUser = userElasticRepository.findByEmail(loginDto.getEmail());

		if (!checkUser.isPresent()) {
			throw new UserNotFoundException("This Email id does not exist");
		}

		if (!checkUser.get().isActivate()) {
			throw new ActivationException("User account is not activated yet");
		}

		if (!passwordEncoder.matches(loginDto.getPassword(), checkUser.get().getPassword())) {
			throw new LoginException("Password unmatched");
		}

		String jwt = jwtToken.tokenGenerator(checkUser.get().getId());
		return jwt;

	}

	@Override
	public String updateUser(User user) throws UserNotFoundException {

		UserUtility.validateEmail(user.getEmail());

		Optional<User> checkUser = userRepository.findById(user.getId());
		if (!checkUser.isPresent()) {
			throw new UserNotFoundException("User id does not exist");
		}

		user.setEmail(checkUser.get().getEmail());
		user.setFirstname(checkUser.get().getFirstname());
		user.setLastname(checkUser.get().getLastname());
		user.setPhoneNo(checkUser.get().getPhoneNo());
		user.setPassword(passwordEncoder.encode(checkUser.get().getPassword()));
		userRepository.save(user);
		userElasticRepository.save(user);

		JwtToken jwtToken=new JwtToken();
		String jwt = jwtToken.tokenGenerator(user.getEmail());
		return jwt;
	}

	@Override
	public void deleteUser(String email) throws UserNotFoundException {

		//Optional<User> checkUser = userRepository.findByEmail(email);
		Optional<User> checkUser = userElasticRepository.findByEmail(email);

		if (!checkUser.isPresent()) {
			throw new UserNotFoundException("User not found");
		}
		userRepository.deleteByEmail(email);
		userElasticRepository.deleteByEmail(email);

		
	}

	@Override
	public void activateJwt(String token) {

		Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(environment.getProperty("Key"))).parseClaimsJws(token)
				.getBody();

		//Optional<User> user = userRepository.findById(claims.getSubject());
		Optional<User> user = userElasticRepository.findById(claims.getSubject());

		user.get().setActivate(true);
		userRepository.save(user.get());
		userElasticRepository.save(user.get());

	}

	
	@Override
	public void activate(String token) throws UserNotFoundException {
		Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(environment.getProperty("Key"))).parseClaimsJws(token)
				.getBody();

		//Optional<User> user = userRepository.findById(claims.getSubject());
		Optional<User> user = userElasticRepository.findById(claims.getSubject());

		if(!claims.getSubject().equals(user.get().getId())) {
	 	throw new UserNotFoundException("User not found");
		}
		
		user.get().setActivate(true);
		userRepository.save(user.get());
		userElasticRepository.save(user.get());

	}

	@Override
	public void forgetPassword(String email) throws UserNotFoundException {

	//	Optional<User> user = userRepository.findById(id);
		Optional<User> user = userElasticRepository.findByEmail(email);


		if (!user.isPresent()) {
			throw new UserNotFoundException("User is not present");
		}

		String uuid=UserUtility.generateUUId();
		redisRepository.save(uuid,email);

		MailDTO mail = new MailDTO();
		mail.setTo(email);
		mail.setSubject("Password reset mail");
		mail.setText(environment.getProperty("passwordResetLink")  + uuid);

		producer.sender(mail);
		//mailService.sendMail(mail);
	}

	@Override
	public void passwordReset(String uuid, PasswordDTO dto) throws UserNotFoundException, RegistrationException {
		
		UserUtility.validateReset(dto);
		
		String email= redisRepository.get(uuid);

		//Optional<User> user = userRepository.findById(userId);
		Optional<User> user = userElasticRepository.findByEmail(email);

		if (!user.isPresent()) {
			throw new UserNotFoundException("User not found");
		}

		user.get().setPassword(passwordEncoder.encode(dto.getPassword()));
		userRepository.save(user.get());
		userElasticRepository.save(user.get());
		redisRepository.delete(uuid);

	}
}
