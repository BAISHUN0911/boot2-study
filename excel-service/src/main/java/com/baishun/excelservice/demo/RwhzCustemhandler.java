package com.baishun.excelservice.demo;

import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.style.column.AbstractColumnWidthStyleStrategy;
import org.apache.poi.ss.usermodel.Cell;

import java.util.List;

/**
 * @description:
 * @Author shengy
 * @Date 2024/10/30 14:22
 */
public class RwhzCustemhandler extends AbstractColumnWidthStyleStrategy {
  private static final int MAX_COLUMN_WIDTH = 255;
  //the maximum column width in Excel is 255 characters
  public RwhzCustemhandler() {
  }

  @Override
  protected void setColumnWidth(WriteSheetHolder writeSheetHolder, List<WriteCellData<?>> cellDataList, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {
    if (isHead) {
      int columnWidth = cell.getStringCellValue().getBytes().length;
      if (columnWidth > MAX_COLUMN_WIDTH) {
        columnWidth = MAX_COLUMN_WIDTH;
      } else {
        /** 根据实际业务场景自行设置，这里第一列给了更大的空间 */
        if (cell.getColumnIndex() == 1) {
          columnWidth = columnWidth + 10;
        } else {
          columnWidth = columnWidth + 3;
        }
      }
      writeSheetHolder.getSheet().setColumnWidth(cell.getColumnIndex(), columnWidth * 256);
    }
  }
}
