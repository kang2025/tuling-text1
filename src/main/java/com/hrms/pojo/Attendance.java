package com.hrms.pojo;

import com.hrms.exception.ResourceNotFoundException;
import com.hrms.mapper.AttendanceMapper;

public class Attendance {
    private Long id;
    private Employee employee;
    private String date;
    private String status; // "present", "late", "early_leave", "absent"
    private AttendanceMapper attendanceMapper;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 如果存在 Attendance 记录，则返回该记录；否则抛出 ResourceNotFoundException。
     *
     * @return Attendance 记录
     * @throws ResourceNotFoundException 如果找不到 Attendance 记录
     */
    public Attendance orElseThrow(Object attendanceRecordNotFound) {
        Attendance attendance = attendanceMapper.findById(id);
        if (attendance == null) {
            throw new ResourceNotFoundException("Attendance record not found");
        }
        return attendance;
    }
}
