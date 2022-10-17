package com.profiles.Spring_Profiles.controller;

import com.profiles.Spring_Profiles.helper.JwtUtil;
import com.profiles.Spring_Profiles.model.JwtRequest;
import com.profiles.Spring_Profiles.model.JwtResponse;
//import com.profiles.Spring_Profiles.model.SaveToken;
import com.profiles.Spring_Profiles.model.User;
import com.profiles.Spring_Profiles.model.UserLoginToken;
import com.profiles.Spring_Profiles.repo.UserRepository;
import com.profiles.Spring_Profiles.response.LoginSuccessResponse;

import com.profiles.Spring_Profiles.services.CustomUserDetailsService;

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
	private com.profiles.Spring_Profiles.repo.UserTokenRepo usertokenRepo;

	@Autowired
	private com.profiles.Spring_Profiles.repo.ResponseRepository responseRepository;

	@Autowired
	private com.profiles.Spring_Profiles.repo.UserRepository userRepository;


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

		com.profiles.Spring_Profiles.model.UserLoginToken userloginToken = new com.profiles.Spring_Profiles.model.UserLoginToken();
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
