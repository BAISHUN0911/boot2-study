package com.baishun.mystudy.controller;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @Author shengy
 * @Date 2024/11/6 9:54
 */
@RestController
@RequestMapping("/data")
public class DataController {
  private final Map<Integer, DruidDataSource> dataSourceMap = new HashMap<>();

  private final Map<Integer, JdbcTemplate> jdbcTemplateMap = new HashMap<>();

  @PostConstruct
  public void init() {
    DruidDataSource dataSource = new DruidDataSource();
    dataSource.setUrl("jdbc:mysql://172.17.13.74:3306/iadt?useUnicode=true&characterEncoding=utf-8&useSSL=false" +
            "&serverTimezone=Asia/Shanghai");
    dataSource.setUsername("newuser");
    dataSource.setPassword("4567891");
    dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//    dataSource.setConnectionErrorRetryAttempts(0);
//    dataSource.setBreakAfterAcquireFailure(true);
    dataSourceMap.put(1, dataSource);
    jdbcTemplateMap.put(1, new JdbcTemplate(dataSource));
  }

  @PostMapping("/getData")
  public List<Map<String, Object>> getData(Integer dbId) {
    JdbcTemplate jdbcTemplate = jdbcTemplateMap.get(dbId);
    String sql = "select distinct mediatypename from dim_mc_viewer_qa";
    List<Map<String, Object>> maps;
    try {
      maps = jdbcTemplate.queryForList(sql);
    } catch (DataAccessException e) {
      System.out.println(e.getMessage());
      return null;
    }
    return maps;
  }
}
