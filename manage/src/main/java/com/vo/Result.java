package com.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;

@Data
@AllArgsConstructor
@ApiModel(value = "统一返回结果")
public class Result {

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "是否成功")
    private boolean success;

    @ApiModelProperty(value = "返回数据")
    private HashMap<String, Object> result = new HashMap<>();


    // 数据
    //当前Result不对外公开构造,提供静态方法
    //私有化,不能new 只调用以下方法
    public Result() {
    };

    //调用成功方法
    public static Result ok() {
        Result r = new Result();//调用自己私有构造方法
        r.setSuccess(true);
        r.setCode(ResultCode.OK);
        r.setMessage("成功");
        return r;
    }

    //失败调用方法
    public static Result error() {
        Result r = new Result();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage("失败");
        return r;
    }

    //调用message 修改信息
    public Result message(String message) {
        this.setMessage(message);
        return this;
    }

    //修改状态码
    public Result code(Integer code) {
        this.setCode(code);
        return this;
    }

    //data数据
    public Result result(String key, Object value) {
        this.result.put(key, value);
        return this;
    }

    // 两个data方法名一样,参数不同,重载
    public Result result(HashMap<String, Object> map) {
        this.setResult(map);
        return this;
    }


}
