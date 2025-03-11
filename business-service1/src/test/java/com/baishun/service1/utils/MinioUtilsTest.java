package com.baishun.service1.utils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MinioUtilsTest {

    @Autowired
    private MinioUtils minioUtils;

    @Test
    void testExistBucket() {
        System.out.println(minioUtils.existBucket("business1"));
    }

}