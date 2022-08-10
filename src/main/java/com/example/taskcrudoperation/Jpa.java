package com.example.taskcrudoperation;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.taskcrudoperation.repository")
public class Jpa {
}
