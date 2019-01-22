package com.auth.test.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.auth.test.models.Role;
import com.auth.test.services.RoleService;

@RestController
public class RoleController {
	@Autowired
	private RoleService roleService ;
	
    @RequestMapping(value = "/api/v1/role", method = RequestMethod.POST)
    public Role saveRole(@RequestBody Role param) {
    	Role role = roleService.saveRole(param);
    	return role ;
    }
    @RequestMapping(value = "/api/v1/roles", method = RequestMethod.GET)
    public Page<Role> listRole(Pageable pageable) {
    	Page<Role> role = roleService.listRole(pageable);
    	return role ;
    }
    @RequestMapping(value = "/api/v1/role/get/{id}", method = RequestMethod.GET)
    public Role getRole(@PathVariable("id") Long id) {
    	Role role = roleService.getRole(id);
    	return role ;
    }
    @RequestMapping(value = "/api/v1/role/del/{id}", method = RequestMethod.GET, produces="aplication/json")
    public String delRole(@PathVariable("id") Long id) {
    	roleService.delRole(id);
    	return "{ \"msg\" : \"success\" }";
    }
}
