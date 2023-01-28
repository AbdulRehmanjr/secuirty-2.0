package com.microservice.apigateway.configuration.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;



// make a class usng spring secuirty 6.0.1 and make some methods of secuirty
@Configuration
@EnableWebSecurity
public class SecuirtyConfig{
    
    // @Bean
    // public PasswordEncoder passwordEncoder()  {
    //     return new BCryptPasswordEncoder();
    // }
    
    
//    @Bean
//     public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{

//         return config.getAuthenticationManager();
//     }
    // @Autowired
    //     public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    //         auth
    //         .userDetailsService(this.uds);

    //     }
    
    /**
     * @param http
     * @return
     * @throws Exception
     */
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
        .csrf().disable()
        .cors()
        .and()
        .authorizeHttpRequests(authorize -> authorize.requestMatchers("/api/v1/hotel/**").permitAll())
        .securityMatcher("/api/v1/ratings/all")                                   
		.authorizeHttpRequests(authorize -> authorize.anyRequest().hasAuthority("ADMIN"))
        .securityMatcher("/api/v1/users/**")
        .authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll());
        // .authorizeRequests(authorize -> authorize
		// 	.anyRequest().authenticated()
		// )
        // .requestMatchers("/user/protected","/user/all","/role/add").hasAuthority("ADMIN")
        // .anyRequest()
        // .authenticated();
        // .and()
        // .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
        // .and()
        // .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        // .and()
        // .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    
}    

        
               

 