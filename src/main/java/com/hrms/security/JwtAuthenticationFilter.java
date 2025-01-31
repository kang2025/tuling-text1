package com.hrms.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrms.request.LoginRequest;
import com.hrms.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * JWT认证过滤器，继承自Spring Security的UsernamePasswordAuthenticationFilter
 * 用于处理用户名和密码的认证请求
 */
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        setFilterProcessesUrl("/api/auth/login"); // 设置登录URL
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            // 读取请求体并缓存
            String requestBody = new BufferedReader(new InputStreamReader(request.getInputStream()))
                    .lines().collect(Collectors.joining("\n"));
            logger.info("Request body: " + requestBody);

            // 将请求体解析为 LoginRequest 对象
            LoginRequest creds = objectMapper.readValue(requestBody, LoginRequest.class);
            logger.info("Attempting authentication for user: " + creds.getUsername());

            // 使用认证管理器进行认证
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getUsername(),
                            creds.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            logger.error("Failed to parse login request", e);
            throw new AuthenticationServiceException("Failed to parse login request", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        // 生成JWT令牌
        String token = JwtUtil.generateToken(authResult.getName());

        // 将令牌添加到响应头
        response.addHeader("Authorization", "Bearer " + token);

        // 返回JSON响应
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(Map.of(
                "message", "Login successful",
                "token", token
        )));

        // 继续执行过滤器链
        chain.doFilter(request, response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        // 认证失败时返回JSON响应
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(Map.of(
                "message", "Authentication failed",
                "error", failed.getMessage()
        )));
    }
}