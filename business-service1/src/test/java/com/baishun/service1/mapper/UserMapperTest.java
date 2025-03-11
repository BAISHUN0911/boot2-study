package com.baishun.service1.mapper;

import com.baishun.mystudy.rpc.po.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class UserMapperTest {
    @Resource
    private UserMapper userMapper;
    @Test
    void testQryUserById() {
        User user = userMapper.queryUserById(1);
        System.out.println(user.toString());
    }
}