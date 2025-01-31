package com.hrms.pojo;

public class LeaveRequest {

    private Long id;

    private Employee employee;

    private String leaveType; // "annual", "sick", "personal"
    private String startDate;
    private String endDate;
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

public String getLeaveType() {
    return leaveType;
}

public void setLeaveType(String leaveType) {
    this.leaveType = leaveType;
}

public String getStartDate() {
    return startDate;
}

public void setStartDate(String startDate) {
    this.startDate = startDate;
}

public String getEndDate() {
    return endDate;
}

public void setEndDate(String endDate) {
    this.endDate = endDate;
}

public String getStatus() {
    return status;
}

public void setStatus(String status) {
    this.status = status;
}
}
