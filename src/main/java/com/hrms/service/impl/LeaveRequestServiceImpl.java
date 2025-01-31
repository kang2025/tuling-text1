package com.hrms.service.impl;

import com.hrms.exception.ResourceNotFoundException;
import com.hrms.mapper.LeaveRequestMapper;
import com.hrms.pojo.LeaveRequest;
import com.hrms.service.LeaveRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * LeaveRequestServiceImpl类实现了LeaveRequestService接口，提供具体的请假请求管理服务
 */
@Service
public class LeaveRequestServiceImpl implements LeaveRequestService {

    /**
     * leaveRequestMapper用于与数据库交互请假请求数据
     */
    @Autowired
    private LeaveRequestMapper leaveRequestMapper;

    /**
     * 获取所有的请假请求
     *
     * @return 包含所有LeaveRequest对象的列表
     */
    @Override
    public List<LeaveRequest> getAllLeaveRequests() {
        return leaveRequestMapper.selectAll();
    }

    /**
     * 审批请假请求
     *
     * @param id 请假请求的ID
     * @throws ResourceNotFoundException 如果找不到对应的请假请求
     */
    @Override
    public void approveLeaveRequest(Long id) {
        LeaveRequest leaveRequest = leaveRequestMapper.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Leave request not found"));
        leaveRequest.setStatus("approved");
        leaveRequestMapper.update(leaveRequest);
    }

    /**
     * 拒绝请假请求
     *
     * @param id 请假请求的ID
     * @throws ResourceNotFoundException 如果找不到对应的请假请求
     */
    @Override
    public void rejectLeaveRequest(Long id) {
        LeaveRequest leaveRequest = leaveRequestMapper.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Leave request not found"));
        leaveRequest.setStatus("rejected");
        leaveRequestMapper.update(leaveRequest);
    }
}
