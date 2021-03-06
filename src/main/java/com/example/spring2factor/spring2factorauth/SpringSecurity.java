package com.example.spring2factor.spring2factorauth;

import com.example.spring2factor.spring2factorauth.twofactorAuth.TOTPAuthenticationProvider;
import com.example.spring2factor.spring2factorauth.twofactorAuth.TOTPAuthenticator;
import com.example.spring2factor.spring2factorauth.twofactorAuth.TOTPWebAuthenticationDetailsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SpringSecurity extends WebSecurityConfigurerAdapter {

  @Autowired
  UserDetailsService userDetailsService;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    PasswordEncoder encoder = NoOpPasswordEncoder.getInstance();
    TOTPAuthenticationProvider authenticationProvider = new TOTPAuthenticationProvider();
    authenticationProvider.setPasswordEncoder(encoder);
    authenticationProvider.setUserDetailsService(userDetailsService);
    authenticationProvider.setTotpAuthenticator(new TOTPAuthenticator());
    auth.authenticationProvider(authenticationProvider);
  }

  @Bean
  public PasswordEncoder getPassword(){
    return NoOpPasswordEncoder.getInstance();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable().authorizeRequests()
        .antMatchers("/admin").hasRole("ADMIN")
        .antMatchers("/user").hasAnyRole("USER", "ADMIN")
        .antMatchers("/").permitAll()
        .antMatchers("/h2-console/**").permitAll()
        .and().formLogin().loginPage("/login").permitAll().authenticationDetailsSource(new TOTPWebAuthenticationDetailsSource());
    http.headers().frameOptions().disable();
  }
}
