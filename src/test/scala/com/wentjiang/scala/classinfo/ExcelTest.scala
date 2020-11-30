package com.wentjiang.scala.classinfo

import java.io.FileOutputStream

import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.junit.jupiter.api.Test

class ExcelTest {

  @Test
  def test_use_apache_poi_excel(): Unit = {
    val filePath = "./test.xls"
    val workbook = new HSSFWorkbook()
    val sheet = workbook.createSheet("test")
    val row = sheet.createRow(0)
    val cell0 = row.createCell(0)
    cell0.setCellValue("test0")
    val cell1 = row.createCell(1)
    cell1.setCellValue("test1")
    val cell2 = row.createCell(2)
    cell2.setCellValue("test2")
    val out = new FileOutputStream(filePath)
    workbook.write(out)
    out.close()
  }

}
