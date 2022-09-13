package com.wp.weatherapp.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class WeatherAppEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherAppEurekaServerApplication.class, args);
	}

}
