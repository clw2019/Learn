package com.clw;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: clw
 * @Description:
 * @Date: 2020/6/12 23:25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private int id;
    private String name;
    private String sex;
}
