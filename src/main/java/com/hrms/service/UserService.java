package com.hrms.service;

import com.hrms.pojo.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username);

    List<User> findAll();
}
