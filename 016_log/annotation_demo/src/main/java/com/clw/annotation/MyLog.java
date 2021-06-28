package com.clw.annotation;


import java.lang.annotation.*;

/**
 * TYPE 类，接口（包括注解类型）或枚举的声明
 * FIELD 属性的声明
 * METHOD 方法的声明
 * PARAMETER 方法形式参数声明
 * CONSTRUCTOR 构造方法的声明
 * LOCAL_VARIABLE 局部变量声明
 * ANNOTATION_TYPE 注解类型声明
 * PACKAGE 包的声明
 */
@Target(ElementType.METHOD)
/**
 * 注解的生命周期有三个阶段：
 * 1、Java源文件阶段（SOURCE）: 这个注解即不会参与编译也不会在运行期起任何作用，这个注解就和一个注释是一样的效果，只能被阅读Java文件的人看到。
 * 2、编译到class文件阶段（CLASS）: 它将被编译到Class文件中，那么编译器可以在编译时根据注解做一些处理动作，但是运行时JVM（Java虚拟机）会忽略它，我们在运行期也不能读取到。
 * 3、运行期阶段（RUNTIME）: 这个注解可以在运行期的加载阶段被加载到Class对象中。那么在程序运行阶段，我们可以通过反射得到这个注解，并通过判断是否有这个注解或这个注解中属性的值，从而执行不同的程序代码段。
 */
@Retention(RetentionPolicy.RUNTIME)
/**
 * 指定自定义注解能否随着被定义的 Java 文件生成到 JavaDoc 文档中
 */
@Documented
public @interface MyLog {
    /** 所属模块*/
    ModuleType moduleType() default ModuleType.DEFAULT;
    /** 操作类型*/
    OperType operType() default OperType.DEFAULT;
    /** 操作描述*/
    String description() default "";
}
