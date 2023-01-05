package com.secuirty.demo.config;

import java.io.IOException;




import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException {
                 // This is invoked when user tries to access a secured REST resource without supplying any credentials  
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        
    }
    
}
