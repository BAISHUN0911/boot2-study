package com.baishun.service1.controller;

import com.baishun.service1.constants.AjaxResult;
import com.baishun.service1.utils.MinioUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;

/**
 * 代码描述
 *
 * @Author BAISHUN
 * @Date: 2024/7/22 20:36
 */
@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {
    private static final String UPLOAD_DIR = "D:/development/temp/";

    @Autowired(required = false)
    private MinioUtils minioUtils;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please select a file to upload");
        }
        try {
            // Get the filename and clean it of any path sequence
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());

            // Copy the file to the upload directory (replace existing file with same name)
            Path targetLocation = Paths.get(UPLOAD_DIR + fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            log.info("文件成功上传到" + targetLocation);

            // Return a success response
            return ResponseEntity.ok().body("File uploaded successfully: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file");
        }
    }


    /**
     * @param file     文件
     * @param fileName 文件名称
     * @return {@link AjaxResult }
     * @Description 上传文件
     * @Author IT小辉同学
     * @Date 2023/06/02
     */
    @GetMapping("api/upload")
    public AjaxResult uploadFile(@RequestParam("file") MultipartFile file, String fileName) {
        long begin = System.currentTimeMillis();
        minioUtils.upload(file, fileName);
        long end = System.currentTimeMillis();
        long seconds = (end - begin) / 1000;
        String msg = "上传成功,共耗时: " + seconds + "s";
        return AjaxResult.success(msg);

    }

    /**
     * @param fileName 文件名称
     * @return {@link ResponseEntity }
     * @Description dowload文件
     * @Author IT小辉同学
     * @Date 2023/06/02
     */
    @GetMapping("api/dowload")
    public ResponseEntity dowloadFile(@RequestParam("fileName") String fileName) {
        return minioUtils.download(fileName);
    }

    /**
     * @param fileName 文件名称
     * @return {@link AjaxResult }
     * @Description 得到文件url
     * @Author IT小辉同学
     * @Date 2023/06/02
     */
    @GetMapping("api/getUrl")
    public AjaxResult getFileUrl(@RequestParam("fileName") String fileName) {
        HashMap map = new HashMap();
        // 根据文件名（object名）来获取文件url
        String fileUrl = minioUtils.getFileUrl(fileName);
        log.info("fileUrl: {}", fileUrl);
        map.put("FileUrl", fileUrl);
        return AjaxResult.success(map);
    }
}
