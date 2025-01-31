package com.hrms.service;

import com.hrms.pojo.TrainingActivity;
import com.hrms.request.TrainingActivityRequest;

import java.util.List;

public interface TrainingActivityService {

    List<TrainingActivity> getAllTrainingActivities();
    TrainingActivity createTrainingActivity(TrainingActivityRequest request);
    TrainingActivity updateTrainingActivity(Long id, TrainingActivityRequest request);
    void deleteTrainingActivity(Long id);
}
