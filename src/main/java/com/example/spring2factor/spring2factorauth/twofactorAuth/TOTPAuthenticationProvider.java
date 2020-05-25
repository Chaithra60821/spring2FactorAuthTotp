package com.example.spring2factor.spring2factorauth.twofactorAuth;

import com.example.spring2factor.spring2factorauth.model.MyUserDetails;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

public class TOTPAuthenticationProvider extends DaoAuthenticationProvider {
  private TOTPAuthenticator totpAuthenticator;

  @Override
  protected void additionalAuthenticationChecks(UserDetails userDetails,
      UsernamePasswordAuthenticationToken authentication)
      throws AuthenticationException {

    super.additionalAuthenticationChecks(userDetails, authentication);

    if (authentication.getDetails() instanceof TOTPWebAuthenticationDetails) {
      String secret = ((MyUserDetails) userDetails).getSecret();

      if (StringUtils.hasText(secret)) {
        Integer totpKey = ((TOTPWebAuthenticationDetails) authentication
            .getDetails()).getTotpKey();
        if (totpKey != null) {
          try {
            if (!totpAuthenticator.verifyCode(secret, totpKey, 2)) {
              throw new BadCredentialsException("Invalid TOTP code");
            }
          } catch (InvalidKeyException | NoSuchAlgorithmException e) {
            throw new InternalAuthenticationServiceException("TOTP code verification failed", e);
          }
        } else {
          throw new RuntimeException("TOTP code is mandatory");
        }
      }
    }
  }

  public TOTPAuthenticator getTotpAuthenticator() {
    return totpAuthenticator;
  }

  public void setTotpAuthenticator(TOTPAuthenticator totpAuthenticator) {
    this.totpAuthenticator = totpAuthenticator;
  }
}
