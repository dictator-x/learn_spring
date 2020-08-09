package com.learn_spring_parent.oauth2server.service;

import com.learn_spring_parent.oauth2server.dao.SysUserDao;
import com.learn_spring_parent.oauth2server.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Transactional
public class UserService implements UserDetailsService {
    @Autowired
    SysUserDao sysUserDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            SysUser sysUser = sysUserDao.findByUsername(username);
            if ( sysUser == null ) return null;
            return sysUser;
        } catch ( Exception e ) {
            e.printStackTrace();
            return null;
        }

    }
}
