package com.Generator.apirest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

//@ComponentScan(basePackages = {"com.Generator.apirest"})
@SpringBootApplication
public class ApiRestGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiRestGeneratorApplication.class, args);
//		SpringApplicationBuilder builder = new SpringApplicationBuilder(ApiRestGeneratorApplication.class);
//		builder.headless(false);
//		ConfigurableApplicationContext context = builder.run(args);
	}


}
// http://localhost:8888/ApiREST/Generator/swagger-ui/
// http://localhost:8888/ApiREST/Generator/#/