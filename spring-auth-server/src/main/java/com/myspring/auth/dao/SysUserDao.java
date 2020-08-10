package com.myspring.auth.dao;

import com.myspring.auth.domain.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysUserDao extends JpaRepository<SysUser, Integer> {
    SysUser findByUsername(String username);
}
