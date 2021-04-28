package com.clw.lambda;

import com.clw.entity.Employee;
import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.*;

/**
 * @Author: clw
 * @Description:
 * @Date: 2021/4/28 21:38
 */
public class TestMethodRef {
     /**
      * 一、 方法引用：若Lambda体中的内容方法已经实现了，我们可以使用“方法引用”
      *         （可以理解为方法引用是Lambda表达式的另外一种表现形式）
      *
      *     主要有三种语法格式：
      *     对象::实例方法名
      *     类::静态方法名
      *     类::实例方法名
      *
      *     注意：
      *         ① Lambda 体中调用方法的参数列表与返回类型，要与函数式接口中抽象方法的函数列表和返回值类型保持一致
      *         ② 第一个参数 是实例方法的调用者，第二个参数是实例方法的参数
      *
      *  二、构造器引用：
      *     格式：
      *     ClassName::new
      *
      *  三、数组引用：
      *     Type[]::new
      */
    // 对象::实例方法名
    @Test
    public void test01() {

        PrintStream ps1 = System.out;
        Consumer<String> consumer1 = x -> ps1.println(x);

        PrintStream ps2 = System.out;
        Consumer<String> consumer2 = ps2::println;

        Consumer<String> consumer3 = System.out::println;
        consumer3.accept("123456");
    }

    @Test
    public void test02() {
        Employee employee = new Employee();
        employee.setName("zs");
        Supplier<String> sup = () -> employee.getName();
        String name1 = sup.get();
        System.out.println(name1);
        Supplier<String> sup2 = employee::getName;
        String name2 = sup2.get();
        System.out.println(name2);
    }

    // 类::静态方法名
    @Test
    public void test03() {
        Comparator<Integer> comp = (x, y) -> Integer.compare(x, y);

        Comparator<Integer> comp2 = Integer::compare;
    }

    // 类::实例方法名
    public void test04() {
        BiPredicate<String, String> bp = (x, y) -> x.equals(y);

        BiPredicate<String, Object> bp2 = String::equals;
    }

    // 构造器引用
    @Test
    public void test05() {
        Supplier<Employee> sup = Employee::new;
        Employee employee = sup.get();
        System.out.println(employee);
    }

    @Test
    public void test06() {
        Function<String, Employee> tConsumer = (x) -> new Employee(x);

        Function<String, Employee> fun = Employee::new;
        Employee employee = fun.apply("路明非");
        System.out.println(employee);

        BiFunction<String, Integer, Employee> bf = Employee::new;
        Employee employee1 = bf.apply("陈墨瞳", 18);
        System.out.println(employee1);
    }

    // 数组引用
    @Test
    public void test07() {
        Function<Integer, String[]> fun = x -> new String[x];
        String[] str = fun.apply(10);
        System.out.println(str.length);

        Function<Integer, String[]> fun2 = String[]::new;
        String[] strings = fun2.apply(20);
        System.out.println(strings.length);
    }
}
