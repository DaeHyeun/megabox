package com.teamproject.megabox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MegaboxApplication {

	public static void main(String[] args) {
		SpringApplication.run(MegaboxApplication.class, args);
	}

}
