package com.identity.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.identity.entity.UserCredential;

@Repository
public interface UserCredentialRepo extends JpaRepository<UserCredential, String>{

	Optional<UserCredential> findByName(String username);

}
