package com.baishun.dynamictpdemo;

import org.dromara.dynamictp.core.DtpRegistry;
import org.dromara.dynamictp.core.executor.DtpExecutor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description:
 * @Author shengy
 * @Date 2025/9/2 17:47
 */
@RestController
@RefreshScope
public class Controller {
  @Resource
  private DtpExecutor dtpExecutorExample1;

  @Value("${dynamictp.executors[0].corePoolSize}")
  private String corePoolSize;

  @GetMapping("/test")
  public String getMessage() {
    System.out.println("dynamictp.executors[0].corePoolSize: " + corePoolSize);
    System.out.println(dtpExecutorExample1.getCorePoolSize());
    DtpExecutor dtpExecutor = DtpRegistry.getDtpExecutor("dtpExecutorExample1");
    System.out.println(dtpExecutor.getCorePoolSize());
    return corePoolSize;
  }
}
