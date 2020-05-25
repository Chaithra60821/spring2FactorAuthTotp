package com.example.spring2factor.spring2factorauth.model;

import com.example.spring2factor.spring2factorauth.model.User;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails implements UserDetails {

  private String userName;
  private String password;
  private  Boolean active;
  private List<GrantedAuthority> grantedAuthorities;
  private String secret;

  public MyUserDetails(String userName){
    this.userName = userName;
  }

  public MyUserDetails(User user){
    this.userName = user.getUsername();
    this.password = user.getPassword();
    this.active = user.isActive();
    this.grantedAuthorities =
        Arrays.stream(user.getRoles().split(",")).map(SimpleGrantedAuthority::new).collect(
            Collectors.toList());
    this.secret = user.getSecret();
  }

  public MyUserDetails(){

  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.grantedAuthorities;
  }

  @Override
  public String getPassword() {
    return this.password;
  }

  @Override
  public String getUsername() {
    return userName;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return this.active;
  }

  public String getSecret() {
    return secret;
  }

  public void setSecret(String secret) {
    this.secret = secret;
  }
}