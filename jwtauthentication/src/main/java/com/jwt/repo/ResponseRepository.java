package com.jwt.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jwt.model.ResponseMessage;


@Repository
public interface ResponseRepository extends JpaRepository<ResponseMessage, Integer>{

	
	@Query(value= "SELECT response_message FROM loginapp.response_message where response_code=?1", nativeQuery= true)
	public String fetchMessage(String responseCode);
	
}
