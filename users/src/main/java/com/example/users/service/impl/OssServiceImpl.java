package com.example.users.service.impl;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sts.model.v20150401.AssumeRoleRequest;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;
import com.example.users.service.OssService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class OssServiceImpl implements OssService {

    @Value("${alibaba.cloud.access-key}")
    private String accessKeyId;
    @Value("${alibaba.cloud.secret-key}")
    private String accessKeySecret;
    @Value("${alibaba.cloud.ram.role-arn}")
    private String roleArn;
    @Value("${alibaba.cloud.ram.role-session-name}")
    private String roleSessionName;
    @Value("${alibaba.cloud.ram.sts-duration-seconds}")
    private Long stsDurationSeconds;
    @Value("${alibaba.cloud.oss.endpoint}")
    private String endpoint;
    @Value("${alibaba.cloud.oss.bucket-name}")
    private String bucketName;

    @Override
    public Map<String, String> generateStsToken() {
        try {
            DefaultProfile.addEndpoint("", "Sts", "sts.aliyuncs.com");
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
            DefaultAcsClient client = new DefaultAcsClient(profile);

            AssumeRoleRequest request = new AssumeRoleRequest();
            request.setRoleArn(roleArn);
            request.setRoleSessionName(roleSessionName);
            request.setDurationSeconds(stsDurationSeconds);

            AssumeRoleResponse response = client.getAcsResponse(request);
            AssumeRoleResponse.Credentials credentials = response.getCredentials();

            Map<String, String> result = new HashMap<>();
            result.put("accessKeyId", credentials.getAccessKeyId());
            result.put("accessKeySecret", credentials.getAccessKeySecret());
            result.put("securityToken", credentials.getSecurityToken());
            result.put("expiration", credentials.getExpiration());
            result.put("bucketName", bucketName);
            result.put("region", endpoint.replace(".aliyuncs.com", ""));
            return result;
        } catch (Exception e) {
            throw new RuntimeException("生成STS Token失败", e);
        }
    }
}