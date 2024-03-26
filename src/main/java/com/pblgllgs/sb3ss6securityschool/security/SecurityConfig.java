package com.pblgllgs.sb3ss6securityschool.security;
/*
 *
 * @author pblgl
 * Created on 25-03-2024
 *
 */

import com.pblgllgs.sb3ss6securityschool.enums.UserType;
import com.pblgllgs.sb3ss6securityschool.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthEntryPoint jwtAuthEntryPoint;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers("/api/public/**").permitAll()
                                .requestMatchers("/auth/**").permitAll()
                                .requestMatchers("/api/v1/admin/**").hasAuthority(UserType.ADMIN.toString())
                                .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(ex -> ex.authenticationEntryPoint(jwtAuthEntryPoint))
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

}
