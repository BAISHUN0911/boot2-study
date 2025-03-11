package com.baishun.service1.mapper;

import com.baishun.mystudy.rpc.po.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 代码描述
 *
 * @Author BAISHUN
 * @Date: 2024/5/19 15:29
 */
@Mapper
public interface UserMapper {
    User queryUserById(Integer id);
}
