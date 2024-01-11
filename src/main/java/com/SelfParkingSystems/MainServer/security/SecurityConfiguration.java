package com.SelfParkingSystems.MainServer.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JWTAuthenticationFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers(
                        "/account/register",
                        "/account/login",
                        "/test/visitor",
                        "/locations/**"
                ).permitAll()
                .requestMatchers(
                        "/account/**",
                        "/test/user"
                ).hasAnyAuthority("USER","STAFF","OWNER","ADMIN")
                .requestMatchers(
                        "/test/staff",
                        "/manage/parks/show/**"
                ).hasAnyAuthority("STAFF","OWNER","ADMIN")
                .requestMatchers(
                        "/test/owner",
                        "/account/rise/staff",
                        "manage/parks/**"
                ).hasAnyAuthority("ADMIN","OWNER")
                .requestMatchers(
                        "/test/admin",
                        "/account/rise/owner"
                ).hasAuthority("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
}
