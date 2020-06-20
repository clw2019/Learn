package com.clw.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Author: clw
 * @Description:
 * @Date: 2020/6/19 23:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName(value = "user")  //建立User.class 和数据库表格的对应关系
public class User {
    @TableId(value = "id")  //代表其实主键
    private Integer id;
    @TableField(value = "name")  //Uer.class中的字段和数据库里的字段一样的话可以省略
    private String username;
    private Integer age;
    private String email;
}
