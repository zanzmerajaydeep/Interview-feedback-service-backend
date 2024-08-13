package com.identity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.identity.entity.UserCredential;
import com.identity.repository.UserCredentialRepo;

@Service
public class AuthService {

	@Autowired
	private UserCredentialRepo repo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtService jwtService;

	public String saveUser(UserCredential credential) {
		credential.setPassword(passwordEncoder.encode(credential.getPassword()));
		repo.save(credential);
		return "user added! " + credential.getName();
	}

	public String generateToken(String username) {
		return jwtService.generateToken(username);
	}

	public void validateToken(String token) {
		jwtService.validateToken(token);
	}
	
	public List<UserCredential> getUsers() {
		return repo.findAll();
	}
}
