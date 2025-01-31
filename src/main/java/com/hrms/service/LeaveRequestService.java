package com.hrms.service;

import com.hrms.pojo.LeaveRequest;

import java.util.List;

public interface LeaveRequestService {

    List<LeaveRequest> getAllLeaveRequests();

    void approveLeaveRequest(Long id);
    void rejectLeaveRequest(Long id);
}
