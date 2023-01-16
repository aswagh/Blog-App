package com.project.blogApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.project.blogApp.repositories.UserRepository;

import org.modelmapper.ModelMapper;

@SpringBootApplication
public class BloggingApplicationApplication implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(BloggingApplicationApplication.class, args);
		System.out.println("blog app working fine");
	}

	@Override
	public void run(String... args) throws Exception {
//		System.out.println(this.passwordEncoder.encode("Yog@1234"));
//		System.out.println(userRepository.findByEmail("aswagh@gmail.com")); 
	}

}
