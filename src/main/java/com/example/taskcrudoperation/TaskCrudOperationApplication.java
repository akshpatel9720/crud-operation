package com.example.taskcrudoperation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableJpaAuditing
public class TaskCrudOperationApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskCrudOperationApplication.class, args);
	}

}
