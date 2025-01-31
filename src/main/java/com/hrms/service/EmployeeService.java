package com.hrms.service;

import com.hrms.pojo.*;
import com.hrms.request.EmployeeRequest;
import com.hrms.request.EmployeeUpdateRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Employee getMyInfo();

    List<Salary> getMySalaries();
    List<Attendance> getMyAttendance();
    List<TrainingActivity> getMyTrainingActivities();
    Employee updateMyInfo(EmployeeUpdateRequest request);
    String updateAvatar(MultipartFile file);
    LeaveRequest submitLeaveRequest(LeaveRequest request);
    ResignationRequest submitResignationRequest(ResignationRequest request);
    Employee registerEmployee(EmployeeRequest request);
    List<Employee> getAllEmployees();
    Employee updateEmployee(Long id, EmployeeUpdateRequest request);
    void deleteEmployee(Long id);
    void softDeleteEmployee(Long id);
    Optional<Employee> findById(Long id);
}
