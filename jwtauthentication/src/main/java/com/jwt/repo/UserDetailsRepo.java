package com.jwt.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jwt.model.UserDetails;


@Repository
public interface UserDetailsRepo extends JpaRepository<UserDetails, Integer>{

	Optional<UserDetails> findByUserId(Integer userId);

}
