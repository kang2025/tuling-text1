package com.hrms.mapper;

import com.hrms.pojo.LeaveRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

@Mapper
public interface LeaveRequestMapper {
    @Select("SELECT * FROM leave_requests WHERE id = #{id}")
    Optional<LeaveRequest> findById(Long id);
    LeaveRequest save(LeaveRequest leaveRequest);
    @Select("SELECT * FROM leave_requests")
    List<LeaveRequest> selectAll();
    void update(LeaveRequest leaveRequest);

    void deleteById(Long id);
}
