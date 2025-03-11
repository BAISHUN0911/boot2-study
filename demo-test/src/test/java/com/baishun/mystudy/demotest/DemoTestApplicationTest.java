package com.baishun.mystudy.demotest;

import cn.itedus.lottery.rpc.IActivityBooth;
import cn.itedus.lottery.rpc.req.ActivityReq;
import cn.itedus.lottery.rpc.res.ActivityRes;
import com.alibaba.fastjson.JSON;
import com.baishun.mystudy.rpc.IUserBooth;
import com.baishun.mystudy.rpc.po.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class DemoTestApplicationTest {
    // 直连模式
    @Reference(interfaceClass = IUserBooth.class, url = "dubbo://127.0.0.1:20880")
    private IUserBooth userBooth;

    /**
     * Lottery抽奖系统的rpc调用
     */
    @Reference(interfaceClass = IActivityBooth.class)
    private IActivityBooth iActivityBooth;

    @Test
    void test_rpc() {
        User user = userBooth.queryUserById(1);
        log.info("测试结果: {}", JSON.toJSONString(user));
    }

    @Test
    void testQueryActivityById() {
        ActivityReq req = new ActivityReq();
        req.setActivityId(100001L);
        ActivityRes activityRes = iActivityBooth.queryActivityById(req);
        System.out.println(JSON.toJSON(activityRes));
    }

}