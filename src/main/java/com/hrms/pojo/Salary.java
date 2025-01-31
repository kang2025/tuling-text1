package com.hrms.pojo;

public class Salary {

    private Long id;

    private Employee employee;

    private String month;
    private Double baseSalary;
    private Double attendanceBonus;
    private Double performanceBonus;
    private Double totalSalary;

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

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(Double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public Double getAttendanceBonus() {
        return attendanceBonus;
    }

    public void setAttendanceBonus(Double attendanceBonus) {
        this.attendanceBonus = attendanceBonus;
    }

    public Double getPerformanceBonus() {
        return performanceBonus;
    }

    public void setPerformanceBonus(Double performanceBonus) {
        this.performanceBonus = performanceBonus;
    }

    public Double getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(Double totalSalary) {
        this.totalSalary = totalSalary;
    }
}
