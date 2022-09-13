package com.wp.weatherapp.favouriteservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class WeatherAppFavouriteApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherAppFavouriteApplication.class, args);
	}

}
