package com.projetmodule.gestiondestock.config;

import com.projetmodule.gestiondestock.services.auth.ApplicationUserDetailsService;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.net.PasswordAuthentication;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity //activer la securite dans l'application
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
   @Autowired
   private ApplicationUserDetailsService applicationUserDetailsService;
    @Autowired
   private ApplicationRequestFilter applicationRequestFilter;
   @Override
   protected  void configure(AuthenticationManagerBuilder auth) throws Exception{
      auth.userDetailsService(applicationUserDetailsService)
              .passwordEncoder(passwordEncoder());
   }
   @Override
   protected void configure(HttpSecurity http) throws Exception{
      http.csrf().disable()
              .authorizeRequests()
              .antMatchers("/**/authenticate",
                      "/**/entreprises/create",
                      "/configuration/ui",
                      "/configuration/security",
                       "/webjars/**")
              .permitAll().anyRequest().authenticated()
              .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      ;
      http.addFilterBefore(applicationRequestFilter, UsernamePasswordAuthenticationFilter.class);
   }
   @Override
  @Bean
   public AuthenticationManager authenticationManager() throws Exception{
      return super.authenticationManagerBean();
   }

   @Bean
   public PasswordEncoder passwordEncoder(){
       return NoOpPasswordEncoder.getInstance();
   }


   }








