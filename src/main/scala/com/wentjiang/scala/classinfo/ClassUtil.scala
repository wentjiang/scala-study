package com.wentjiang.scala.classinfo

import java.io.File
import java.lang.reflect.Field

object ClassUtil {

  /**
   * 获取类属性示例
   */
  def getClassFieldsValue(className: String, fieldClassSimpleName: String): Array[String] = {
    val clazz = Class.forName(className)
    val instance = clazz.newInstance()
    clazz.getDeclaredFields
      .filter((it: Field) => it.getType.getSimpleName == fieldClassSimpleName)
      .map(it => {
        it.setAccessible(true)
        it.get(instance).asInstanceOf[String]
      })
  }

  /**
   * 获取包下的所有类的类名
   */
  def getScanPackageClassName(basePath: String, scanPackage: String, suffix: String): Array[String] = {
    val replacedBasePath = basePath.split("/").mkString(File.separator)
    val scanPath = scanPackage.split('.').mkString(File.separator)
    val fullScanPath = s"$replacedBasePath${File.separator}$scanPath"
    val fullScanPathFile = new File(fullScanPath)
    val files = getFiles(fullScanPathFile, suffix)
    val classesFullName = files.map(fileName => {
      val scalaFileName = fileName.substring(basePath.length + 1)
        .replace(File.separator, '.'.toString)
      scalaFileName.substring(0, scalaFileName.length - suffix.length - 1)
    })
    classesFullName
  }

  /**
   * 获取文件树中所有的
   *
   * @param file
   * @return
   */
  def getFiles(file: File, suffix: String): Array[String] = {
    val files = file.listFiles()
    val fullPaths = files.filter(file => {
      !file.isDirectory && file.getName.endsWith(suffix)
    }).map(_.getPath)
    Array.concat(fullPaths, files.filter(_.isDirectory).flatMap(file => getFiles(file, suffix)))
  }

}
