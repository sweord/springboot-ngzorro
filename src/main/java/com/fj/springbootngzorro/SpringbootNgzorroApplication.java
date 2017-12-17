package com.fj.springbootngzorro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.fj")
@EntityScan("com.fj.entity")
@EnableJpaRepositories("com.fj.dao")
public class SpringbootNgzorroApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootNgzorroApplication.class, args);
    }
}
