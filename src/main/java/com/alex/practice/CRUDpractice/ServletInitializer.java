package com.alex.practice.CRUDpractice;

import com.alex.practice.CRUDpractice.CRUDPracticeApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class 	ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(CRUDPracticeApplication.class);
	}

}
