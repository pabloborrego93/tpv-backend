package com.pbg.tpvbackend.dao.security;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pbg.tpvbackend.model.security.Role;
import com.pbg.tpvbackend.model.security.RoleName;

@Repository
public interface RoleDao extends CrudRepository<Role, Integer> {
	Role findByName(RoleName name);
}
