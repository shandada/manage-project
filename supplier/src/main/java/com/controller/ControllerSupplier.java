package com.controller;

import com.common.BaseResult;
import com.common.Data;
import com.common.PageRequest;
import com.common.PageResult;
import com.pojo.Supplier;
import com.service.ServiceSupplier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @Auther: 杨爽
 */
@RestController
@RequestMapping("/supplier")
public class ControllerSupplier {

    @Resource
    private ServiceSupplier serviceSupplier;


    //分页查询
    @GetMapping("/findAll")
    public ResponseEntity<PageResult> findAll(PageRequest pageRequest) {
        try {
            //调用分页方法
            PageResult all = serviceSupplier.findAll(pageRequest);
            //返回值
            return ResponseEntity.ok(all);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //创建
    @PostMapping("/add")
    public ResponseEntity<BaseResult> add(@RequestBody Supplier supplier) {
        try {
            supplier.setGid(UUID.randomUUID().toString().replaceAll("-", ""));
            serviceSupplier.add(supplier);
            BaseResult result = new BaseResult();
            result.ok();
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            BaseResult result = new BaseResult();
            result.error();
            return ResponseEntity.ok(result);
        }
    }

    //更新
    @PutMapping("/update")
    public ResponseEntity<BaseResult> update(@RequestBody Supplier supplier) {
        try {
            serviceSupplier.update(supplier);
            BaseResult result = new BaseResult();
            result.ok();
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            BaseResult result = new BaseResult();
            result.error();
            return ResponseEntity.ok(result);
        }
    }

    //删除
//    @DeleteMapping("/{delete}")
//    public ResponseEntity<BaseResult> delete(@PathVariable("delete") String gid) {
//        try {
//            serviceSupplier.delete(gid);
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

    //批量删除
//    @DeleteMapping(value = "delete[]")
//    public ResponseEntity<BaseResult> deleteP(@PathVariable("delete") String[] idp) {
//
//        try {
//            String[] split = idp.split(",");
//            serviceSupplier.deleteP(split);
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
    /**
     * 接收String[] 数组 单个和批量删除
     * @param idp
     * @return
     */
    @DeleteMapping("/delete")
    public ResponseEntity<BaseResult> deleteP(@RequestBody String[] idp) {
        try {
//            String[] split = idp.(",");
            serviceSupplier.deleteP(idp);
            BaseResult result = new BaseResult();
            result.ok();
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            BaseResult result = new BaseResult();
            result.error();
            return ResponseEntity.ok(result);
        }
    }
}
