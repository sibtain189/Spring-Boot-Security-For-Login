package com.jwt.controller;

import com.jwt.helper.JwtUtil;
import com.jwt.model.JwtRequest;
import com.jwt.model.JwtResponse;
import com.jwt.model.User;
import com.jwt.model.UserLoginToken;
import com.jwt.repo.UserRepository;
import com.jwt.response.LoginSuccessResponse;

import com.jwt.services.CustomUserDetailsService;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class JwtController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private com.jwt.repo.UserTokenRepo usertokenRepo;

	@Autowired
	private com.jwt.repo.ResponseRepository responseRepository;

	@Autowired
	private com.jwt.repo.UserRepository userRepository;

	

	@RequestMapping(value = "/token", method = RequestMethod.POST)
	public LoginSuccessResponse generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {

		System.out.println("Inside Controller");
		System.out.println(jwtRequest);
		try {

			this.authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));

		} catch (Exception ex) {
			LoginSuccessResponse loginsuccessResponse = new LoginSuccessResponse();
			loginsuccessResponse.setResponseCode(401);
			loginsuccessResponse.setResponseMessage(responseRepository.fetchMessage("401_login"));
			loginsuccessResponse.setAuthToken("");

			return loginsuccessResponse;
		}

		com.jwt.model.UserLoginToken userloginToken = new com.jwt.model.UserLoginToken();
//      String authToken =  jwtUtil.generateToken(jwtRequest.getUsername());
		String authToken = jwtUtil.generateToken(jwtRequest.getUsername());

		String authUserName = jwtUtil.getUsernameFromToken(authToken);
		User user = userRepository.findByUsername(authUserName);
		System.out.println("================find token table=========");
		Optional<UserLoginToken> opt = usertokenRepo.findByUserId(user.getId());
		System.out.println("================got token table=========");
		LoginSuccessResponse loginsuccessResponse = new LoginSuccessResponse();

		if (opt.isPresent()) {

			UserLoginToken userLoginToken3 = opt.get();

			userLoginToken3.setAuthToken(authToken);
			userLoginToken3.setUserId(user.getId());
			userLoginToken3.setStatus(1);
			usertokenRepo.save(userLoginToken3);

			loginsuccessResponse.setResponseCode(200);
			loginsuccessResponse.setResponseMessage("successfuly updated the token");
			loginsuccessResponse.setAuthToken(authToken);
			return loginsuccessResponse;

		} else {

			userloginToken.setAuthToken(authToken);
			userloginToken.setUserId(user.getId());
			userloginToken.setStatus(1);
			usertokenRepo.save(userloginToken);

			loginsuccessResponse.setResponseCode(200);
			loginsuccessResponse.setResponseMessage("successfuly added the token");
			loginsuccessResponse.setAuthToken(authToken);
			return loginsuccessResponse;
		}

	}

}
