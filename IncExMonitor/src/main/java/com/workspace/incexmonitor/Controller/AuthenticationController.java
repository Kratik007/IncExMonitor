package com.workspace.incexmonitor.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.workspace.incexmonitor.Entities.AuthenticationRequest;
import com.workspace.incexmonitor.Entities.AuthenticationResponse;
import com.workspace.incexmonitor.JwtUtil.JwtUtil;
import com.workspace.incexmonitor.Services.UserService;
import com.workspace.incexmonitor.Services.myUserDetailService;

@RestController
public class AuthenticationController{
	
	@Autowired
	public AuthenticationManager authenticationManager;
	
	@Autowired
	public JwtUtil jwtUtil;
	
	@Autowired
	private UserService userSer;
	
	@Autowired
	public myUserDetailService userDetailService;
	
	@GetMapping("/hello")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<String> getGreeting() {
		return new ResponseEntity("Hello",HttpStatus.OK);
	}
	
	@PostMapping("/authenticate") 
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> generateToken(@RequestBody AuthenticationRequest body) throws Exception{
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(body.getUserName(), body.getPassword()));
		}catch(Exception e) {
			throw new Exception("Invalid Credentials");
		}
		UserDetails userDet=userDetailService.loadUserByUsername(body.getUserName());
		final String jwt=jwtUtil.generateToken(userDet);
		String fullname=userSer.getFullnameFromUsername(body.getUserName());
		return new ResponseEntity<AuthenticationResponse>(new AuthenticationResponse(jwt,body.getUserName(),fullname),HttpStatus.OK );
	}
	
}
