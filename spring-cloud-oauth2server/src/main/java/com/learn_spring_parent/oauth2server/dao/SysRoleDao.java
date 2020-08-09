package com.learn_spring_parent.oauth2server.dao;

import com.learn_spring_parent.oauth2server.domain.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysRoleDao extends JpaRepository<SysRole, Integer> {
}
