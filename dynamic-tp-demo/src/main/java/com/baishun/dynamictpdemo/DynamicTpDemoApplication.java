package com.baishun.dynamictpdemo;

import org.dromara.dynamictp.spring.annotation.EnableDynamicTp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDynamicTp
public class DynamicTpDemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(DynamicTpDemoApplication.class, args);
  }

}
