package com.hrms.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.hrms.service.OssService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {

    @Value("${alioss.endpoint}")
    private String endpoint;

    @Value("${alioss.accessKeyId}")
    private String accessKeyId;

    @Value("${alioss.accessKeySecret}")
    private String accessKeySecret;

    @Value("${alioss.bucketName}")
    private String bucketName;

    @Override
    public String uploadFile(MultipartFile file) {
        // 创建OSSClient实例
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {
            // 生成唯一的文件名
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            // 上传文件
            ossClient.putObject(bucketName, fileName, file.getInputStream());
            // 关闭OSSClient
            ossClient.shutdown();
            // 返回文件URL
            return "http://" + bucketName + "." + endpoint + "/" + fileName;
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload file to OSS", e);
        }
    }
}
