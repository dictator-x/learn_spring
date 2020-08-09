package com.learn_spring_parent.oauth2server.dao;

import com.learn_spring_parent.oauth2server.domain.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysUserDao extends JpaRepository<SysUser, Integer> {
    SysUser findByUsername(String username);
}
