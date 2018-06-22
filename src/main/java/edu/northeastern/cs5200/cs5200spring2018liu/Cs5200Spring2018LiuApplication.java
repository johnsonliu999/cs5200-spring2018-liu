package edu.northeastern.cs5200.cs5200spring2018liu;

import edu.northeastern.cs5200.cs5200spring2018liu.daos.DeveloperDao;
import edu.northeastern.cs5200.cs5200spring2018liu.models.Developer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.sql.Date;

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
