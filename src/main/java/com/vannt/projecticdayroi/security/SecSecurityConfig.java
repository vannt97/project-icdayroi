package com.vannt.projecticdayroi.security;

import ch.qos.logback.core.encoder.Encoder;
import com.vannt.projecticdayroi.jwt.JwtTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebSecurity
public class SecSecurityConfig {

//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        // InMemoryUserDetailsManager (see below)
//        UserDetails user1 = User.withUsername("user1")
//                .password((passwordEncoder().encode("user1Pass")))
//                .roles("USER")
//                .build();
//        UserDetails user2 = User.withUsername("user2")
//                .password((passwordEncoder().encode("user2Pass")))
//                .roles("USER")
//                .build();
//        UserDetails admin = User.withUsername("admin")
//                .password((passwordEncoder().encode("adminPass")))
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user1, user2, admin);
//    }

    @Autowired
    CustomAuthentProvider customAuthentProvider;

    @Autowired
    JwtTokenFilter jwtTokenFilter;
    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(customAuthentProvider);
        return authenticationManagerBuilder.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // http builder configurations for authorize requests and form login (see below)

        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/history-transaction").authenticated()
                .antMatchers("/api/signin/test").authenticated()
//                .antMatchers("/api/bill/**").authenticated()
                .anyRequest().permitAll()
                .and().build();

    }
}
