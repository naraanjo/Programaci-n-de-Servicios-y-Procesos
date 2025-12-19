package com.example.apiRest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.apiRest.entity.Producto;

@SpringBootApplication
@RestController
public class ApiRest {

	public static void main(String[] args) {
		SpringApplication.run(ApiRest.class, args);}
		
	
}
