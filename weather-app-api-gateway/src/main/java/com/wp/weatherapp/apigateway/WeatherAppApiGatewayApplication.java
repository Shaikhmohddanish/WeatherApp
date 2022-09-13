package com.wp.weatherapp.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class WeatherAppApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherAppApiGatewayApplication.class, args);
	}

}
