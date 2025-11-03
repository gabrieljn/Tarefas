package com.tarefas.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class RotasConfig {

    @Bean
    List<String> rotasPublicas() {
        return List.of(
            "/login/**",
            "/usuario/**",
            "/h2-console/**"
        );
    }
    
	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}