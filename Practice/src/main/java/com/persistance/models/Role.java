package com.persistance.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Role {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int role_id;
	private String role;
	/////////////////////////////
	@ManyToMany(mappedBy = "roles")
	@JsonBackReference
	private List<Users> users;
	///////////CONSTRUCTORS//////////////////
	public Role() {
		super();
	}
	public Role(String role) {
		super();
		this.role = role;
	}
	/////////////////////////////
	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	/////////////////////////////
	public List<Users> getUsers() {
		return users;
	}
	/////////////////////////////
	public void setUsers(List<Users> users) {
		this.users = users;
	}	 
	/////////////////////////////
	
}
