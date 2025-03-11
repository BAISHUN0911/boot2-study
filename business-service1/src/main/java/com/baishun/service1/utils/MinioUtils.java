package com.baishun.service1.utils;

import com.baishun.service1.config.MinioConfig;
import com.baishun.service1.constants.HttpStatus;
import io.minio.BucketExistsArgs;
import io.minio.GetObjectArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PostPolicy;
import io.minio.PutObjectArgs;
import io.minio.RemoveBucketArgs;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import io.minio.http.Method;
import lombok.SneakyThrows;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Minio工具类 来源：wx
 *
 * @Author BAISHUN
 * @Date: 2024/7/22 18:15
 */
@Component
@ConditionalOnProperty(name = "spring.minio.open", havingValue = "true")
public class MinioUtils {
    @Autowired
    private MinioClient minioClient;

    @Autowired
    private MinioConfig configuration;

    /**
     *
     * @param name
     * @return
     */
    public boolean existBucket(String name) {
        boolean exists;
        try {
            exists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(name).build());
            if (!exists) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(name).build());
                exists = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            exists = false;
        }
        return exists;
    }

    /**
     * @param bucketName 存储bucket名称
     * @return {@link Boolean }
     * @Description 创建存储bucket
     * @Author IT小辉同学
     * @Date 2023/06/02
     */
    public Boolean makeBucket(String bucketName) {
        try {
            minioClient.makeBucket(MakeBucketArgs.builder()
                    .bucket(bucketName)
                    .build());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * @param bucketName 存储bucket名称
     * @return {@link Boolean }
     * @Description 删除存储bucket
     * @Author IT小辉同学
     * @Date 2023/06/02
     */
    public Boolean removeBucket(String bucketName) {
        try {
            minioClient.removeBucket(RemoveBucketArgs.builder()
                    .bucket(bucketName)
                    .build());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * @param fileName 文件名称
     * @param time     时间
     * @return {@link Map }
     * @Description 获取上传临时签名
     * @Author IT小辉同学
     * @Date 2023/06/02
     */
    @SneakyThrows
    public Map getPolicy(String fileName, ZonedDateTime time) {
        PostPolicy postPolicy = new PostPolicy(configuration.getBucketName(), time);
        postPolicy.addEqualsCondition("key", fileName);
        try {
            Map<String, String> map = minioClient.getPresignedPostFormData(postPolicy);
            HashMap<String, String> map1 = new HashMap<>();
            map.forEach((k, v) -> {
                map1.put(k.replaceAll("-", ""), v);
            });
            map1.put("host", configuration.getUrl() + "/" + configuration.getBucketName());
            return map1;
        } catch (ErrorResponseException e) {
            e.printStackTrace();
        } catch (InsufficientDataException e) {
            e.printStackTrace();
        } catch (InternalException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidResponseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (XmlParserException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param objectName 对象名称
     * @param method     方法
     * @param time       时间
     * @param timeUnit   时间单位
     * @return {@link String }
     * @Description 获取上传文件的url
     * @Author IT小辉同学
     * @Date 2023/06/02
     */
    public String getPolicyUrl(String objectName, Method method, int time, TimeUnit timeUnit) {
        try {
            return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                    .method(method)
                    .bucket(configuration.getBucketName())
                    .object(objectName)
                    .expiry(time, timeUnit).build());
        } catch (ErrorResponseException e) {
            e.printStackTrace();
        } catch (InsufficientDataException e) {
            e.printStackTrace();
        } catch (InternalException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidResponseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (XmlParserException e) {
            e.printStackTrace();
        } catch (ServerException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * @param file     文件
     * @param fileName 文件名称
     * @Description 上传文件
     * @Author IT小辉同学
     * @Date 2023/06/02
     */
    public void upload(MultipartFile file, String fileName) {
        // 使用putObject上传一个文件到存储桶中。
        try {
            InputStream inputStream = file.getInputStream();
            /**
             * 1.根据自定义配置类指定bucket名
             * 2.指定对象名，也是就文件以什么名存在minio
             */
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(configuration.getBucketName())
                    .object(fileName)
                    .stream(inputStream, file.getSize(), -1)
                    .contentType(file.getContentType())
                    .build());
        } catch (ErrorResponseException e) {
            e.printStackTrace();
        } catch (InsufficientDataException e) {
            e.printStackTrace();
        } catch (InternalException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidResponseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (XmlParserException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param objectName 对象名称
     * @param time       时间
     * @param timeUnit   时间单位
     * @return {@link String }
     * @Description 根据filename获取文件访问地址
     * @Author IT小辉同学
     * @Date 2023/06/02
     */
    public String getUrl(String objectName, int time, TimeUnit timeUnit) {
        String url = null;
        try {
            url = minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                    .method(Method.GET)
                    .bucket(configuration.getBucketName())
                    .object(objectName)
                    .expiry(time, timeUnit).build());
        } catch (ErrorResponseException e) {
            e.printStackTrace();
        } catch (InsufficientDataException e) {
            e.printStackTrace();
        } catch (InternalException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidResponseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (XmlParserException e) {
            e.printStackTrace();
        } catch (ServerException e) {
            e.printStackTrace();
        }
        return url;
    }

    /**
     * @param fileName
     * @return {@link ResponseEntity }<{@link byte[] }>
     * @Description description: 下载文件
     * @Author IT小辉同学
     * @Date 2023/06/02
     */
    public ResponseEntity<byte[]> download(String fileName) {
        ResponseEntity<byte[]> responseEntity = null;
        InputStream in = null;
        ByteArrayOutputStream out = null;
        try {
            in = minioClient.getObject(GetObjectArgs.builder().bucket(configuration.getBucketName()).object(fileName).build());
            out = new ByteArrayOutputStream();
            IOUtils.copy(in, out);
            //封装返回值
            byte[] bytes = out.toByteArray();
            HttpHeaders headers = new HttpHeaders();
            try {
                headers.add("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            headers.setContentLength(bytes.length);
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setAccessControlExposeHeaders(Arrays.asList("*"));
            responseEntity = new ResponseEntity<>(bytes, headers, HttpStatus.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responseEntity;
    }

    /**
     * @param objectFile 对象文件
     * @return {@link String }
     * @Description 根据文件名和桶获取文件路径
     * @Author IT小辉同学
     * @Date 2023/06/02
     */
    public String getFileUrl(String objectFile) {
        try {

            return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                    .method(Method.GET)
                    .bucket(configuration.getBucketName())
                    .object(objectFile)
                    .build()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
