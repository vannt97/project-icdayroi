package com.vannt.projecticdayroi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ProjectIcdayroiApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProjectIcdayroiApplication.class, args);
	}
}
