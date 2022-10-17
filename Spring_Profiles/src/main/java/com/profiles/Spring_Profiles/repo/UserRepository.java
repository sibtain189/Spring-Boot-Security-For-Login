package com.profiles.Spring_Profiles.repo;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.profiles.Spring_Profiles.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);
}
