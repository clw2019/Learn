package com.clw.lambda;

import com.clw.entity.Employee;
import com.clw.service.MyPredicate;
import com.clw.service.impl.FilterEmployeeByAge;
import com.clw.service.impl.FilterEmployeeBySalary;
import org.junit.Test;

import java.util.*;

/**
 * @Author: clw
 * @Description:
 * @Date: 2021/4/1 21:24
 */
public class Lambda {

    // 原來的匿名内部类
    @Test
    public void test1() {
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };

        TreeSet<Integer> ts = new TreeSet<>(comparator);

    }

    // Lambda 表达式
    @Test
    public void test2() {
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
        Comparator<Integer> com1 = Integer::compare; // Lambda 表达式优化
        TreeSet<Integer> ts = new TreeSet<>(com);
        TreeSet<Integer> ts1 = new TreeSet<>(com1);
    }


    List<Employee> employees = Arrays.asList(
            new Employee("张三", 18, 1000.00),
            new Employee("李四", 20, 2000.00),
            new Employee("王五", 22, 3000.00),
            new Employee("赵六", 23, 4000.00),
            new Employee("田七", 40, 5000.00)
    );

    public List<Employee> filterEmployee1() {
        List<Employee> empList = new ArrayList<>();
        // 获取年龄大于 35 的
        for (Employee employee : employees) {
            if (employee.getAge() > 35) {
                empList.add(employee);
            }
        }
        return empList;
    }

    public List<Employee> filterEmployee2() {
        List<Employee> empList = new ArrayList<>();
        // 工资大于3000.00
        for (Employee employee : employees) {
            if (employee.getSalary() > 3000.00) {
                empList.add(employee);
            }
        }
        return empList;
    }


    /***
    * @Author: clw
    * @Description: 优化方法式一: 策略设计模式
     * new FilterEmployeeByAge()、new FilterEmployeeBySalary() 是策略
     * 优点：
     * 缺点: 需要建立多个类去实现同一个接口
    * @Param:
    * @return:
    * @Date: 2021/4/1 23:01
    */
    public List<Employee> filterEmployee(List<Employee> list, MyPredicate<Employee> mp) {
        List<Employee> employees = new ArrayList<>();
        for (Employee employee : list) {
            if (mp.test(employee)) {
                employees.add(employee);
            }
        }
        return employees;
    }

    @Test
    public void test3() {
        List<Employee> employees1 = filterEmployee1();
        List<Employee> employees2 = filterEmployee2();
        List<Employee> employees = filterEmployee(this.employees, new FilterEmployeeByAge());
        for (Employee employee : employees) {
            System.out.println(employee);
        }

        System.out.println("=============================================");

        List<Employee> employees3 = filterEmployee(this.employees, new FilterEmployeeBySalary());
        for (Employee employee : employees3) {
            System.out.println(employee);
        }
    }

    /***
    * @Author: clw
    * @Description: 优化方式二: 匿名内部类
    * @Param: []
    * @return: void
    * @Date: 2021/4/1 23:05
    */
    @Test
    public void test4() {

        List<Employee> employeeList = filterEmployee(this.employees, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getSalary() > 3000.00;
            }
        });

        for (Employee employee : employeeList) {
            System.out.println(employee);
        }
    }

    /***
    * @Author: clw
    * @Description: 优化方法三: Lambda 表达式
    * @Param: []
    * @return: void
    * @Date: 2021/4/1 23:12
    */
    public void test5() {
        List<Employee> employeeList = filterEmployee(employees, e -> e.getSalary() > 3000.00);

        employeeList.forEach(e -> System.out.println(e));
        // <==>
        employeeList.forEach(System.out::println);

    }

    /***
    * @Author: clw
    * @Description: 优化方式四:
    * @Param: []
    * @return: void
    * @Date: 2021/4/1 23:15
    */
    @Test
    public void test6() {

        System.out.println("=========工资大于3000.00=========");

        employees.stream()
                .filter(e -> e.getSalary() > 3000.00)
                .forEach(System.out::println);

        System.out.println("=========工资大于3000.00 && 取前2条=========");

        employees.stream()
                .filter(e -> e.getSalary() > 3000.00)
                .limit(1)
                .forEach(System.out::println);

        System.out.println("=========遍历名字=========");

        employees.stream()
                .map(Employee::getName)
                .forEach(System.out::println);

        System.out.println("=========遍历名字=========");

    }
}
