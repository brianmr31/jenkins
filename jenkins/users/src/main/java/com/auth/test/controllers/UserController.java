package com.auth.test.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.auth.test.models.Role;
import com.auth.test.models.User;
import com.auth.test.services.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService ;
	
    @RequestMapping(value = "/api/v1/user/{username}", method = RequestMethod.GET)
    public User getUserByUsername(@PathVariable("username") String username) {
    	User user = userService.getUserByUsername(username);
    	return user ;  
    }
    @RequestMapping(value = "/api/v1/user", method = RequestMethod.POST)
    public User saveUser(@RequestBody User param) {
    	
    	User user = userService.saveUser(param);
    	return user ;
    }

    @Secured({"ROLE_SYSTEMADMIN", "ROLE_USER"})
    @RequestMapping(value = "/api/v1/users", method = RequestMethod.GET)
    public Page<User> listUser(Pageable pageable) {
    	Page<User> user = userService.listUser(pageable);
    	return user ;
    }
    @RequestMapping(value = "/api/v1/user/get/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable("id") Long id) {
    	User user = userService.getUser(id);
    	// System.out.println(user.getPassword());
    	return user ;
    }
    @RequestMapping(value = "/api/v1/user/del/{id}", method = RequestMethod.GET, produces="aplication/json")
    public String delUser(@PathVariable("id") Long id) {
    	userService.delUser(id);
    	return "{ \"msg\" : \"success\" }";
    }
    @RequestMapping(value = "/api/v1/user/role/{id}", method = RequestMethod.GET)
    public Set<Role> getUserRole(@PathVariable("id") Long id) {
    	User user = userService.getUser(id);
    	return user.getRoles() ;
    }
    @RequestMapping(value = "/api/v1/user/role/{id}", method = RequestMethod.POST, produces="aplication/json")
    public String setUserRole(@PathVariable("id") Long id, @RequestBody List<Long> roles) {
    	if(roles.size() < 0 ) {
    		return "{ \"msg\" : \"success\" }";
    	}
    	userService.setUserRole(id,roles);
    	return "{ \"msg\" : \"success\" }";
    }
}
