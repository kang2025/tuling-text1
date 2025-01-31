package com.hrms.security;

import com.hrms.mapper.UserMapper;
import com.hrms.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.hrms.mapper.EmployeeMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 使用注入的 userMapper 实例来调用 findByUsername 方法，并处理 Optional 类型
        User user = userMapper.findByUsername(username)
                .orElseThrow(() -> {
                    logger.error("User not found with username: " + username);
                    return new UsernameNotFoundException("User not found with username: " + username);
                });

        // 打印日志，记录加载的用户信息
        logger.info("User found: " + user.getUsername());

        // 假设每个用户有一个角色列表，这里简单地使用一个固定的角色
        String roleString = user.getRole();
        List<SimpleGrantedAuthority> authorities;
        if (roleString != null && !roleString.isEmpty()) {
            authorities = Arrays.stream(roleString.split(","))
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role.trim()))
                    .collect(Collectors.toList());
        } else {
            authorities = Collections.emptyList();
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                !user.isDeleted(), // 如果用户被软删除，则禁用账户
                true,
                true,
                true,
                authorities
        );
    }
}