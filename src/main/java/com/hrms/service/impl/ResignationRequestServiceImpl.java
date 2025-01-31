package com.hrms.service.impl;

import com.hrms.exception.ResourceNotFoundException;
import com.hrms.mapper.ResignationRequestMapper;
import com.hrms.pojo.ResignationRequest;
import com.hrms.service.ResignationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 实现离职申请服务接口，处理离职申请的相关业务逻辑
 */
@Service
public class ResignationRequestServiceImpl implements ResignationRequestService {

    /**
     * 注入离职申请的Mapper接口，用于与数据库交互
     */
    @Autowired
    private ResignationRequestMapper resignationRequestMapper;

    /**
     * 获取所有的离职申请记录
     *
     * @return 离职申请记录列表
     */
    @Override
    public List<ResignationRequest> getAllResignationRequests() {
        return resignationRequestMapper.selectAll();
    }

    /**
     * 审批离职申请
     *
     * @param id 离职申请的ID
     * @throws ResourceNotFoundException 如果找不到对应的离职申请
     */
    @Override
    public void approveResignationRequest(Long id) {
        ResignationRequest resignationRequest = resignationRequestMapper.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resignation request not found"));
        resignationRequest.setStatus("approved");
        resignationRequestMapper.update(resignationRequest);
    }

    /**
     * 拒绝离职申请
     *
     * @param id 离职申请的ID
     * @throws ResourceNotFoundException 如果找不到对应的离职申请
     */
    @Override
    public void rejectResignationRequest(Long id) {
        ResignationRequest resignationRequest = resignationRequestMapper.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resignation request not found"));
        resignationRequest.setStatus("rejected");
        resignationRequestMapper.update(resignationRequest);
    }
}