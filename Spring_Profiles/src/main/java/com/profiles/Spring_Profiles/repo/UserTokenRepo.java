package com.profiles.Spring_Profiles.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.profiles.Spring_Profiles.model.UserLoginToken;


@Repository
public interface UserTokenRepo extends JpaRepository<UserLoginToken, Integer>{

	
//	@Query(value = "select * from user_login_token where auth_token = ?1	",nativeQuery = true)
	public UserLoginToken findByAuthToken(String token);

	public Optional<UserLoginToken> findByUserId(Integer id);
	
}
