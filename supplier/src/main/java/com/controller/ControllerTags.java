package com.controller;

import com.common.BaseResult;
import com.pojo.Tags;
import com.service.ServiceTags;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/tags")
public class ControllerTags {

    @Resource
    private ServiceTags serviceTags;

    @GetMapping("/findAll")
    public ResponseEntity<BaseResult> findAll() {

        List<Tags> all = serviceTags.findAll();

        BaseResult result = new BaseResult(1, "查询成功", all);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{tid}")
    public ResponseEntity<BaseResult> findId(@PathVariable("tid") Integer tid) {

        Tags tagsId = serviceTags.findId(tid);

        BaseResult result = new BaseResult(1, "根据ID查询成功", tagsId);

        return ResponseEntity.ok(result);
    }
}
