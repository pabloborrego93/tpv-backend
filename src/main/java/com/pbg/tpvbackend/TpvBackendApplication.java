package com.pbg.tpvbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("config.*")
public class TpvBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(TpvBackendApplication.class, args);
	}

}
