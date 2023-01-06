package com.projetmodule.gestiondestock.controller;

import com.projetmodule.gestiondestock.dto.auth.AuthenticationRequest;
import com.projetmodule.gestiondestock.dto.auth.AuthenticationResponse;
import com.projetmodule.gestiondestock.services.auth.ApplicationUserDetailsService;
import com.projetmodule.gestiondestock.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

import static com.projetmodule.gestiondestock.utils.Constants.APP_ROOT;

@RestController
@RequestMapping(value=APP_ROOT+"/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    private ApplicationUserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
       authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(
                       request.getLogin(),
                        request.getPassword()
                )
       );
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getLogin());
        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(AuthenticationResponse.builder().accessToken(jwt).build());

    }
}
