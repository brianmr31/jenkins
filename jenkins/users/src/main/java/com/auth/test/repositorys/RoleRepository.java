package com.auth.test.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.auth.test.models.Role;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Role, Long> {

}
