package com.hrms.pojo;

public class ResignationRequest {

    private Long id;

    private Employee employee;

    private String reason;
    private String status; // "pending", "approved", "rejected"

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

public String getReason() {
    return reason;
}

public void setReason(String reason) {
    this.reason = reason;
}

public String getStatus() {
    return status;
}

public void setStatus(String status) {
    this.status = status;
}
}
