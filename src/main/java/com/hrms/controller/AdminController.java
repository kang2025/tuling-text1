package com.hrms.controller;

import java.util.List;
import java.util.Optional;

import com.hrms.pojo.*;
import com.hrms.request.EmployeeRequest;
import com.hrms.request.EmployeeUpdateRequest;
import com.hrms.request.PenaltyRequest;
import com.hrms.request.TrainingActivityRequest;
import com.hrms.response.MessageResponse;
import com.hrms.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    // 自动注入EmployeeService
    @Autowired
    private EmployeeService employeeService;

    // 自动注入AttendanceService
    @Autowired
    private AttendanceService attendanceService;

    // 自动注入LeaveRequestService
    @Autowired
    private LeaveRequestService leaveRequestService;

    // 自动注入ResignationRequestService
    @Autowired
    private ResignationRequestService resignationRequestService;

    // 自动注入TrainingActivityService
    @Autowired
    private TrainingActivityService trainingActivityService;

    /**
     * 注册新员工
     *
     * @param request 员工注册请求体
     * @return 响应实体，包含注册成功的员工信息
     */
    @PostMapping("/employees")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> registerEmployee(@RequestBody EmployeeRequest request) {
        Employee employee = employeeService.registerEmployee(request);
        return ResponseEntity.ok(employee);
    }

    /**
     * 获取所有员工信息
     *
     * @return 响应实体，包含所有员工信息的列表
     */
    @GetMapping("/employees")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    /**
     * 根据ID获取员工信息
     *
     * @param id 员工ID
     * @return 响应实体，包含指定ID的员工信息
     */
    @GetMapping("/employees/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getEmployeeById(@PathVariable Long id) {
        Optional<Employee> optionalEmployee = employeeService.findById(id);
        return optionalEmployee.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * 更新员工信息
     *
     * @param id 员工ID
     * @param request 更新员工信息的请求体
     * @return 响应实体，包含更新后的员工信息
     */
    @PutMapping("/employees/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id, @RequestBody EmployeeUpdateRequest request) {
        Employee updatedEmployee = employeeService.updateEmployee(id, request);
        return ResponseEntity.ok(updatedEmployee);
    }

    /**
     * 删除员工
     *
     * @param id 员工ID
     * @return 响应实体，包含删除成功的消息
     */
    @DeleteMapping("/employees/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok(new MessageResponse("Employee deleted successfully"));
    }

    /**
     * 获取所有考勤记录
     *
     * @return 响应实体，包含所有考勤记录的列表
     */
    @GetMapping("/attendance")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllAttendance() {
        List<Attendance> attendanceRecords = attendanceService.getAllAttendance();
        return ResponseEntity.ok(attendanceRecords);
    }

    /**
     * 记录员工罚单
     *
     * @param id 员工ID
     * @param request 记录罚单的请求体，包含罚单原因
     * @return 响应实体，包含记录成功罚单的消息
     */
    @PostMapping("/attendance/{id}/penalty")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> recordPenalty(@PathVariable Long id, @RequestBody PenaltyRequest request) {
        attendanceService.recordPenalty(id, request.getReason());
        return ResponseEntity.ok(new MessageResponse("Penalty recorded successfully"));
    }

    /**
     * 获取所有请假请求
     *
     * @return 响应实体，包含所有请假请求的列表
     */
    @GetMapping("/leave-requests")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllLeaveRequests() {
        List<LeaveRequest> leaveRequests = leaveRequestService.getAllLeaveRequests();
        return ResponseEntity.ok(leaveRequests);
    }

    /**
     * 审批请假请求
     *
     * @param id 请假请求ID
     * @return 响应实体，包含审批成功的消息
     */
    @PostMapping("/leave-requests/{id}/approve")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> approveLeaveRequest(@PathVariable Long id) {
        leaveRequestService.approveLeaveRequest(id);
        return ResponseEntity.ok(new MessageResponse("Leave request approved"));
    }

    /**
     * 拒绝请假请求
     *
     * @param id 请假请求ID
     * @return 响应实体，包含拒绝成功的消息
     */
    @PostMapping("/leave-requests/{id}/reject")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> rejectLeaveRequest(@PathVariable Long id) {
        leaveRequestService.rejectLeaveRequest(id);
        return ResponseEntity.ok(new MessageResponse("Leave request rejected"));
    }

    /**
     * 获取所有辞职请求
     *
     * @return 响应实体，包含所有辞职请求的列表
     */
    @GetMapping("/resignation-requests")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllResignationRequests() {
        List<ResignationRequest> resignationRequests = resignationRequestService.getAllResignationRequests();
        return ResponseEntity.ok(resignationRequests);
    }

    /**
     * 审批辞职请求
     *
     * @param id 辞职请求ID
     * @return 响应实体，包含审批成功的消息
     */
    @PostMapping("/resignation-requests/{id}/approve")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> approveResignationRequest(@PathVariable Long id) {
        resignationRequestService.approveResignationRequest(id);
        return ResponseEntity.ok(new MessageResponse("Resignation request approved"));
    }

    /**
     * 拒绝辞职请求
     *
     * @param id 辞职请求ID
     * @return 响应实体，包含拒绝成功的消息
     */
    @PostMapping("/resignation-requests/{id}/reject")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> rejectResignationRequest(@PathVariable Long id) {
        resignationRequestService.rejectResignationRequest(id);
        return ResponseEntity.ok(new MessageResponse("Resignation request rejected"));
    }

    /**
     * 软删除员工
     *
     * @param id 员工ID
     * @return 响应实体，包含软删除成功的消息
     */
    @PostMapping("/employees/{id}/soft-delete")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> softDeleteEmployee(@PathVariable Long id) {
        employeeService.softDeleteEmployee(id);
        return ResponseEntity.ok(new MessageResponse("Employee soft deleted successfully"));
    }

    /**
     * 获取所有培训活动
     *
     * @return 响应实体，包含所有培训活动的列表
     */
    @GetMapping("/training-activities")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllTrainingActivities() {
        List<TrainingActivity> trainingActivities = trainingActivityService.getAllTrainingActivities();
        return ResponseEntity.ok(trainingActivities);
    }

    /**
     * 创建新的培训活动
     *
     * @param request 创建培训活动的请求体
     * @return 响应实体，包含创建成功的培训活动信息
     */
    @PostMapping("/training-activities")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createTrainingActivity(@RequestBody TrainingActivityRequest request) {
        TrainingActivity trainingActivity = trainingActivityService.createTrainingActivity(request);
        return ResponseEntity.ok(trainingActivity);
    }

    /**
     * 更新培训活动信息
     *
     * @param id 培训活动ID
     * @param request 更新培训活动信息的请求体
     * @return 响应实体，包含更新后的培训活动信息
     */
    @PutMapping("/training-activities/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateTrainingActivity(@PathVariable Long id, @RequestBody TrainingActivityRequest request) {
        TrainingActivity updatedActivity = trainingActivityService.updateTrainingActivity(id, request);
        return ResponseEntity.ok(updatedActivity);
    }

    /**
     * 删除培训活动
     *
     * @param id 培训活动ID
     * @return 响应实体，包含删除成功的消息
     */
    @DeleteMapping("/training-activities/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteTrainingActivity(@PathVariable Long id) {
        trainingActivityService.deleteTrainingActivity(id);
        return ResponseEntity.ok(new MessageResponse("Training activity deleted successfully"));
    }
}

