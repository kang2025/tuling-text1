package com.hrms.service.impl;

import com.hrms.exception.ResourceNotFoundException;
import com.hrms.mapper.*;
import com.hrms.pojo.*;
import com.hrms.request.EmployeeRequest;
import com.hrms.request.EmployeeUpdateRequest;
import com.hrms.service.EmployeeService;
import com.hrms.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

/**
 * 员工服务实现类
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private SalaryMapper salaryMapper;

    @Autowired
    private AttendanceMapper attendanceMapper;

    @Autowired
    private LeaveRequestMapper leaveRequestMapper;

    @Autowired
    private ResignationRequestMapper resignationRequestMapper;

    @Autowired
    private TrainingActivityMapper trainingActivityMapper;

    @Autowired
    private OssService ossService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    private MultipartFile file;

    /**
     * 注册新员工
     *
     * @param request 员工注册请求对象，包含员工的基本信息
     * @return 保存后的员工对象
     */
    @Override
    public Employee registerEmployee(EmployeeRequest request) {
        Employee employee = new Employee();
        employee.setName(request.getName());
        employee.setEmail(request.getEmail());
        employee.setPhone(request.getPhone());
        employee.setAddress(request.getAddress());
        employee.setPassword(passwordEncoder.encode(request.getPassword()));

        return employeeMapper.save(employee);
    }

    /**
     * 根据ID查找员工
     *
     * @param id 员工ID
     * @return 可选的员工对象
     */
    @Override
    public Optional<Employee> findById(Long id) {
        return employeeMapper.findById(id);
    }

    /**
     * 获取当前登录员工的信息
     *
     * @return 当前登录的员工对象
     */
    @Override
    public Employee getMyInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Employee employee = employeeMapper.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        return employeeMapper.findById(employee.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
    }

    /**
     * 更新当前登录员工的信息
     *
     * @param request 员工更新请求对象，包含需要更新的基本信息
     * @return 更新后的员工对象
     */
    @Override
    public Employee updateMyInfo(EmployeeUpdateRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Employee employee = employeeMapper.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        employee = employeeMapper.findById(employee.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        employee.setName(request.getName());
        employee.setEmail(request.getEmail());
        employee.setPhone(request.getPhone());
        employee.setAddress(request.getAddress());

        return employeeMapper.save(employee);
    }

    /**
     * 获取所有员工信息
     *
     * @return 员工列表
     */
    @Override
    public List<Employee> getAllEmployees() {
        return employeeMapper.selectAll();
    }

    /**
     * 获取当前登录员工的工资信息
     *
     * @return 工资信息列表
     */
    @Override
    public List<Salary> getMySalaries() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Employee employee = employeeMapper.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        employee = employeeMapper.findById(employee.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        return salaryMapper.findByEmployeeId(employee.getId());
    }

    /**
     * 获取当前登录员工的考勤记录
     *
     * @return 考勤记录列表
     */
    @Override
    public List<Attendance> getMyAttendance() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Employee employee = employeeMapper.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        employee = employeeMapper.findById(employee.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        return attendanceMapper.findByEmployeeId(employee.getId());
    }

    /**
     * 获取当前登录员工参与的培训活动
     *
     * @return 培训活动列表
     */
    @Override
    public List<TrainingActivity> getMyTrainingActivities() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Employee employee = employeeMapper.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        employee = employeeMapper.findById(employee.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        return trainingActivityMapper.findByEmployeeId(employee.getId());
    }

    /**
     * 更新当前登录员工的头像
     *
     * @param file 头像文件
     * @return 头像的URL
     */    @Override
    public String updateAvatar(MultipartFile file) {
        String avatarUrl = ossService.uploadFile(file);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Employee employee = employeeMapper.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        employee = employeeMapper.findById(employee.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        employee.setAvatarUrl(avatarUrl);
        employeeMapper.save(employee);

        return avatarUrl;
    }

    /**
     * 提交请假申请
     *
     * @param request 请假申请对象，包含请假的相关信息
     * @return 保存后的请假申请对象
     */
    @Override
    public LeaveRequest submitLeaveRequest(LeaveRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Employee employee = employeeMapper.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        employee = employeeMapper.findById(employee.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        request.setEmployee(employee);
        request.setStatus("pending"); // 设置初始状态为待审批

        return leaveRequestMapper.save(request);
    }

    /**
     * 提交离职申请
     *
     * @param request 离职申请对象，包含离职的相关信息
     * @return 保存后的离职申请对象
     */
    @Override
    public ResignationRequest submitResignationRequest(ResignationRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Employee employee = employeeMapper.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        employee = employeeMapper.findById(employee.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        request.setEmployee(employee);
        request.setStatus("pending"); // 设置初始状态为待审批

        return resignationRequestMapper.save(request);
    }

    /**
     * 删除员工
     *
     * @param id 员工ID
     */    @Override
    public void deleteEmployee(Long id) {
        Employee employee = employeeMapper.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        employeeMapper.deleteById(id);
    }

    /**
     * 软删除员工（标记为删除）
     *
     * @param id 员工ID
     */    @Override
    public void softDeleteEmployee(Long id) {
        Employee employee = employeeMapper.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        employee.setDeleted(true); // 假设有一个deleted字段来标记软删除
        employeeMapper.update(employee);
    }

    /**
     * 更新员工信息
     *
     * @param id      员工ID
     * @param request 员工更新请求对象，包含需要更新的基本信息
     * @return 更新后的员工对象
     */
    @Override
    public Employee updateEmployee(Long id, EmployeeUpdateRequest request) {
        Employee employee = employeeMapper.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        employee.setName(request.getName());
        employee.setEmail(request.getEmail());
        employee.setPhone(request.getPhone());
        employee.setAddress(request.getAddress());

        return employeeMapper.save(employee);
    }
}
