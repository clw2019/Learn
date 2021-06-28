package com.clw.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Value;

@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * 支持链式写法
 * Depart depart = new Depart();
 * depart.setDepartNo("NO_1").setDepart("人事部");
 */
@Accessors(chain = true)
public class Depart {

    /**部门编号*/
    @TableId(type = IdType.ASSIGN_UUID)
    private String deptNo;
    /**部门名称*/
    private String deptName;
    /**数据库名称*/
    private String dbName;


}
