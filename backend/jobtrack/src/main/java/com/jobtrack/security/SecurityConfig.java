package com.jobtrack.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {
	
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
		this.jwtAuthenticationFilter=jwtAuthenticationFilter;
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		
		http
		  .csrf(csrf -> csrf.disable())
		  .cors(cors -> cors.configurationSource(corsConfigurationSource()))
		  .addFilterBefore(
				  jwtAuthenticationFilter,
				  UsernamePasswordAuthenticationFilter.class)
	        .authorizeHttpRequests(auth -> auth
	           .requestMatchers(HttpMethod.POST,"/api/users").permitAll()
	           .requestMatchers("/api/auth/**").permitAll()
	           
	           //
	           .requestMatchers(HttpMethod.OPTIONS,"/**").permitAll()
	           
	           //Job viewing or searching  USER OR ADMIN
	           .requestMatchers(HttpMethod.GET,"/api/jobs/**").authenticated()
	           
	           
	           // Job Management ADMIN ONLY 
	           
	           .requestMatchers(HttpMethod.POST,"/api/jobs/**").hasAuthority("ROLE_ADMIN")
	           .requestMatchers(HttpMethod.PUT,"/api/jobs/**").hasAuthority("ROLE_ADMIN")
	           .requestMatchers(HttpMethod.DELETE,"/api/jobs/**").hasAuthority("ROLE_ADMIN")
	           
	           
	           .requestMatchers("/api/admin/**").hasAuthority("ROLE_ADMIN")
	           .requestMatchers(HttpMethod.POST, "/api/applications").hasAuthority("ROLE_USER")
	           .requestMatchers(HttpMethod.GET, "/api/applications").hasAuthority("ROLE_ADMIN")
	           .requestMatchers(HttpMethod.GET, "/api/applications/my").authenticated()
	           .requestMatchers(HttpMethod.PUT, "/api/applications/*/status").hasAuthority("ROLE_ADMIN")
	           .requestMatchers("/api/users/profile").authenticated()
	           .anyRequest().authenticated()
	        );
		return http.build();
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		
		CorsConfiguration configuration = new CorsConfiguration();
		
		configuration.setAllowedOrigins(
				List.of("http://localhost:5173"));
		
		configuration.setAllowedMethods(List.of("GET","POST","PUT","DELETE","OPTIONS"));
		
		configuration.setAllowedHeaders(
				List.of("*"));
		
		configuration.setAllowCredentials(true);
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		
		source.registerCorsConfiguration("/**",configuration);
		
		return source;
	}
	
	}


