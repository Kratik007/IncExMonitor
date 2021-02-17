package com.workspace.incexmonitor.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.workspace.incexmonitor.Entities.User;

public interface UserRepository extends JpaRepository<User, String> {
	  public User findByUsername(String username);

	  public Optional<User> findByEmail(String email);
	  
	  @Query(value="SELECT fullname from user WHERE username=:username",nativeQuery = true)
	  public String findFullnameByUsername(@Param("username") String username);

}
