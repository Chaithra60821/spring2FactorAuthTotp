package com.example.spring2factor.spring2factorauth.twofactorAuth;

import javax.servlet.http.HttpServletRequest;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

//we need to extend the authentication details source to return our TOTPWebAuthenticationDetails
public class TOTPWebAuthenticationDetailsSource extends WebAuthenticationDetailsSource {

  @Override
  public TOTPWebAuthenticationDetails buildDetails(HttpServletRequest request) {
    return new TOTPWebAuthenticationDetails(request);
  }

}
