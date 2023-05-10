package com.stock.inventario.filters;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stock.inventario.auth.services.AuthService;
import com.stock.inventario.auth.services.JwtService;
import com.stock.inventario.users.models.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthService authService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");
        Map<String, Object> errorDetails = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();

        try{
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
                String token = authorizationHeader.substring(7);
                String userId = jwtService.validateToken(token);
                User user = authService.getById(userId);

                if (user != null && SecurityContextHolder.getContext().getAuthentication() == null){
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            user, null, user.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
            filterChain.doFilter(request, response);
        }catch (JWTDecodeException ex){
            handleException(ex, HttpStatus.UNAUTHORIZED, "Token Invalido", "INVALID_TOKEN", response, mapper);
        }
        catch (TokenExpiredException ex){
            handleException(ex, HttpStatus.UNAUTHORIZED, "Token Expirado", "EXPIRED_TOKEN", response, mapper);
        }catch (Exception ex){
            filterChain.doFilter(request, response);
        }
    }

    private void handleException(Exception ex, HttpStatus httpStatus, String message, String code, HttpServletResponse response,
                                 ObjectMapper mapper) throws IOException {
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("message", message);
        errorDetails.put("code", code);
        response.setStatus(httpStatus.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        mapper.writeValue(response.getWriter(), errorDetails);
    }
}
