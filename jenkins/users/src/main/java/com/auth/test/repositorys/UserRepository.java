package com.auth.test.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.auth.test.models.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
	    @Query("SELECT u FROM User u WHERE u.username=(:username)")
	    User findByUsername(@Param("username") String username);
	    
	    @Query("SELECT u FROM User u WHERE u.id=(:id)")
	    User findById(@Param("id") Long id);
	    
	    void delete(Long id);
}
