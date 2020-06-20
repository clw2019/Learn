package com.clw.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;

/**
 * @Author: clw
 * @Description: MybatisPlus 的AR模式: ActiveRecord
 * @Date: 2020/6/20 21:51
 * @EqualsAndHashCode注解与@ToString注解默认情况下是忽略父类的成员变量的,默认是false,
 * 在类继承的情况时应注意@Data注解不会涉父类的成员的坑，需要加callSuper = true的参数。
 */
@Data
@EqualsAndHashCode(callSuper = false)  //视频说置为false,猜测可能错了,该置为true
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName(value = "user")  //建立User.class 和数据库表格的对应关系
public class User extends Model<User> {
    @TableId(value = "id")  //代表其实主键
    private Integer id;
    @TableField(value = "name")  //Uer.class中的字段和数据库里的字段一样的话可以省略
    private String username;
    private Integer age;
    private String email;

    @TableField(exist = false)  //当查询的结果里没有这个字段，可以使用这个方法进行忽略
    private String sex;
}
