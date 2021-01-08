package com.vo;

import com.vo.ResultCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@ApiModel(value = "全局统一返回结果")
public class Result {
    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "是否成功")
    private boolean success;
//    @ApiModelProperty(value = "返回数据")
//    private HashMap<String, Object> data = new HashMap<>();

    @ApiModelProperty(value = "返回数据")
    private com.vo.Data data;

    // 数据
//    private String username;
//    private String password;
//    private String level;


    //当前Result不对外公开构造,提供静态方法

    //私有化,不能new 只调用以下方法
    public Result() {
    }

    ;

    //调用成功方法
//    public static Result ok() {
//        Result r = new Result();//调用自己私有构造方法
//        r.setSuccess(true);
//        r.setCode(ResultCode.OK);
//        return r;
//    }
    public void ok() {
//        Result r = new Result();//调用自己私有构造方法
        setSuccess(true);
        setCode(ResultCode.OK);
    }

    public void error() {
        setSuccess(false);
        setCode(ResultCode.ERROR);
    }

    //失败调用方法
//    public static Result error() {
//        Result r = new Result();
//        r.setSuccess(false);
//        r.setCode(ResultCode.ERROR);
//        return r;
//    }

    //修改状态码
    public Result code(Integer code) {
        this.setCode(code);
        return this;
    }

    //data数据

    public Integer getCode() {
        return code;
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





    // 两个data方法名一样,参数不同,重载

    //data数据
//    public Result data(Object object) {
//      return data(object);
//
//    }


    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", success=" + success +
                ", data=" + data +

                '}';
    }
}
