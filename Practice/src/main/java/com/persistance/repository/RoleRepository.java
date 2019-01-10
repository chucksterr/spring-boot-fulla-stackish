package com.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.persistance.models.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{

}
