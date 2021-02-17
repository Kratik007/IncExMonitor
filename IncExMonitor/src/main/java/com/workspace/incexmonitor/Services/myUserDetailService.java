package com.workspace.incexmonitor.Services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.workspace.incexmonitor.Entities.User;
@Service
public class myUserDetailService implements UserDetailsService {

	@Autowired
	public UserService userService;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=userService.getUserByUserName(username);
		UserDetails userDet= new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),new ArrayList<>()); 	
		return userDet;
	}
	
}
