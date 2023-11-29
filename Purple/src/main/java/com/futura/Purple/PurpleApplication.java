package com.futura.Purple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.futura.Purple.Controller.DeathClaimLeapDetailsController;

@SpringBootApplication
public class PurpleApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(PurpleApplication.class, args);
	}
	
	 @Bean
	   public WebMvcConfigurer corsConfigurer() {
	      return new WebMvcConfigurer() {
	         @Override
	         public void addCorsMappings(CorsRegistry registry) {
	            registry.addMapping("/**").allowedOrigins("*").allowedMethods("PUT", "DELETE","GET", "POST", "PATCH");
	         }
	      };
	   }

}
