package com.project.blogApp.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.project.blogApp.security.CustomUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

//	@Autowired
//	private CustomUserDetailService customUserDetailService;
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeRequests()
//		.antMatchers("/api/user/**").hasRole("ADMIN")
//		.antMatchers("/api/post/**","/api/category/** ").hasRole("NORMAL")
		.anyRequest()
		.authenticated()
		.and()
		.formLogin()
		//.loginPage("/signin")
		//.loginProcessingUrrl
		//			.httpBasic()
		//
		;
		
		return http.build();
		
	}
	
//	@Bean
//	public UserDetailsService userDetailsService() {
//		
//	}
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		
//		auth.userDetailsService(this.customUserDetailService).passwordEncoder(passwordEncoder());
//	}
//		

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
				
	}
	
	// For in memory data		
//	auth.inMemoryAuthentication()
//	.withUser("admin").password(this.passwordEncoder().encode("atul")).roles("ADMIN");
//	auth.inMemoryAuthentication()
//	.withUser("user").password(this.passwordEncoder().encode("wagh")).roles("NORMAL");

//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//
//			http
//			.csrf().disable()
//			.authorizeRequests()
////			.antMatchers("/api/user/**").hasRole("ADMIN")
////			.antMatchers("/api/post/**","/api/category/** ").hasRole("NORMAL")
//			.anyRequest()
//			.authenticated()
//			.and()
//			.formLogin()
//			//.loginPage("/signin")
//			//.loginProcessingUrrl
//			//			.httpBasic()
//			//
//			;
//	
//	}

	
	
}
  