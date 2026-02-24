package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RestController
public class ProdutoApiApplication {

	@GetMapping("produtoAPI")
	public String Inicializacao() {
		
	}

	public static void main(String[] args) {
		SpringApplication.run(ProdutoApiApplication.class, args);
	}

}
