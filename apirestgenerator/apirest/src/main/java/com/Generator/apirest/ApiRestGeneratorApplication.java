package com.Generator.apirest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.Generator.apirest"})
public class ApiRestGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiRestGeneratorApplication.class, args);
//		SpringApplicationBuilder builder = new SpringApplicationBuilder(ApiRestGeneratorApplication.class);
//		builder.headless(false);
//		ConfigurableApplicationContext context = builder.run(args);
	}


}
