package com.tom.mycat.repository;

import com.tom.mycat.entity.Role;
import com.tom.mycat.entity.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(RoleType roleType);
}
