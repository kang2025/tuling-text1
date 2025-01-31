package com.hrms.mapper;

import com.hrms.pojo.ResignationRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ResignationRequestMapper {

    ResignationRequest save(ResignationRequest resignationRequest);

    void update(ResignationRequest resignationRequest);

    void deleteById(Long id);
    @Select("SELECT * FROM resignation_requests")
    List<ResignationRequest> selectAll();

    @Select("SELECT * FROM resignation_requests WHERE id = #{id}")
    Optional<ResignationRequest> findById(Long id);
}