package com.recomsAI.security

import org.springframework.context.annotation.{Bean, Configuration}
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.{EnableWebSecurity, WebSecurityConfigurerAdapter}


@Configuration
@EnableWebSecurity
class SecurityConfig extends WebSecurityConfigurerAdapter {

  override protected def configure(http : HttpSecurity):Unit = {
    http
      .authorizeRequests()
      .antMatchers("/", "/login", "/oauth2/**").permitAll()
      .anyRequest().authenticated()
      .and()
      .oauth2Login()
      .defaultSuccessUrl("/home", true);
  }
}
