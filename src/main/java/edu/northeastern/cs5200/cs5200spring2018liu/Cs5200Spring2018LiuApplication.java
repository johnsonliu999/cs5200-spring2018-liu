package edu.northeastern.cs5200.cs5200spring2018liu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class Cs5200Spring2018LiuApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(Cs5200Spring2018LiuApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(Cs5200Spring2018LiuApplication.class, args);
	}
}
