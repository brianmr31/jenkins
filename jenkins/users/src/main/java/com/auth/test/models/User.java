package com.auth.test.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user")
public class User {
	
	private long id;
    @NotNull
    private String username;
    @NotNull
    @Size(min=7, message="Passport should have atleast 2 characters")
    private String password;
    
    private Set<Role> roles = new HashSet<Role>();
    public User() {
    	
    }
    public User(String username, String password) {
    	this.username = username ;
    	this.password = password ;
		// TODO Auto-generated constructor stub
	}
    @ManyToMany(fetch = FetchType.LAZY,
	    cascade = {
	            CascadeType.PERSIST,
	            CascadeType.MERGE
	        })
    @JoinTable(
        name = "user_role", 
        joinColumns = { @JoinColumn(name = "user_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "role_id") })
    public Set<Role> getRoles(){
    	return this.roles ;
    }
    public void setRoles(Set<Role> roles) {
    	this.roles = roles;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    @Column(name = "username", nullable = false)
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    @Column(name = "password", nullable = false)
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", password=" + password + "]";
    }
}
