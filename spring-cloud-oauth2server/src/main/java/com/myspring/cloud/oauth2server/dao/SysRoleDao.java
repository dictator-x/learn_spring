package com.myspring.cloud.oauth2server.dao;

import com.myspring.cloud.oauth2server.domain.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysRoleDao extends JpaRepository<SysRole, Integer> {
}
