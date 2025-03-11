package com.baishun.service1.worker;

import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 代码描述
 *
 * @Author BAISHUN
 * @Date: 2024/5/19 14:41
 */
@Component
public class DemoXxlJob {
    private static final Logger logger = LoggerFactory.getLogger(DemoXxlJob.class);

    /**
     * 1、简单任务示例（Bean模式）
     */
    @XxlJob("demoJobHandler")
    public void demoJobHandler() throws Exception {
        logger.info("XXL-JOB, Hello World.");
        for (int i = 0; i < 5; i++) {
            logger.info("beat at: {}", i);
            TimeUnit.SECONDS.sleep(2);
        }
    }
}
