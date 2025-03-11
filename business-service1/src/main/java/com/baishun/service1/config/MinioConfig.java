package com.baishun.service1.config;

import io.minio.MinioClient;
import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 代码描述
 *
 * @Author BAISHUN
 * @Date: 2024/7/22 18:12
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "spring.minio")
@ConditionalOnProperty(name = "spring.minio.open", havingValue = "true")
public class MinioConfig {
    private String accessKey;

    private String secretKey;

    private String url;

    private String bucketName;

    @Bean
    public MinioClient minioClient(){
        return MinioClient.builder()
                .endpoint(url)
                .credentials(accessKey,secretKey)
                .build();
    }
}
