package com.vo;


//状态码返回类
public interface ResultCode {
    //接口中只能定义常量    public static final可省略,且常量字母大写
    public static final int OK = 200;//成功
    public static final int ERROR = 500;//失败
}
