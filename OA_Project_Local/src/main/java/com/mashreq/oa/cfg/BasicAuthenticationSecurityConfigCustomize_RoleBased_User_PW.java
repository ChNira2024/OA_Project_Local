//package com.mashreq.oa.cfg;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//@EnableWebSecurity
//public class BasicAuthenticationSecurityConfigCustomize_RoleBased_User_PW extends WebSecurityConfigurerAdapter
//{
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception 
//	{
//		auth.inMemoryAuthentication().withUser("prem").password("premc").roles("CUSTOMER");
//		auth.inMemoryAuthentication().withUser("lokesh").password("lokeshb").roles("CUSTOMER");
//		auth.inMemoryAuthentication().withUser("niranjana").password("niranjanac").roles("MANAGER");
//	}
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception 
//	{
//		http.authorizeRequests()
//		       .antMatchers("/buy").hasRole("CUSTOMER")
//		       .antMatchers("/visit").hasRole("CUSTOMER")
//		       .antMatchers("/dataSave").hasRole("MANAGER")
//		        .anyRequest()
//		        .authenticated()
//		        .and()
//		        .httpBasic();
//	}
//
//	@Bean
//	public PasswordEncoder passwordEncoder()  //This method is mandatory. Without this method error came:There is no PasswordEncoder mapped for the id "null"
//	{                                                                                                          //because SpringBoot takes password as Encode format
//		return NoOpPasswordEncoder.getInstance();
//	}
//}
