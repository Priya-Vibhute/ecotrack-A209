package com.learn.ecotrack.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.ecotrack.entities.Role;
import com.learn.ecotrack.enums.AppRole;

public interface RoleRepository extends JpaRepository<Role, Integer>{
	
     Optional<Role>	findByRoleName(AppRole appRole);
     

}
