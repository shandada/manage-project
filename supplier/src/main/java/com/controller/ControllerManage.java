package com.controller;

import com.common.BaseResult;
import com.pojo.Manage;
import com.service.ServiceManage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/manage")
public class ControllerManage {

   @Resource
    private ServiceManage serviceManage;

   @GetMapping("/findAll")
    public ResponseEntity<BaseResult>findAll(){

       List<Manage> all = serviceManage.findAll();
       BaseResult result = new BaseResult(1, "查询成功", all);

       return ResponseEntity.ok(result);
   }
   @GetMapping("/{id}")
    public ResponseEntity<BaseResult>findId(@PathVariable("id")Integer id){

       Manage manageId = serviceManage.findId(id);

       BaseResult result = new BaseResult(1, "根据ID查询成功", manageId);

       return ResponseEntity.ok(result);
   }
}
