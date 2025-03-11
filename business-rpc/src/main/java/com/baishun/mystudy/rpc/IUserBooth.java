package com.baishun.mystudy.rpc;

import com.baishun.mystudy.rpc.po.User;

/**
 * rpc层，用于其他项目引入依赖进行调用
 *
 * @Author BAISHUN
 * @Date: 2024/5/19 15:08
 */
public interface IUserBooth {
    User queryUserById(Integer id);
}
