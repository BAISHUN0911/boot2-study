package com.baishun.service1.booth;

import com.baishun.mystudy.rpc.IUserBooth;
import com.baishun.mystudy.rpc.po.User;
import com.baishun.service1.mapper.UserMapper;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;

/**
 * 代码描述
 *
 * @Author BAISHUN
 * @Date: 2024/5/19 15:12
 */
@Service
public class UserBooth implements IUserBooth {
    @Resource
    private UserMapper userMapper;
    @Override
    public User queryUserById(Integer id) {
        User user = userMapper.queryUserById(id);
        return user;
    }
}
