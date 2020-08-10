package com.myspring.cloud.oauth2server.service;

import com.myspring.cloud.oauth2server.dao.SysUserDao;
import com.myspring.cloud.oauth2server.domain.SysUser;
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
