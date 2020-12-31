package com.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pojo.FileName;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.mapper.VersionsMapper;
import com.pojo.Versions;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description:
 * @Auther: logo丶西亚卡姆
 * @Date: 2020/12/29 17:49
 */
@Service
@Transactional
public class VersionsService extends ServiceImpl<VersionsMapper,Versions> {


    public List<Versions> findVersionID(Integer vid) {
        // QueryWrapper 根据vid 查询版本号数据
        QueryWrapper<Versions> wrapper = new QueryWrapper<>();
        wrapper.eq("vid",vid);
        return baseMapper.selectList(wrapper);
    }
}
