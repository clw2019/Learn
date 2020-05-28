package com.clw.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: clw
 * @Description:
 * @Date: 2020/5/28 13:33
 */
@Data                   // @Getter、@Setter、@ToString等
@AllArgsConstructor     // 全参构造
@NoArgsConstructor      // 无参构造
public class User {
    private Integer id;
    private String username;
    private String password;
}
