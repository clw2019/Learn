package com.clw.annotation;

public enum ModuleType {
    DEFAULT(""),
    USER("用户模块"),
    ROLE("角色模块");

    ModuleType(String type) {
        this.type = type;
    }

    String type;

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
