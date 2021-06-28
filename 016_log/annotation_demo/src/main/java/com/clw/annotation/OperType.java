package com.clw.annotation;

public enum OperType {

    DEFAULT("", "默认"),
    CREATE("create", "添加"),
    RETRIEVE("retrieve", "查询"),
    DELETE("delete", "删除"),
    UPDATE("update", "修改"),
    LOGIN("login", "登录"),
    LOGOUT("logout", "退出");

    private String code;
    private String name;

    OperType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
