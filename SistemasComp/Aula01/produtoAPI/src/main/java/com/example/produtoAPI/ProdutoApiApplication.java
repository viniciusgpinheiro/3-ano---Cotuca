package com.example.produtoAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ProdutoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProdutoApiApplication.class, args);
	}

}
