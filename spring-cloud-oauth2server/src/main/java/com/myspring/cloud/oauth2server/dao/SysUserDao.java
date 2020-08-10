package com.myspring.cloud.oauth2server.dao;

import com.myspring.cloud.oauth2server.domain.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysUserDao extends JpaRepository<SysUser, Integer> {
    SysUser findByUsername(String username);
}
