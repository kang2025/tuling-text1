package com.hrms.service;

import com.hrms.pojo.Attendance;

import java.util.List;

public interface AttendanceService {
    void recordPenalty(Long attendanceId, String reason);

    // 获取所有考勤记录的方法
    List<Attendance> getAllAttendance();
}
