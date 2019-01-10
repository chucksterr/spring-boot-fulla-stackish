package com.persistance.models;

import java.util.List;
//import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
//import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Users {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private int active;
	@Email
	@NotEmpty
	@Column(unique=true)
	private String email;
	@NotEmpty
	private String username;
	@Size(min=6)
	private String password;
		
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", 
	joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id")}, 
	inverseJoinColumns = {@JoinColumn(name="role_id", referencedColumnName = "role_id")})
	@JsonManagedReference /*this annotation prevents JSON infinite recursion when querying all users...
	the owner of the relationship is the managed reference; the other is back reference...
	see Role class List <Users> users*/ 
	private List<Role> roles; // collection of role objects save in a variable roles
	
	public Users() {
		super();
	}
	
	public Users(Users users) {
		super();
		this.username = users.getUsername();
		this.email = users.getEmail();
		this.password = users.getPassword();
		this.active = users.getActive();
		this.roles = users.getRoles();
		this.id = users.getId();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public List<Role> getRoles() { // returns a set of roles
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", username=" + username + ", email=" + email 
							+ ", password=" + password
							+ ", active=" + active + ", roles=" + roles + "]";
	}	

}
