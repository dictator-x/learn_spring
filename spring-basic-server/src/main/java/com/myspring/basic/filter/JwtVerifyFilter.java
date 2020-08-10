package com.myspring.basic.filter;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.myspring.basic.config.RsaKeyProperty;
import com.myspring.basic.domain.SysUser;
import com.myspring.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class JwtVerifyFilter extends BasicAuthenticationFilter {

    private RsaKeyProperty prop;

    public JwtVerifyFilter(AuthenticationManager authenticationManager, RsaKeyProperty prop) {
        super(authenticationManager);
        this.prop = prop;
    }

    @Override
    public void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String header = request.getHeader("Authorization");
        if ( header == null || ! header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            response.setContentType("application/json;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            PrintWriter out = response.getWriter();
            Map resultMap = new HashMap();
            resultMap.put("code", HttpServletResponse.SC_FORBIDDEN);
            resultMap.put("msg", "Please Login");
            out.write(new ObjectMapper().writeValueAsString(resultMap));
            out.flush();
            out.close();
        } else {
            String token = header.replace("Bearer ", "");
            JwtUtil.PayLoad<SysUser> payLoad = JwtUtil.getPayLoadFromToken(token, prop.getPublicKey(), SysUser.class);
            SysUser user = payLoad.getUserInfo();
            if ( user != null ) {
                UsernamePasswordAuthenticationToken authResult = new UsernamePasswordAuthenticationToken(user.getUsername(), null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authResult);
                chain.doFilter(request, response);
            }
        }
    }
}
