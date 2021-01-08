package com.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import com.pojo.Supplier;

import java.util.List;


/**
 * @Description:
 * @Auther: logo丶西亚卡姆
 * @Date: 2020/12/22 14:07
 */
//mapper接口
@Mapper
// 继承BaseMapper  使用mybatis-pubs完成crud操作
public interface SupplierMapper extends BaseMapper<Supplier> {
//
//    //使用@Select注解方法查询
//    @Select("SELECT*FROM supplier WHERE gid IN(SELECT gid FROM manage WHERE manage.gid=#{gid})")
//    //数据库字段名与实体类对应的属性名不一致时，可以使用@Results映射来将其对应起来。
//    @Results({
//            //column数据库字段,property实体类字段
//            @Result(column = "gid", property = "gid"),
//
//    })
//    //自定义方法
//    public List<Supplier> findAID(Integer gid);
}