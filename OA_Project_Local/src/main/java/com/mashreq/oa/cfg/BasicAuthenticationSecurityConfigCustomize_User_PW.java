/*
package com.mashreq.oa.cfg;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class BasicAuthenticationSecurityConfigCustomize_User_PW extends WebSecurityConfigurerAdapter
{
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception 
	{
		auth.inMemoryAuthentication().withUser("sisu").password("sisuna").roles("USER");
		auth.inMemoryAuthentication().withUser("RAJA").password("rani").roles("ADMIN");
	}

	@Bean
	public PasswordEncoder passwordEncoder()  //This method is mandatory. Without this method error came:There is no PasswordEncoder mapped for the id "null"
	{                                                                                                          //because SpringBoot takes password as Encode format
		return NoOpPasswordEncoder.getInstance();
	}
}
*/
