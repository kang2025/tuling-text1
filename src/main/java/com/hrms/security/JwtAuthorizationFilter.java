
package com.hrms.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import java.io.IOException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * JWT授权过滤器，继承自Spring Security的基本认证过滤器
 * 用于在HTTP请求过程中进行JWT token的验证和授权
 */
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    /**
     * 构造函数，初始化认证管理器
     * @param authManager 认证管理器，用于处理认证相关逻辑
     */
    public JwtAuthorizationFilter(AuthenticationManager authManager) {
        super(authManager);
    }

    /**
     * 执行过滤器的主要逻辑
     * 验证请求头中的JWT token，并设置认证信息
     * @param req HTTP请求对象，包含请求头等信息
     * @param res HTTP响应对象，用于处理响应逻辑
     * @param chain 过滤链，用于将请求传递给下一个过滤器或目标资源
     * @throws IOException 当读取请求信息时发生I/O错误
     * @throws ServletException 当过滤器遇到异常情况
     */
    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        // 获取请求头中的Authorization值
        String header = req.getHeader("Authorization");

        // 检查Authorization头是否存在且以"Bearer "开头
        if (header == null || !header.startsWith("Bearer ")) {
            // 如果不存在或格式不正确，则直接传递请求到下一个过滤器
            chain.doFilter(req, res);
            return;
        }

        // 尝试从请求中获取认证信息
        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

        // 设置当前请求的认证信息到SecurityContext中
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 传递请求到下一个过滤器
        chain.doFilter(req, res);
    }

    /**
     * 从请求中解析并获取认证信息
     * @param request HTTP请求对象，包含请求头等信息
     * @return 解析成功则返回认证信息对象，否则返回null
     */

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
    String token = request.getHeader("Authorization");
    if (token != null) {
    Claims claims = Jwts.parser()
    .setSigningKey("wwwdddd123")
    .parseClaimsJws(token.replace("Bearer ", ""))
    .getBody();

    // 添加角色信息
    List<SimpleGrantedAuthority> authorities = Arrays.stream(claims.get("roles").toString().split(","))
    .map(SimpleGrantedAuthority::new)
    .collect(Collectors.toList());

    return new UsernamePasswordAuthenticationToken(claims.getSubject(), null, authorities);
    }
    return null;
    }
}
