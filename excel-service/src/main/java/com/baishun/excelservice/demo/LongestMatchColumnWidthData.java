package com.baishun.excelservice.demo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @description:
 * @Author shengy
 * @Date 2024/10/30 14:08
 */
@Getter
@Setter
@EqualsAndHashCode
public class LongestMatchColumnWidthData {
  @ExcelProperty("字符串标题")
  private String string;
  @ExcelProperty("日期标题很长日期标题很长日期标题很长很长")
  private Date date;
  @ExcelProperty("数字")
  private Double doubleData;
}
