package com.profiles.Spring_Profiles.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.profiles.Spring_Profiles.model.UserDetails;




@Repository
public interface UserDetailsRepo extends JpaRepository<UserDetails, Integer>{

	Optional<UserDetails> findByUserId(Integer userId);

}
