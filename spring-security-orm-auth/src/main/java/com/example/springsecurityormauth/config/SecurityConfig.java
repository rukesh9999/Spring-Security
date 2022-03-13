package com.example.springsecurityormauth.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
   
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		 auth.jdbcAuthentication()
		 .dataSource(dataSource)
		 .usersByUsernameQuery("select user_name,user_password,enabled from user where user_name=?")
		 .authoritiesByUsernameQuery("select user_name,user_role from user where user_name=?")
		 .passwordEncoder(passwordEncoder)
		;
		
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
