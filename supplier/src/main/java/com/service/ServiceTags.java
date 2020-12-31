package com.service;

import com.mapper.MapperTags;
import com.pojo.Tags;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ServiceTags {

    @Resource
    private MapperTags mapperTags;

    public List<Tags>findAll(){
        List<Tags> tags = mapperTags.selectAll();

        return tags;
    }
     public Tags findId(Integer tid){
       return mapperTags.selectByPrimaryKey(tid);
     }
}
