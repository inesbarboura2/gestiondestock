package com.projetmodule.gestiondestock.config;

import com.projetmodule.gestiondestock.services.auth.ApplicationUserDetailsService;
import com.projetmodule.gestiondestock.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
@Slf4j
public class ApplicationRequestFilter extends OncePerRequestFilter {
    @Autowired
JwtUtil jwtUtil;
    @Autowired
    private ApplicationUserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        String username=null;
        String jwt = null;
        String idEntreprise;

        if(authHeader!= null && authHeader.startsWith("Bearer")){
           jwt = authHeader.substring(7);
            username=jwtUtil.extractUsername(jwt);
            idEntreprise=jwtUtil.extractIdEntreprise(jwt);
        }
        if(username != null
                && SecurityContextHolder.getContext()
                .getAuthentication() == null){
            UserDetails userDetails = this.userDetailsService.
                    loadUserByUsername(username);
            if(jwtUtil.validateToken(jwt , userDetails)){
                UsernamePasswordAuthenticationToken
                        usernamePasswordAuthenticationToken
                        = new UsernamePasswordAuthenticationToken
                        (userDetails , null,
                                userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource()
                        .buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }

        }

        chain.doFilter(request , response);
    }
}
