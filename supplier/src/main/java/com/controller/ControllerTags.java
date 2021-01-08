package com.controller;

import com.common.BaseResult;
import com.common.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.service.Tags;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: 杨爽
 */
@RestController
@RequestMapping("/tags")
public class ControllerTags {

//    @Resource
//    private ServiceTags serviceTags;
//
//    @GetMapping("/findAll")
//    public ResponseEntity<BaseResult> findAll() {
//
//        try {
//            List<Tags> all = serviceTags.findAll();
//            BaseResult result = new BaseResult();
//            Data data = new Data();
//            data.setResults(all);
//            result.setData(data);
//            result.ok();
//            return ResponseEntity.ok(result);
//        } catch (Exception e) {
//            e.printStackTrace();
//            BaseResult result = new BaseResult();
//            result.error();
//            return ResponseEntity.ok(result);
//        }
//    }
//
//    @GetMapping("/{tid}")
//    public ResponseEntity<BaseResult> findId(@PathVariable("tid") String tid) {
//        try {
//            Tags tagsId = serviceTags.findId(tid);
//            BaseResult result = new BaseResult();
//            Data data = new Data();
//            data.setResult(tagsId);
//            result.setData(data);
//            result.ok();
//            return ResponseEntity.ok(result);
//        } catch (Exception e) {
//            e.printStackTrace();
//            BaseResult result = new BaseResult();
//            result.error();
//            return ResponseEntity.ok(result);
//        }
//    }
}
