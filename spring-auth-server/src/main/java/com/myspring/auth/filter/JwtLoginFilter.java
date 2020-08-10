package com.myspring.auth.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myspring.auth.config.RsaKeyProperty;
import com.myspring.auth.domain.SysRole;
import com.myspring.auth.domain.SysUser;
import com.learn_spring_parent.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private RsaKeyProperty prop;

    public JwtLoginFilter(AuthenticationManager authenticationManager, RsaKeyProperty prop) {
        this.authenticationManager = authenticationManager;
        this.prop = prop;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            SysUser sysUser = new ObjectMapper().readValue(request.getInputStream(), SysUser.class);
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                    sysUser.getUsername(), sysUser.getPassword());
            return authenticationManager.authenticate(authRequest);
        } catch (IOException e) {
            try {
                response.setContentType("application/json;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                PrintWriter out = response.getWriter();
                Map resultMap = new HashMap();
                resultMap.put("code", HttpServletResponse.SC_UNAUTHORIZED);
                resultMap.put("msg", "Username or Password error!!");
                out.write(new ObjectMapper().writeValueAsString(resultMap));
                out.flush();
                out.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        SysUser user = new SysUser();
        user.setUsername(authResult.getName());
        user.setRoles((List<SysRole>) authResult.getAuthorities());
        String token = JwtUtil.genToken(user, prop.getPrivateKey(),3600);
        System.out.println(token);
        response.addHeader("Authorization", "Bearer " + token);
        try {
            response.setContentType("application/json;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
            PrintWriter out = response.getWriter();
            Map resultMap = new HashMap();
            resultMap.put("code", HttpServletResponse.SC_OK);
            resultMap.put("msg", "Authentication Success");
            out.write(new ObjectMapper().writeValueAsString(resultMap));
            out.flush();
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
