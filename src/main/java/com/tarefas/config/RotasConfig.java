package com.tarefas.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
public class RotasConfig {

	@Bean
	List<RequestMatcher> rotasPublicas() {
		return List.of(new AntPathRequestMatcher("/login/**", "POST"), new AntPathRequestMatcher("/h2-console/**"),
				new AntPathRequestMatcher("/usuario/**", "POST"));
	}

	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}