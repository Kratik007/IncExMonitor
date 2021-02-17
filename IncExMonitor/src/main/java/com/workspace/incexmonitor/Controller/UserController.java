package com.workspace.incexmonitor.Controller;

import java.util.Optional;

import javax.persistence.Id;
import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jayway.jsonpath.spi.json.GsonJsonProvider;
import com.workspace.incexmonitor.Entities.AuthenticationResponse;
import com.workspace.incexmonitor.Entities.User;
import com.workspace.incexmonitor.JwtUtil.JwtUtil;
import com.workspace.incexmonitor.Services.UserService;
import com.workspace.incexmonitor.Services.myUserDetailService;

@RestController
public class UserController {
	
	@Autowired
	public AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userServ;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	public myUserDetailService userDetailService;
	
	@PostMapping("/reg-user")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> registerUser(@RequestBody User user){
		try {
			userServ.createUser(user);
		}catch(Exception e) {
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity(HttpStatus.CREATED);
	}
	
	@GetMapping("/user/{username}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<UserDto> getUser(@PathVariable String username){
		User op;
		UserDto userDet;
		try {	
			op=userServ.getUserByUserName(username);
			userDet=new UserDto(op.getUsername(),op.getFullname(),op.getEmail(),op.getMobile());
		}catch(Exception e){
			return new ResponseEntity("There was error in saving the user", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<UserDto>(userDet,HttpStatus.OK);
	}
	
	@PutMapping("/update-user")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> updateUser(@RequestBody User user){
		User updatedUser;
		try {
			updatedUser=userServ.updateUser(user);
		}catch(Exception e) {
			return new ResponseEntity("Update unsuccessfull try again",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity(updatedUser,HttpStatus.OK);
	}
	
	@PutMapping("/update-user-email")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> updateUserWithEmail(@RequestBody User user){
		User updatedUser;
		try {
			if(!userServ.getUserByEmail(user.getEmail()).isPresent()) {
				updatedUser=userServ.updateUser(user);		
			}
			else {
				return new ResponseEntity("Email Already Exist",HttpStatus.CONFLICT);
			}
		}catch(Exception e) {
			return new ResponseEntity("Update unsuccessfull try again",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity(updatedUser,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete-user/{username}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<String>deleteUser(@PathVariable String username){
		try {			
			userServ.deleteUser(username);
		}catch(Exception E) {
			return new ResponseEntity("Problem in deleting the user",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity("User deleted usuccessfully",HttpStatus.OK);
	}
	
}
class UserDto{
	private String username;
	private String fullname;
	private String email;
	private String mobile;
	
	public UserDto() {
		super();
	}
	public UserDto(String username, String fullname, String email, String mobile) {
		super();
		this.username = username;
		this.fullname = fullname;
		this.email = email;
		this.mobile = mobile;
	}
	public String getUsername() {
		return username;
	}
	public String getFullname() {
		return fullname;
	}
	public String getEmail() {
		return email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
