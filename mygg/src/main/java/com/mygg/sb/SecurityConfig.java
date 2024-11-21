package com.mygg.sb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import lombok.AllArgsConstructor;

@EnableWebSecurity
@AllArgsConstructor
@Configuration
//Spring Security 설정(비활성화)
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/").permitAll()
                .requestMatchers("/static/**").permitAll()
                .requestMatchers("/api/**").permitAll()
                .requestMatchers("/manifest.json").permitAll()
                .requestMatchers("/favicon.ico").permitAll()
                .requestMatchers("/logo192.png").permitAll()
                .requestMatchers("/**").permitAll()  // 모든 정적 리소스 허용
            )
            .formLogin().disable()
            .headers(headers -> headers
                .frameOptions().disable()  // iframe 허용
            );
            
        return http.build();
    }
}
