package com.service;

import com.mapper.MapperFileName;
import com.pojo.FileName;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class ServiceManage {

    @Resource
    private MapperFileName mapperFileName;


    public List<FileName> findAll(){

        List<FileName> manages = mapperFileName.selectAll();

        return manages;
    }
    public FileName findId(String id ){

        return mapperFileName.selectByPrimaryKey(id);
    }

}
