package com.controller;

import com.common.BaseResult;
import com.common.PageRequest;
import com.common.PageResult;
import com.pojo.Supplier;
import com.service.ServiceSupplier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/supplier")
public class ControllerSupplier {

    @Resource
    private ServiceSupplier serviceSupplier;

  //查询
    @GetMapping("/findAll")
    public ResponseEntity<PageResult>findAll(PageRequest pageRequest){

        PageResult all = serviceSupplier.findAll(pageRequest);

        return ResponseEntity.ok(all);
    }
    //创建
    @PostMapping("/add")
    public ResponseEntity<BaseResult>add(@RequestBody Supplier supplier){

        serviceSupplier.add(supplier);

        BaseResult result = new BaseResult(1, "创建成功");

        return ResponseEntity.ok(result);
    }
    //更新
    @PutMapping("/update")
    public ResponseEntity<BaseResult>update(@RequestBody Supplier supplier){
        serviceSupplier.update(supplier);

        BaseResult result = new BaseResult(1, "更新成功");

        return ResponseEntity.ok(result);
    }
    //删除
    @DeleteMapping("/{gid}")
    public ResponseEntity<BaseResult>delete(@PathVariable("gid") Integer gid){
        serviceSupplier.delete(gid);

        BaseResult result = new BaseResult(1, "删除成功");

        return ResponseEntity.ok(result);
    }
    //批量删除
    @DeleteMapping("/pi/{idp}")
    public ResponseEntity<BaseResult>deleteP(@PathVariable("idp") String idp){

        String[] split = idp.split(",");

         serviceSupplier.deleteP(split);

        BaseResult result = new BaseResult(1, "批量删除成功");

        return ResponseEntity.ok(result);
    }
}
