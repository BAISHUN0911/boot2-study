package com.baishun.excelservice.demo;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import org.apache.poi.ss.usermodel.IndexedColors;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description:
 * @Author shengy
 * @Date 2024/10/30 9:30
 */
public class WriteDemo {
  // 文件根目录
  private static final String path = "D:/airui/workspace/excel/";
  public static void main(String[] args) {
    WriteDemo writeDemo = new WriteDemo();
//    writeDemo.widthAndHeightWrite2();
//    writeDemo.longestMatchColumnWidthWrite();
    System.out.println("媒体终端".getBytes().length);
  }
  public void widthAndHeightWrite() {
    String fileName = WriteDemo.path + "widthAndHeightWrite" + System.currentTimeMillis() + ".xlsx";
    // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
    EasyExcel.write(fileName, WidthAndHeightData.class).sheet("模板").doWrite(data());
  }

  public void widthAndHeightWrite2() {
    String fileName = WriteDemo.path + "widthAndHeightWrite" + System.currentTimeMillis() + ".xlsx";
    // 设置headList
    List<List<String>> headList = new ArrayList<>();
    headList.add(ListUtils.newArrayList("短"));
    headList.add(ListUtils.newArrayList("日期标题日期标题日期标题"));
    headList.add(ListUtils.newArrayList("数字"));
    List<List<Object>> dataList = new ArrayList<>();
    dataList.add(ListUtils.newArrayList("字符串1", "2020-11-11", 123123123123123.56));
    dataList.add(ListUtils.newArrayList("字符串2", "2020-11-12", 123123123123123.57));

    WriteCellStyle headWriteCellStyle = new WriteCellStyle();
    headWriteCellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());

    WriteCellStyle contentCellStyle = new WriteCellStyle();

    HorizontalCellStyleStrategy horizontalCellStyleStrategy =
            new HorizontalCellStyleStrategy(headWriteCellStyle, contentCellStyle);

    // 使用EasyExcelFactory导出excel
    EasyExcelFactory.write(fileName)
            .head(headList)
            .registerWriteHandler(new RwhzCustemhandler())
            .sheet("模板")
            .doWrite(dataList);
  }

  public void longestMatchColumnWidthWrite() {
    String fileName = WriteDemo.path + "longestMatchColumnWidthWrite" + System.currentTimeMillis() + ".xlsx";
    // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
    EasyExcel.write(fileName, LongestMatchColumnWidthData.class)
            .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy()).sheet("模板").doWrite(dataLong());
  }

  private List<DemoData> data() {
    List<DemoData> list = ListUtils.newArrayList();
    for (int i = 0; i < 10; i++) {
      DemoData data = new DemoData();
      data.setString("字符串" + i);
      data.setDate(new Date());
      data.setDoubleData(0.56);
      list.add(data);
    }
    return list;
  }

  private List<LongestMatchColumnWidthData> dataLong() {
    List<LongestMatchColumnWidthData> list = ListUtils.newArrayList();
    for (int i = 0; i < 10; i++) {
      LongestMatchColumnWidthData data = new LongestMatchColumnWidthData();
      data.setString("测试很长的字符串测试很长的字符串测试很长的字符串" + i);
      data.setDate(new Date());
      data.setDoubleData(1000000000000.0);
      list.add(data);
    }
    return list;
  }



}
