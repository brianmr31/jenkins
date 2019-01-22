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
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "role")
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "role_id")
	private long id;
	
	@NotNull
    @Column(name = "rolename", nullable = false)
	private String rolename ;
	
	@ManyToMany(fetch = FetchType.LAZY,
        cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
        },
        mappedBy = "roles")
	private Set<User> users = new HashSet<>();
	
    public Role() {
    }
    public Role(String rolename) {
         this.rolename = rolename;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getRolename() {
        return this.rolename;
    }
    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
}
