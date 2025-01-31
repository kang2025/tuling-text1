package com.hrms.pojo;

import java.util.List;
import java.util.Objects;

public class Employee {

    private Long id;

    // 员工姓名
    private String name;
    // 员工邮箱
    private String email;
    // 员工电话
    private String phone;
    // 员工地址
    private String address;
    // 头像URL
    private String avatarUrl;
    // 密码
    private String password;
    // 是否已删除（软删除）
    private boolean deleted;
    private List<String> roles; // 添加角色列表
    // 工资信息列表
    private List<Salary> salaries;

    // 考勤记录列表
    private List<Attendance> attendances;

    // 请假申请列表
    private List<LeaveRequest> leaveRequests;

    // 离职申请列表
    private List<ResignationRequest> resignationRequests;

    // 培训活动列表
    private List<TrainingActivity> trainingActivities;

    // 无参构造函数
    public Employee() {
    }

    // 全参构造函数
    public Employee(Long id, String name, String email, String phone, String address, String avatarUrl, String password, boolean deleted, List<Salary> salaries, List<Attendance> attendances, List<LeaveRequest> leaveRequests, List<ResignationRequest> resignationRequests, List<TrainingActivity> trainingActivities) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.avatarUrl = avatarUrl;
        this.password = password;
        this.deleted = deleted;
        this.salaries = salaries;
        this.attendances = attendances;
        this.leaveRequests = leaveRequests;
        this.resignationRequests = resignationRequests;
        this.trainingActivities = trainingActivities;
    }

    // Getters and Setters
    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public List<Salary> getSalaries() {
        return salaries;
    }

    public void setSalaries(List<Salary> salaries) {
        this.salaries = salaries;
    }

    public List<Attendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(List<Attendance> attendances) {
        this.attendances = attendances;
    }

    public List<LeaveRequest> getLeaveRequests() {
        return leaveRequests;
    }

    public void setLeaveRequests(List<LeaveRequest> leaveRequests) {
        this.leaveRequests = leaveRequests;
    }

    public List<ResignationRequest> getResignationRequests() {
        return resignationRequests;
    }

    public void setResignationRequests(List<ResignationRequest> resignationRequests) {
        this.resignationRequests = resignationRequests;
    }

    public List<TrainingActivity> getTrainingActivities() {
        return trainingActivities;
    }

    public void setTrainingActivities(List<TrainingActivity> trainingActivities) {
        this.trainingActivities = trainingActivities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return deleted == employee.deleted && Objects.equals(id, employee.id) && Objects.equals(name, employee.name) && Objects.equals(email, employee.email) && Objects.equals(phone, employee.phone) && Objects.equals(address, employee.address) && Objects.equals(avatarUrl, employee.avatarUrl) && Objects.equals(password, employee.password) && Objects.equals(salaries, employee.salaries) && Objects.equals(attendances, employee.attendances) && Objects.equals(leaveRequests, employee.leaveRequests) && Objects.equals(resignationRequests, employee.resignationRequests) && Objects.equals(trainingActivities, employee.trainingActivities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, phone, address, avatarUrl, password, deleted, salaries, attendances, leaveRequests, resignationRequests, trainingActivities);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", password='" + password + '\'' +
                ", deleted=" + deleted +
                ", salaries=" + salaries +
                ", attendances=" + attendances +
                ", leaveRequests=" + leaveRequests +
                ", resignationRequests=" + resignationRequests +
                ", trainingActivities=" + trainingActivities +
                '}';
    }
}
