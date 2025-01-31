package com.hrms.controller;

import java.util.List;
import com.hrms.mapper.EmployeeMapper;
import com.hrms.pojo.Employee;
import com.hrms.pojo.JwtResponse;
import com.hrms.pojo.User;

import com.hrms.request.LoginRequest;
import com.hrms.response.MessageResponse;
import com.hrms.security.JwtTokenProvider;
import com.hrms.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        logger.info("Login request received: " + loginRequest.getUsername());
        System.out.println("===== 进入登录接口 =====");

        try {
            // 1. 手动查询用户
            User user = userService.findByUsername(loginRequest.getUsername())
                    .orElseThrow(() -> new BadCredentialsException("用户不存在"));

            // 2. 打印密码匹配结果
            boolean isPasswordMatch = encoder.matches(
                    loginRequest.getPassword(),
                    user.getPassword()
            );
            System.out.println("===== 调试信息开始 =====");
            System.out.println("用户名: " + loginRequest.getUsername());
            System.out.println("输入密码: " + loginRequest.getPassword());
            System.out.println("数据库密码哈希: " + user.getPassword());
            System.out.println("密码匹配结果: " + isPasswordMatch);
            System.out.println("===== 调试信息结束 =====");

            if (!isPasswordMatch) {
                throw new BadCredentialsException("用户名或密码错误");
            }

            // 3. 继续原有的认证流程
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtTokenProvider.generateToken(authentication);

            return ResponseEntity.ok()
                    .header("Authorization", "Bearer " + jwt)
                    .body(new JwtResponse(
                            jwt,
                            user.getId(),
                            user.getUsername(),
                            user.getRole()
                    ));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("用户名或密码错误");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser() {
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok(new MessageResponse("成功登出"));
    }

    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/users/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new BadCredentialsException("用户不存在"));
        return ResponseEntity.ok(user);
    }
}
