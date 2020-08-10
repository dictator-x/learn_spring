package com.myspring.auth.service;

import com.myspring.auth.dao.SysUserDao;
import com.myspring.auth.domain.SysUser;
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
//            List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
//            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//            //UserDetails userDetails = new User(sysUser.getUsername(), "{noop}"+sysUser.getPassword(), authorities);
//            //UserDetails userDetails = new User(sysUser.getUsername(), sysUser.getPassword(), authorities);
//            UserDetails userDetails = new User(
//                    sysUser.getUsername(),
//                    sysUser.getPassword(),
//                    sysUser.getStatus() == 1 ? true : false,
//                    true,
//                    true,
//                    true,
//                    authorities
//            );
            return sysUser;
        } catch ( Exception e ) {
            e.printStackTrace();
            return null;
        }

    }
}
