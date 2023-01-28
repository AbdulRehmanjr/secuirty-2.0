package com.microservice.apigateway.configuration.Jwt;

import java.io.IOException;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.microservice.apigateway.Api.UserDetail;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{


    private Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Autowired
    private UserDetail userDetails;
    @Autowired
    private jwtUtils jwtUtil;

        @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
    
        log.info("Request uri: {}",request.getRequestURI());
        log.info("Request type: {}",request.getContentType());
        log.info("Request Mehod: {}",request.getMethod());

        final String RequestTokenHeader = request.getHeader("Authorization");
    
        String username = null;
        String jwttoken = null;

        if(RequestTokenHeader != null && RequestTokenHeader.startsWith("Bearer ")){
            jwttoken = RequestTokenHeader.substring(7);
    
            try{
            username = this.jwtUtil.extractUsername(jwttoken);
            
            }catch(Exception e){
                log.error("Cannot extract username from token / expirerd token");
            }

        }else{
            log.error("Invalid token, not start with bearer string");
        }

        // validate token
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            final UserDetails userDetail = this.userDetails.getUserDetails(username);
            // log.info("userDetails: "+userDetails.toString());
            log.info("User: "+username+" is authenticated");
            if(this.jwtUtil.validateToken(jwttoken, userDetail)){
                // token is valid
                log.info("authority {}",userDetail.getAuthorities());
                UsernamePasswordAuthenticationToken uPAT = new UsernamePasswordAuthenticationToken(userDetail,null,userDetail.getAuthorities());
                uPAT.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(uPAT);
            }
            
        }else{
            log.error("Token not valide");
        }        
    filterChain.doFilter(request, response);
    
    log.info("Response {}",response.getContentType());
    log.info("Body: {}",response.getOutputStream());
    
    }

        
}
    
        

    

