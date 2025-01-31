package com.hrms.mapper;

import com.hrms.pojo.Attendance;
import com.hrms.pojo.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;

import java.util.List;

@Mapper
public interface AttendanceMapper {

    List<Attendance> findByEmployee(@Param("employee") Employee employee);

    // 根据ID查找考勤记录的方法
    @Select("SELECT * FROM attendances WHERE id = #{id}")
    Attendance findById(@Param("id") Long id);

    // 保存考勤记录的方法
    @Insert("INSERT INTO attendance (employee_id, date, status) VALUES (#{employeeId}, #{date}, #{status})")
    void save(Attendance attendance);

    // 获取所有考勤记录的方法
    @Select("SELECT * FROM attendances")
    List<Attendance> selectAll();
    @Select("SELECT * FROM attendances WHERE employee_id = #{employeeId}")
    List<Attendance> findByEmployeeId(@Param("employeeId") Long employeeId);

}
