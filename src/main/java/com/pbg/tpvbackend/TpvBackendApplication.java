package com.pbg.tpvbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.pbg.tpvbackend.dao.security.RoleDao;
import com.pbg.tpvbackend.model.security.Role;
import com.pbg.tpvbackend.model.security.RoleName;

@SpringBootApplication
@EnableAutoConfiguration(exclude = { ErrorMvcAutoConfiguration.class })
public class TpvBackendApplication extends SpringBootServletInitializer implements CommandLineRunner {

	@Autowired
	RoleDao roleDao;

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(TpvBackendApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(TpvBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		for (RoleName roleName : RoleName.values()) {
			Role role = roleDao.findByName(roleName);
			if (role == null) {
				role = new Role();
				role.setName(roleName);
				roleDao.save(role);
			}
		}
	}

}
