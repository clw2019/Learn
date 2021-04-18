package com.clw.service.impl;

import com.clw.entity.Employee;
import com.clw.service.MyPredicate;

/**
 * @Author: clw
 * @Description:
 * @Date: 2021/4/1 22:53
 */
public class FilterEmployeeBySalary implements MyPredicate<Employee> {
    @Override
    public boolean test(Employee employee) {
        return employee.getSalary() > 3000.00;
    }
}
