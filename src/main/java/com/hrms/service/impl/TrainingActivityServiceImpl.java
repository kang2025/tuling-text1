package com.hrms.service.impl;

import com.hrms.exception.ResourceNotFoundException;
import com.hrms.mapper.TrainingActivityMapper;
import com.hrms.pojo.TrainingActivity;
import com.hrms.request.TrainingActivityRequest;
import com.hrms.service.TrainingActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * TrainingActivityServiceImpl 实现了 TrainingActivityService 接口，提供培训活动相关的服务
 */
@Service
public class TrainingActivityServiceImpl implements TrainingActivityService {

    /**
     * 自动注入 TrainingActivityMapper，用于执行与培训活动相关的数据库操作
     */
    @Autowired
    private TrainingActivityMapper trainingActivityMapper;

    /**
     * 获取所有培训活动
     *
     * @return 培训活动列表
     */
    @Override
    public List<TrainingActivity> getAllTrainingActivities() {
        return trainingActivityMapper.findAll();
    }

    /**
     * 创建新的培训活动
     *
     * @param request 包含要创建的培训活动信息的请求对象
     * @return 创建的培训活动对象
     */
    @Override
    public TrainingActivity createTrainingActivity(TrainingActivityRequest request) {
        TrainingActivity trainingActivity = new TrainingActivity();
        trainingActivity.setName(request.getName());
        trainingActivity.setDescription(request.getDescription());
        trainingActivity.setStartDate(request.getStartDate());
        trainingActivity.setEndDate(request.getEndDate());
        trainingActivity.setScore(request.getScore());
        return trainingActivityMapper.save(trainingActivity);
    }

    /**
     * 更新现有的培训活动
     *
     * @param id 培训活动的ID
     * @param request 包含要更新的培训活动信息的请求对象
     * @return 更新后的培训活动对象
     * @throws ResourceNotFoundException 如果找不到指定的培训活动
     */
    @Override
    public TrainingActivity updateTrainingActivity(Long id, TrainingActivityRequest request) {
        // 正确处理 findById 返回的 Optional<TrainingActivity>
        Optional<TrainingActivity> optionalTrainingActivity = trainingActivityMapper.findById(id);
        TrainingActivity trainingActivity = optionalTrainingActivity
                .orElseThrow(() -> new ResourceNotFoundException("Training activity not found with id: " + id));

        trainingActivity.setName(request.getName());
        trainingActivity.setDescription(request.getDescription());
        trainingActivity.setStartDate(request.getStartDate());
        trainingActivity.setEndDate(request.getEndDate());
        return trainingActivityMapper.save(trainingActivity);
    }



    /**
     * 删除指定的培训活动
     *
     * @param id 要删除的培训活动的ID
     */    @Override
    public void deleteTrainingActivity(Long id) {
        trainingActivityMapper.deleteById(id);
    }
}