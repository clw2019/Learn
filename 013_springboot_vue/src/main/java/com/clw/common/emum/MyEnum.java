package com.clw.common.emum;

/**
 * @Author: clw
 * @Description: 枚举
 * @Date: 2021/3/13 23:16
 */
public enum MyEnum {
    UN_KNOWN(-1, "未知状态"),
    LOGIN_SUCCESS(200, "登录成功"),
    LOGIN_FAIL(400, "登录失败"),

    USER_COUNT_NOT_FOUND(401, "用户名不存在");

    private final int code;
    private final String reasonPhrase;

    MyEnum(int code, String reasonPhrase) {
        this.code = code;
        this.reasonPhrase = reasonPhrase;
    }

    private int getCode() {
        return this.code;
    }
    private String getReasonPhrase() {
        return this.reasonPhrase;
    }

    public static MyEnum gertStatus(int code) {
        for (MyEnum myEnum : values()) {
            if (code == myEnum.code) {
                return myEnum;
            }
        }
        return UN_KNOWN;
    }
}
