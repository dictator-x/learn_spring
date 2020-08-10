package com.myspring.auth.dao;

import com.myspring.auth.domain.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysRoleDao extends JpaRepository<SysRole, Integer> {
}
