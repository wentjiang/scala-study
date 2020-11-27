package com.wentjiang.scala.classinfo

import java.io.File
import java.lang.reflect.Field

object ClassUtil {

  /**
   * get all class field value
   * @param className object class name
   * @param fieldClassSimpleName the filed type of the field name
   * @return
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
   * get all class name in scan package
   * @param basePath base path of project code source
   * @param scanPackage the package to scan
   * @param suffix the suffix of file
   * @return
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
   * get all fix file in dir tree
   * @param file file dir
   * @param suffix the file suffix not contains dot
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
