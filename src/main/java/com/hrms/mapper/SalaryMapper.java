package com.hrms.mapper;

import com.hrms.pojo.Employee;
import com.hrms.pojo.Salary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SalaryMapper {

    List<Salary> findByEmployee(@Param("employee") Employee employee);
    @Select("SELECT * FROM salaries WHERE employee_id = #{employeeId}")
    List<Salary> findByEmployeeId(@Param("employeeId") Long employeeId);

}
