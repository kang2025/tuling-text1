package com.hrms.service;

import com.hrms.pojo.ResignationRequest;

import java.util.List;

public interface ResignationRequestService {

    List<ResignationRequest> getAllResignationRequests();

    void approveResignationRequest(Long id);
    void rejectResignationRequest(Long id);
}
