package com.advertise;

import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication
@EnableEurekaClient
public class OlxAdvertiseApplication {

	public static void main(String[] args) {
		SpringApplication.run(OlxAdvertiseApplication.class, args);
	}
	@Bean
	public RestTemplate getRestTemplate()
	{
		return new RestTemplate();
	}
	
	@Bean
	public Docket getDocket()
	{
		return new Docket(DocumentationType.SWAGGER_2)
		.select()
		.paths(PathSelectors.any())
		.apis(RequestHandlerSelectors.basePackage("com.advertise"))
		.build()
		.apiInfo(getApiInfo());
	}
	
	private ApiInfo getApiInfo()
	{
		return new ApiInfo(
				"Advertise Rest API Implementation",
				"API designed for Advertise details",
				"1.0.0",
				"http://www.zensar.com",
				new Contact("vinodh","http://www.advertise.com","advertise.k@zensar.com"),
				"GPL",
				"http://www.gpllicense.com",
				new ArrayList<>()
		
				);
	}


}
