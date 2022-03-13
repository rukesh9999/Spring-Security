package com.example.springsecurityinmemoryauth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
 
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.inMemoryAuthentication().withUser("nani").password("{noop}nani").authorities("student");
		auth.inMemoryAuthentication().withUser("amar").password("{noop}amar").authorities("employee");
		auth.inMemoryAuthentication().withUser("navya").password("{noop}navya").authorities("admin");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		
		//url-access type
		http.authorizeRequests()
		.antMatchers("/hrms/home").permitAll()
		.antMatchers("/hrms/welcome").authenticated()
		.antMatchers("/hrms/employee").hasAuthority("employee")
		.antMatchers("/hrms/student").hasAuthority("student")
		.antMatchers("/hrms/admin").hasAuthority("admin")
		.anyRequest().authenticated()
		//login form details
		.and()
		.formLogin()
		.defaultSuccessUrl("/hrms/welcome",true)
		
		//logout details
		.and()
		.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/hrms/logout"))
		
		//exception details
		.and()
		.exceptionHandling()
		.accessDeniedPage("/hrms/denied")
		
		;
		
		
		
	}
}
