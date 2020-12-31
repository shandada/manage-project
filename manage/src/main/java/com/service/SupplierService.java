package com.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import com.pojo.Supplier;
import com.mapper.SupplierMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description:
 * @Auther: logo丶西亚卡姆
 * @Date: 2020/12/22 14:07
 */
//供应商service类
@Service
@Transactional
public class SupplierService extends ServiceImpl<SupplierMapper, Supplier> {


    //根据供应商gid,查询相关信息
    public List<Supplier> findAID(Integer gid) {
        //使用QueryWrapper 构造器
        QueryWrapper<Supplier> wrapper = new QueryWrapper<>();
        //eq 等于("数据库id","传的gid")
        wrapper.eq("gid", gid);
        //返回根据 QueryWrapper条件 查询的全部记录
        return baseMapper.selectList(wrapper);
    }
}
