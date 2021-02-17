package com.workspace.incexmonitor.Services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workspace.incexmonitor.Entities.User;
import com.workspace.incexmonitor.Repository.UserRepository;

@Service
public class UserService {
	@Autowired
	public UserRepository userRepo;
	
	public void createUser(User user) {
		userRepo.save(user);
	}
	
	public void createAllUser(ArrayList<User> users) {
		userRepo.saveAll(users);
	}
	
	public User getUserByUserName(String username) {
		User user=userRepo.findByUsername(username);
		return user;
	}
	
	public String getFullnameFromUsername(String username) {
		return userRepo.findFullnameByUsername(username);
	}
	
	public Optional<User> getUserByEmail(String email) {
		return userRepo.findByEmail(email);
	}
	
	public User updateUser(User user) {
		userRepo.save(user);
		return getUserByUserName(user.getUsername());
	}
	
	public void deleteUser(String userid) {
		userRepo.delete(getUserByUserName(userid));
	}
}
