package com.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.pojo.Manage;

/**
 * @Description:
 * @Auther: logo丶西亚卡姆
 * @Date: 2020/12/21 15:02
 */
//mapper接口
@Mapper
// 继承BaseMapper  使用mybatis-pubs完成crud操作
public interface ManageMapper extends BaseMapper<Manage> {
}