package com.codewithmandeep.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithmandeep.blog.payloads.JwtAuthRequest;
import com.codewithmandeep.blog.payloads.JwtAuthResponse;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
	
	@Autowired
	private com.codewithmandeep.blog.security.jwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> creteToken(
			@RequestBody JwtAuthRequest request
			) throws Exception
	{
		this.authenticate(request.getUsername(),request.getPassword());
		
	UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
	String token =	this.jwtTokenHelper.generateToken(userDetails);
	JwtAuthResponse response=new JwtAuthResponse();
	response.setToken(token);
	return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
	}

	private void authenticate(String username, String password) throws Exception {
		// TODO Auto-generated method stub
		
		UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username,password);
		
		try {
		
		this.authenticationManager.authenticate(authenticationToken);
		}catch (BadCredentialsException e) {
		System.out.println("Invalid Details !!");
		throw new Exception ("Invalid username or password!!");
		
		}
	}
}
