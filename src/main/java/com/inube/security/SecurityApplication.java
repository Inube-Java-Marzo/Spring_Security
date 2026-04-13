package com.inube.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SecurityApplication {

	public static void main(String[] args) {
		System.out.println(new BCryptPasswordEncoder().encode("test"));
		SpringApplication.run(SecurityApplication.class, args);
	}

}
