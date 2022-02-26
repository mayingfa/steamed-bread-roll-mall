package com.qiu.util.oss;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.qiu.config.OssConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.UUID;

/**
 * @author Captain
 * 上传文件到阿里云OSS对象存储
 */
@Slf4j
@Component
public class AliyunOssUtil {
    @Autowired
    private OssConfiguration config;

    public String upload(String folderName, File file) {
        if (file == null) {
            return null;
        }
        String bucketName = config.getBucketName();
        OSSClient client = new OSSClient(config.getEndPoint(), config.getAccessKeyId(), config.getAccessKeySecret());
        try {
            //判断桶是否存在
            if (!client.doesBucketExist(bucketName)) {
                //创建桶
                client.createBucket(bucketName);
                CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
                //设置访问权限为公共读
                createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
                //发起创建桶的请求
                client.createBucket(createBucketRequest);
            }
            //设置文件路径和名称
            String fileUrl = config.getFileHost() + "/" + (folderName + "/" + UUID.randomUUID().toString().replace("-", "") + "-" + file.getName());
            PutObjectResult result = client.putObject(new PutObjectRequest(bucketName, fileUrl, file));
            client.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            //文件上传成功后,返回当前文件的路径
            if (result != null) {
                return config.getBucket() + "/" + fileUrl;
            }
        } catch (Exception e) {
            log.error("上传文件到阿里云OSS失败", e);
        } finally {
            client.shutdown();
        }
        return null;
    }
}