package com.recomsAI.security

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.{EnableWebSecurity, WebSecurityConfigurerAdapter}


@Configuration
@EnableWebSecurity
class SecurityConfig extends WebSecurityConfigurerAdapter {

  override protected def configure(http : HttpSecurity):Unit = {
    http
      .authorizeRequests()
      .antMatchers("/", "/css/**", "/js/**", "/api/v1/auth/status").permitAll()
      .anyRequest().authenticated()
      .and()
      .oauth2Login()
      .defaultSuccessUrl("/api/v1/signin", true)
      .and()
      .logout()
      .logoutUrl("/api/v1/signout")
      .logoutSuccessUrl("/")
      .invalidateHttpSession(true)
      .deleteCookies("JSESSIONID")
  }
}
