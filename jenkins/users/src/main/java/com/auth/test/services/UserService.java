package com.auth.test.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth.test.models.Role;
import com.auth.test.models.User;
import com.auth.test.repositorys.RoleRepository;
import com.auth.test.repositorys.UserRepository;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository ;
	@Autowired
	private RoleRepository roleRepository ;
	
	public User getUserByUsername(String username) {
		User user = userRepository.findByUsername(username);
		return user;
	}
	public Page<User> listUser(Pageable pageable) {
		Page<User> users = userRepository.findAll(pageable);
		return users ;
	}
	public User saveUser(User param) {
		System.out.println(param.getPassword());
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(16);
		String test = passwordEncoder.encode(param.getPassword());
		System.out.println(test);
		param.setPassword(passwordEncoder.encode(param.getPassword()));
		User user = userRepository.save(param);
		return user;
	}
	public User getUser(Long id) {
		User user = userRepository.findById(id);
		return user;
	}
	public void delUser(Long id) {
		userRepository.delete(id);
	}
	public void setUserRole(Long id, List<Long> roles) {
		User user = userRepository.findOne(id);
		Set<Role> tmpRoles = new HashSet<Role>();
		Role role = null ;
    	for(int i =0; i< roles.size() ;i++) {
    		role = roleRepository.findOne(roles.get(i));
    		tmpRoles.add(role);
    	}
    	user.setRoles(tmpRoles);
    	userRepository.save(user);
	}
	@Override
	public org.springframework.security.core.userdetails.User loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Collection<GrantedAuthority> grantedAuthoritiesList = new ArrayList<>();
		User user = userRepository.findByUsername(username);
		GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_SYSTEMADMIN");
		grantedAuthoritiesList.add(grantedAuthority);
		System.out.println( user.getPassword() );
		System.out.println( user.getUsername() );
		org.springframework.security.core.userdetails.User userEntity = 
				new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthoritiesList) ;
		return userEntity;
	}
}
