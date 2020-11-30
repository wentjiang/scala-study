package com.wentjiang.scala.classinfo


import org.junit.Test
import org.junit.jupiter.api.Assertions


class ClassUtilTest {

  @Test
  def test_getAllClassName(): Unit = {
    val basePath = "src/main/scala"
    val scanPackageName = "com.wentjiang.scala.testdir"
    val suffix = "scala"
    val classNames = ClassUtil.getScanPackageClassName(basePath, scanPackageName, suffix)
    Assertions.assertEquals(1, classNames.length)
    Assertions.assertEquals("com.wentjiang.scala.testdir.Test", classNames(0))
  }

  @Test
  def test_getClassFieldsValue(): Unit = {
    val fieldsValues = ClassUtil.getClassFieldsValue("com.wentjiang.scala.testdir.Test", classOf[String].getSimpleName)
    Assertions.assertEquals(1, fieldsValues.length)
    Assertions.assertEquals("testName", fieldsValues(0))
  }

  @Test
  def test_filterClassBySubClass(): Unit = {
    val classesStr = Array("com.wentjiang.scala.testdir.SubTestClass",
      "com.wentjiang.scala.testdir.SupperTestClass")
    val filterClasses = ClassUtil.filterClassBySubClass(classesStr, "com.wentjiang.scala.testdir.SupperTestClass")
    Assertions.assertEquals(1,filterClasses.length)
    Assertions.assertEquals("com.wentjiang.scala.testdir.SubTestClass",filterClasses(0))
  }
}
