package com.persistance.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;

import com.persistance.models.Users;

public interface UserRepository extends JpaRepository<Users, Integer>{

	Optional <Users> findByUsername(String username);
	// Users findOne(String email);
	// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
	// CRUD refers Create, Read, Update, Delete	
	/* interacting with the repository will interact with database in our stead
	   we deal with the DB at the java level, SPRING takes care of persistence */
}
