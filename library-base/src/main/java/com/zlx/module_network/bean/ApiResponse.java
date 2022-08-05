package com.zlx.module_network.bean;


/**
 * @description:
 */
public class ApiResponse<T> {

    public static final int CODE_SUCCESS = 0;
    public static final int CODE_ERROR = 1;
    private int code; //状态码
    private String message; //信息
    private T data; //数据

    public ApiResponse(int code, String msg) {
        this.code = code;
        this.message = msg;
        this.data = null;
    }

    public ApiResponse(int code, String msg, T data) {
        this.code = code;
        this.message = msg;
        this.data = data;
    }

    public static int getCodeSuccess() {
        return CODE_SUCCESS;
    }

    public static int getCodeError() {
        return CODE_ERROR;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        if (code == 0) {
            return true;
        } else {
            return false;
        }
    }
}