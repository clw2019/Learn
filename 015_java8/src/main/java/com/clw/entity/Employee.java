package com.clw.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: clw
 * @Description:
 * @Date: 2021/4/1 22:15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    private String name;
    private Integer age;
    private Double salary;

}
