package com.jurbin.werewolf.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication
@EnableMongoRepositories(basePackages = {"com.jurbin.werewolf.repository"})
@ComponentScan(basePackages = { "com.jurbin"})
public class WerewolfApplication {

	public static void main(String[] args) {
		SpringApplication.run(WerewolfApplication.class, args);
	}
}
