package com.login;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepository extends JpaRepository<userDetailsModel, Integer>  {
	
	public userDetailsModel findByName(String name);
}
