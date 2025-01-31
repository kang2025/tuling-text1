package com.hrms.service.impl;

import com.hrms.mapper.UserMapper;
import com.hrms.pojo.User;
import com.hrms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public List<User> findAll() {
        return userMapper.findAll(); // 假设 userMapper 中有 findAll 方法
    }
}