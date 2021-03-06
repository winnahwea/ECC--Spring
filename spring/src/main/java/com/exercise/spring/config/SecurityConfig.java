package com.exercise.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Bean
	PasswordEncoder bycryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();
		auth.inMemoryAuthentication()
		.withUser("user").password(encoder.encode("user")).roles("USER")
		.and()
		.withUser("admin").password(encoder.encode("admin")).roles("ADMIN");
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
		.csrf().disable()
		.authorizeRequests()
		.antMatchers(HttpMethod.GET,"/api/employee/all").hasAnyRole("ADMIN","USER")
		.antMatchers(HttpMethod.GET,"/api/employee/{employeeId}").hasAnyRole("ADMIN","USER")
		.antMatchers(HttpMethod.GET,"/api/employee/lastname").hasAnyRole("ADMIN","USER")
		.antMatchers(HttpMethod.POST,"/api/employee/add").hasRole("ADMIN")
		.antMatchers(HttpMethod.PUT,"/api/employee/update/{employeeId}").hasRole("ADMIN")
		.antMatchers(HttpMethod.DELETE,"/api/employee/delete/{employeeId}").hasRole("ADMIN")
		.antMatchers(HttpMethod.GET,"/api/ticket/all").hasAnyRole("ADMIN","USER")
		.antMatchers(HttpMethod.GET,"/api/ticket/{id}").hasAnyRole("ADMIN","USER")
		.antMatchers(HttpMethod.POST,"/api/ticket/add").hasRole("ADMIN")
		.antMatchers(HttpMethod.PUT,"/api/ticket/update/{id}").hasRole("ADMIN")
		.antMatchers(HttpMethod.DELETE,"/api/ticket/delete/{id}").hasRole("ADMIN")
		.antMatchers(HttpMethod.GET,"/api/department/all").hasAnyRole("ADMIN","USER")
		.antMatchers(HttpMethod.POST,"/api/department/add").hasRole("ADMIN")
		.antMatchers(HttpMethod.POST,"/api/department/addTo/{employeeId}/{departmentId}").hasRole("ADMIN")
		.antMatchers(HttpMethod.PUT,"/api/department/update/{departmentId}").hasRole("ADMIN")
		.antMatchers(HttpMethod.DELETE,"/api/department/delete/{departmentId}").hasRole("ADMIN")
		.antMatchers(HttpMethod.POST,"/api/department/deleteFrom/{employeeId}/{departmentId}").hasRole("ADMIN")
		.anyRequest().authenticated().and().httpBasic();
	}

}
