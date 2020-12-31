package com.service;

import com.mapper.MapperManage;
import com.pojo.Manage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ServiceManage {

    @Resource
    private MapperManage mapperManage;


    public List<Manage> findAll(){

        List<Manage> manages = mapperManage.selectAll();

        return manages;
    }
    public Manage findId(Integer id ){

        return mapperManage.selectByPrimaryKey(id);
    }

}
