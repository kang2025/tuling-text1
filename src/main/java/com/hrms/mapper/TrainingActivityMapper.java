package com.hrms.mapper;

import com.hrms.pojo.TrainingActivity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface TrainingActivityMapper {

    List<TrainingActivity> findAll();

    TrainingActivity save(TrainingActivity trainingActivity);

    void update(TrainingActivity trainingActivity);

    void deleteById(Long id);

    Optional<TrainingActivity> findById(@Param("id") Long id);
    List<TrainingActivity> findByEmployeeId(@Param("employeeId") Long employeeId);

}
