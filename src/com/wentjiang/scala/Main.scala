package com.wentjiang.scala

import java.lang.reflect.Field
import java.util
import java.util.Arrays

import com.wentjiang.scala.test.BaseClass

object Main {
  def main(args: Array[String]) {
    getClassFields("com.wentjiang.scala.test.FirstClass", classOf[String].getSimpleName)
  }

  //  def
  /**
   * 获取类属性示例
   */
  def getClassFields(className: String, fieldClassSimpleName: String) = {
    val firstClass = Class.forName(className)
    util.Arrays.stream(firstClass.getDeclaredFields)
      .filter((it: Field) => it.getType.getSimpleName == fieldClassSimpleName)
      .forEach((it: Field) => System.out.println(it.getName))
  }


}
