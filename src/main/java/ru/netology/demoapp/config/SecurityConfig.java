package ru.netology.demoapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/persons/by-city").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(withDefaults());

        return http.build();
    }

    @Bean
    public UserDetailsService users() {
        UserDetails readUser = User.withDefaultPasswordEncoder()
                .username("read_user")
                .password("password")
                .authorities("READ")
                .build();

        UserDetails writeUser = User.withDefaultPasswordEncoder()
                .username("write_user")
                .password("password")
                .authorities("WRITE")
                .build();

        UserDetails deleteUser = User.withDefaultPasswordEncoder()
                .username("delete_user")
                .password("password")
                .authorities("DELETE")
                .build();

        return new InMemoryUserDetailsManager(readUser, writeUser, deleteUser);
    }
}
