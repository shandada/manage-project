package com.controller;

import com.common.BaseResult;
import com.common.CommonUtils;
import com.common.Data;
import com.common.Data;
import com.pojo.FileName;
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

    @Resource
    private ServiceManage serviceManage;

    @GetMapping("/findAll")
    public ResponseEntity<BaseResult> findAll() {

        List<FileName> all = serviceManage.findAll();
        BaseResult result = new BaseResult();
        Data data = new Data();
        data.setResults(all);
        result.setData(data);
        result.setCode(CommonUtils.SUCCESS_CODE);
        result.setSuccess(true);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResult> findId(@PathVariable("id") String id) {

        FileName manageId = serviceManage.findId(id);
        Data data = new Data();
        data.setResult(manageId);
        BaseResult result = new BaseResult(200, true, data);


        return ResponseEntity.ok(result);
    }
}
