package com.hrms.service.impl;

import com.hrms.exception.ResourceNotFoundException;
import com.hrms.mapper.AttendanceMapper;
import com.hrms.pojo.Attendance;
import com.hrms.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 实现考勤服务接口，处理考勤相关业务逻辑
 */
@Service
public class AttendanceServiceImpl implements AttendanceService {

    /**
     * 注入考勤数据访问对象
     */
    @Autowired
    private AttendanceMapper attendanceMapper;

    /**
     * 记录考勤处罚
     *
     * @param attendanceId 考勤记录ID
     * @param reason 处罚原因
     * @throws ResourceNotFoundException 如果找不到指定的考勤记录
     */
    @Override
    public void recordPenalty(Long attendanceId, String reason) {
        // 使用 Optional.ofNullable 包装返回的 Attendance 对象
        Optional<Attendance> optionalAttendance = Optional.ofNullable(attendanceMapper.findById(attendanceId));
        // 如果考勤记录不存在，则抛出异常
        Attendance attendance = optionalAttendance.orElseThrow(() -> new ResourceNotFoundException("Attendance record not found"));

        // 这里可以添加具体的处罚逻辑，例如更新状态或记录日志
        attendance.setStatus("penalized");
        // 保存更新后的考勤记录
        attendanceMapper.save(attendance);
    }

    /**
     * 获取所有考勤记录
     *
     * @return 考勤记录列表
     */
    @Override
    public List<Attendance> getAllAttendance() {
        // 获取并返回所有考勤记录
        return attendanceMapper.selectAll();
    }
}
