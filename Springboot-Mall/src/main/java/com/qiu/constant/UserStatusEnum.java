package com.qiu.constant;


public enum UserStatusEnum {
    /**
     * 管理员 0
     */
    ADMIN("admin", "管理员"),

    /**
     * 顾客 1
     */
    CUSTOMER("customer", "顾客");

    private final String value;

    private final String desc;

    UserStatusEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getValue() {
        return this.value;
    }

    public String getDesc() {
        return this.desc;
    }

    /**
     * 注意要重写此方法，不然会将值转换成 ‘MAN’，而不是 ‘男’
     */
    @Override
    public String toString() {
        return this.desc;
    }
}