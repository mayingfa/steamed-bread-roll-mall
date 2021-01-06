package com.qiu.util.general;

public class CommonResult {
    private Integer code;
    private String message;
    private Object data;
    public CommonResult() {
    }
    public CommonResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    public CommonResult(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }

    public static CommonResult error(String message){
        return new CommonResult (500,message);
    }
    public static CommonResult error(String message,Object data){
        return new CommonResult (500,message,data);
    }
    public static CommonResult success(String message){
        return new CommonResult(200,message);
    }
    public static CommonResult success(String message,Object data){
        return new CommonResult(200,message,data);
    }

    @Override
    public String toString() {
        return "{" +
                "code :" + code +
                ", message:'" + message + '\'' +
                ", data:" + data +
                '}';
    }
}