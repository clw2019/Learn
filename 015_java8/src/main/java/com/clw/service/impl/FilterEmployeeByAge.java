package com.clw.service.impl;

import com.clw.entity.Employee;
import com.clw.service.MyPredicate;

/**
 * @Author: clw
 * @Description:
 * @Date: 2021/4/1 22:43
 */
public class FilterEmployeeByAge implements MyPredicate<Employee> {
    @Override
    public boolean test(Employee employee) {
        return employee.getAge() >= 35;
    }
}
