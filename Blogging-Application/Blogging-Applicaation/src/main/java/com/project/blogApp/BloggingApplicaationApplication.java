package com.project.blogApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.project.blogApp.repositories.UserRepository;

import org.modelmapper.ModelMapper;

@SpringBootApplication
public class BloggingApplicaationApplication implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(BloggingApplicaationApplication.class, args);
		System.out.println("blog app working fine");
	}

	@Override
	public void run(String... args) throws Exception {
//		System.out.println(this.passwordEncoder.encode("Yog@1234"));
//		System.out.println(userRepository.findByEmail("aswagh@gmail.com")); 
	}

}
