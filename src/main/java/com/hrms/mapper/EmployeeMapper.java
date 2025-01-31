package com.hrms.mapper;

import com.hrms.pojo.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

@Mapper
public interface EmployeeMapper {

    Optional<Employee> findByEmail(@Param("email") String email);

    Employee findByEmailWithTrainingActivities(@Param("email") String email);

    Employee save(Employee employee);

    Employee findByname(@Param("name") String name);
    void update(Employee employee);

    void deleteById(@Param("id") Long id);
    @Select("SELECT * FROM employees")
    List<Employee> selectAll();
    Optional<Employee> findById(Long id);
}
