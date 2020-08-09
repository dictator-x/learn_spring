package com.learn_spring_parent.auth.dao;

import com.learn_spring_parent.auth.domain.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysRoleDao extends JpaRepository<SysRole, Integer> {
}
