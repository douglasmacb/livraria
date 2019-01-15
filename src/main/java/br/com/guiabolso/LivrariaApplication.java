package br.com.guiabolso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Configuration
@EntityScan(basePackages = { "br.com.guiabolso.domain" })
@EnableJpaRepositories(basePackages = { "br.com.guiabolso.repositories" })
@ComponentScan(basePackages = { "br.com.guiabolso.controllers", "br.com.guiabolso.services" })
public class LivrariaApplication {

	public static void main(String[] args) {
		SpringApplication.run(LivrariaApplication.class, args);
	}

}
