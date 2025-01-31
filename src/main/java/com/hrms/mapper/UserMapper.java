package com.hrms.mapper;

import com.hrms.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {
    Optional<User> findByUsername(@Param("username") String username);

    List<User> findAll();
}
