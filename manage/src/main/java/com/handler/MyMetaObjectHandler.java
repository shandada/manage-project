package com.handler;


import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j// 日志输出
@Component// 被spring扫描

/**
 *  Mybatis-plus实现MetaObjectHandler接口 自动更新创建时间更新时间
 */
public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     * 创建填充createTime 创建时间,updateTime修改时间
     * *创建和修改时间都有
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        //自动填充 createTime创建时间, updateTime修改时间
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }
    /**
     * 修改填充updateTime 修改时间
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        //自动填充 updateTime 修改时间
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }
}
