package com.common;

/**

 * 返回结果的基类/父类
 */
public class BaseResult {
    // 0 失败 1 成功
    private Integer code;
    // 失败原因  或者  成功的说明
    private String msg;
    // 数据
    private Object data;

    public BaseResult() {
    }

    public BaseResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BaseResult(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
