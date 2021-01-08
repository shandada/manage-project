package com.common;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 返回结果的基类/父类
 */

@AllArgsConstructor
public class BaseResult {
    // 0 失败 1 成功
    private Integer code;
    // 失败原因  或者  成功的说明

    // 数据
    private boolean success;
    private Data data;

    public Integer getCode() {
        return code;
    }

    public BaseResult() {
    }

    ;

    //调用成功方法
    public void ok() {
//        BaseResult r = new BaseResult();//调用自己私有构造方法
        setSuccess(true);
        setCode(ResultCode.OK);
    }

    //失败调用方法
    public void  error() {
     setSuccess(false);
       setCode(ResultCode.ERROR);

    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseResult{" +
                "code=" + code +
                ", success=" + success +
                ", data=" + data +
                '}';
    }
}
