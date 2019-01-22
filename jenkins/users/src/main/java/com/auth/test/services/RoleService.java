package com.auth.test.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.auth.test.models.Role;
import com.auth.test.repositorys.RoleRepository;

@Service
public class RoleService {
	@Autowired
	private RoleRepository roleRepository ;
	
	public Page<Role> listRole(Pageable pageable) {
		Page<Role> role = roleRepository.findAll(pageable);
		return role ;
	}
	public Role saveRole(Role param) {
		Role role = roleRepository.save(param);
		return role;
	}
	public Role getRole(Long id) {
		Role role = roleRepository.findOne(id);
		return role;
	}
	public void delRole(Long id) {
		roleRepository.delete(id);
	}
}
