package com.microservice.user.Config;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.microservice.user.Service.Implementation.UserDetailServiceImp;


@EnableWebSecurity
@Configuration
public class  SecurityConfig {


    @Autowired
    private UserDetailServiceImp uds;

    
    // create secuirty configureation class with new spring update
    
    @Bean
    PasswordEncoder encoder()  {
        return new BCryptPasswordEncoder();
    }
    
    
   @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{

        return config.getAuthenticationManager()
        ;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
            auth
            .userDetailsService(this.uds);
            // .passwordEncoder(this.encoder());
            
        }

        @Bean
     SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors()
                .and()
                .authorizeHttpRequests((req) -> 
                    req.requestMatchers("/user/protected").hasAuthority("USER")
                    .requestMatchers("/user/auth").hasAuthority("ADMIN")
                    .requestMatchers("/role/**","/token/**","/user/add").permitAll()
                    .anyRequest().authenticated()
                    );
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
        configuration.setExposedHeaders(Arrays.asList("x-auth-token"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
    
    



  

    
    
    
    
