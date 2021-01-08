package com.controller;

import com.common.BaseResult;
import com.common.Data;
import com.service.ServiceManage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: 杨爽
 */
@RestController
@RequestMapping("/manage")
public class ControllerManage {
//
//    @Resource
//    private ServiceManage serviceManage;
//
//    @GetMapping("/findAll")
//    public ResponseEntity<BaseResult> findAll() {
//
//        try {
//            List<FileName> all = serviceManage.findAll();
//                BaseResult result = new BaseResult();
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
//    @GetMapping("/{id}")
//    public ResponseEntity<BaseResult> findId(@PathVariable("id") String id) {
//        try {
//            FileName manageId = serviceManage.findId(id);
//            Data data = new Data();
//            data.setResult(manageId);
//            BaseResult result = new BaseResult();
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
