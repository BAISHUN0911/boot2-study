package com.baishun.mystudy.common;

import com.mysql.cj.exceptions.CJException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLException;
import java.sql.SQLNonTransientConnectionException;

/**
 * @description: @Author shengy @Date 2024/11/6 9:26
 */
@ControllerAdvice
public class GlobalExceptionHandler {
  // 针对数据库连接失败的密码错误进行特殊处理
  @ExceptionHandler(SQLException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public String handleSQLException(SQLException ex, CJException ex2, SQLNonTransientConnectionException ex3) {
    // 检查异常消息或状态码是否涉及密码错误
    if (ex.getMessage().contains("password") || ex.getSQLState().equals("28000")) {
      System.err.println("数据库连接失败：密码错误");
      return "数据库连接失败，请检查用户名或密码。";
    }

    // 返回其他SQL异常的通用处理
    System.err.println("SQL异常：" + ex.getMessage());
    return "数据库操作失败，请稍后重试。";
  }
}
