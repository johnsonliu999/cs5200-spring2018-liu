package edu.northeastern.cs5200.cs5200spring2018liu;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(edu.northeastern.cs5200.cs5200spring2018liu.Cs5200Spring2018LiuApplication.class);
	}

}
