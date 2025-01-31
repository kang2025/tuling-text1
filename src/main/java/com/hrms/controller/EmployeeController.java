package com.hrms.controller;

import com.hrms.pojo.*;
import com.hrms.request.EmployeeRequest;
import com.hrms.request.EmployeeUpdateRequest;
import com.hrms.response.AvatarResponse;
import com.hrms.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * EmployeeController类负责处理与员工相关的HTTP请求
 * 它依赖于EmployeeService接口的实现来执行具体的业务逻辑
 */
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    // 注入EmployeeService的实现，用于处理员工相关的业务逻辑
    @Autowired
    private EmployeeService employeeService;

    /**
     * 获取当前登录员工的个人信息
     *
     * @return 包含员工信息的响应实体
     */
    @GetMapping("/me")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<?> getMyInfo() {
        return ResponseEntity.ok(employeeService.getMyInfo());
    }

    /**
     * 更新当前登录员工的个人信息
     *
     * @param request 包含要更新的员工信息的请求体
     * @return 包含更新后的员工信息的响应实体
     */
    @PutMapping("/me")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<?> updateMyInfo(@RequestBody EmployeeUpdateRequest request) {
        Employee updatedEmployee = employeeService.updateMyInfo(request);
        return ResponseEntity.ok(updatedEmployee);
    }

    /**
     * 更新当前登录员工的头像
     *
     * @param file 包含新头像的多媒体文件
     * @return 包含新头像URL的响应实体
     */
    @PostMapping("/me/avatar")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<?> updateAvatar(@RequestParam("file") MultipartFile file) {
        String avatarUrl = employeeService.updateAvatar(file);
        return ResponseEntity.ok(new AvatarResponse(avatarUrl));
    }

    /**
     * 获取当前登录员工的工资记录
     *
     * @return 包含工资记录列表的响应实体
     */
    @GetMapping("/me/salaries")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<?> getMySalaries() {
        List<Salary> salaries = employeeService.getMySalaries();
        return ResponseEntity.ok(salaries);
    }

    /**
     * 获取当前登录员工的出勤记录
     *
     * @return 包含出勤记录列表的响应实体
     */
    @GetMapping("/me/attendance")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<?> getMyAttendance() {
        List<Attendance> attendanceRecords = employeeService.getMyAttendance();
        return ResponseEntity.ok(attendanceRecords);
    }

    /**
     * 提交当前登录员工的请假请求
     *
     * @param request 包含请假信息的请求体
     * @return 包含提交后的请假请求的响应实体
     */
    @PostMapping("/me/leave-requests")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<?> submitLeaveRequest(@RequestBody LeaveRequest request) {
        LeaveRequest savedRequest = employeeService.submitLeaveRequest(request);
        return ResponseEntity.ok(savedRequest);
    }

    /**
     * 提交当前登录员工的辞职请求
     *
     * @param request 包含辞职信息的请求体
     * @return 包含提交后的辞职请求的响应实体
     */
    @PostMapping("/me/resignation-requests")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<?> submitResignationRequest(@RequestBody ResignationRequest request) {
        ResignationRequest savedRequest = employeeService.submitResignationRequest(request);
        return ResponseEntity.ok(savedRequest);
    }

    /**
     * 获取当前登录员工的培训活动记录
     *
     * @return 包含培训活动记录列表的响应实体
     */
    @GetMapping("/me/training-activities")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<?> getMyTrainingActivities() {
        List<TrainingActivity> trainingActivities = employeeService.getMyTrainingActivities();
        return ResponseEntity.ok(trainingActivities);
    }

    /**
     * 由管理员提交新员工的注册信息
     *
     * @param request 包含新员工信息的请求体
     * @return 包含新注册员工信息的响应实体
     */
    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> registerEmployee(@RequestBody EmployeeRequest request) {
        Employee employee = employeeService.registerEmployee(request);
        return ResponseEntity.ok(employee);
    }
}

