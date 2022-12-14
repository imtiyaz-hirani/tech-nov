package com.springrest.employeecms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.springrest.employeecms.service.MyUserDetailsService;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{	 
	
	@Autowired
	 private MyUserDetailsService myUserDetailsService;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		 /* Define User Info 
		  a. In Memory (Hard Coded) 
		  b. From DB 
		 */
		/* 	
		.inMemoryAuthentication()
			.passwordEncoder(getPasswordEncoder())
			.withUser("harry@gmail.com").password(getPasswordEncoder().encode("potter@123")).authorities("EMPLOYEE")
			.and()
			.withUser("ronald@gmail.com").password(getPasswordEncoder().encode("weasley@123")).authorities("EMPLOYEE")
			.and()
			.withUser("albus@gmail.com").password(getPasswordEncoder().encode("albus@123")).authorities("MANAGER");
		*/
		
		auth.authenticationProvider(customProvider());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http.authorizeRequests()
		 .antMatchers(HttpMethod.GET,"/api/manager/all").permitAll()
		 .antMatchers(HttpMethod.GET,"/api/manager/auth/hello").authenticated()
		 .antMatchers(HttpMethod.GET,"/api/manager/auth/role/hello").hasAuthority("MANAGER")
		 .antMatchers(HttpMethod.PUT,"/api/employee/status/{status}/{id}").hasAuthority("MANAGER")
		 .antMatchers(HttpMethod.GET,"/api/user/login").authenticated()
		 .antMatchers(HttpMethod.POST,"/api/ticket/add").hasAuthority("EMPLOYEE")
		 .antMatchers(HttpMethod.POST,"/api/leave/add").hasAuthority("EMPLOYEE")
		 .antMatchers(HttpMethod.GET,"/api/employee/details").hasAuthority("EMPLOYEE")
		 .antMatchers(HttpMethod.GET,"/api/loan/apply/{lid}/{amount}").hasAuthority("AH")
		 .anyRequest().permitAll()
		 .and()
		 .httpBasic()
		 .and()
		 .csrf().disable();
	}
	
	@Bean //that allows you to autowire to PasswordEncoder from anywhere in the APP
	public PasswordEncoder getPasswordEncoder(){
		return new BCryptPasswordEncoder(); 
	}
	
	DaoAuthenticationProvider customProvider(){
		DaoAuthenticationProvider dao = new DaoAuthenticationProvider(); 
		//password Encoder info 
		dao.setPasswordEncoder(getPasswordEncoder());
		//give DB info
		dao.setUserDetailsService(myUserDetailsService);
		
		
		return dao;
	}
}
