/**
 * project name : oauth2
 * Created by deva.jin on 2020/11/17.
 */


package com.deva.spring.oauth2.config;

import com.deva.spring.oauth2.dto.RoleType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

	private final CustomOAuth2UserService customOAuth2UserService;

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf()
				.disable()
				.headers().frameOptions().disable()
			.and()
				.authorizeRequests()
				.antMatchers( "/","/api/v1/**", "/webjars/**").permitAll()
				//.antMatchers("/index").hasRole(RoleType.USER.getRoleName())
				.anyRequest().authenticated()
				/*.and()
				.formLogin()
				.loginPage("/login")

				.usernameParameter("username")
				.passwordParameter("password")*/
			.and()
				.oauth2Login()
				.userInfoEndpoint()
				.userService(customOAuth2UserService)
			.and()
				.defaultSuccessUrl("/index");
	}
}
