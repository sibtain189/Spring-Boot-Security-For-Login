package com.profiles.Spring_Profiles.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.profiles.Spring_Profiles.model.CustomUserDetails;
import com.profiles.Spring_Profiles.model.User;
import com.profiles.Spring_Profiles.repo.UserRepository;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		final User user = this.userRepository.findByUsername(userName);

		if (user == null) {
			throw new UsernameNotFoundException("User not found !!");
		} else {
			return new CustomUserDetails(user);
		}

		// user database `

//        if (userName.equals("Durgesh")) {
//            return new User("Durgesh", "Durgesh123", new ArrayList<>());
//        } else {
//            throw new UsernameNotFoundException("User not found !!");
//        }

	}
}
