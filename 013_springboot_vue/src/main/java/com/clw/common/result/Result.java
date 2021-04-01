package com.clw.common.result;

import com.clw.common.constant.MyConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: clw
 * @Description: 通用返回结果
 * @Date: 2021/3/13 23:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    public static <T> Result<T> success(T data) {
        return new Result<>(MyConstant.SUCCESS_CODE, MyConstant.SUCCESS_MESSAGE, data);
    }

    public static <T> Result<T> success(String message, T data) {
        return new Result<>(MyConstant.SUCCESS_CODE, message, data);
    }

    public static <T> Result<T> fail(String message) {
        return new Result<>(MyConstant.FAIL_CODE, MyConstant.FAIL_MESSAGE, null);
    }

}
