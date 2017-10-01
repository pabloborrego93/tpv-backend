package com.pbg.tpvBackEnd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pbg.tpvBackEnd.dao.security.RoleDao;

@SpringBootApplication
public class TpvBackEndApplication implements CommandLineRunner {

	@Autowired
	RoleDao roleDao;
	
	public static void main(String[] args) {
		SpringApplication.run(TpvBackEndApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
	}
}
