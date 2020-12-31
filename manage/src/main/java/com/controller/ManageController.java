package com.controller;

import com.pojo.FileName;
import com.pojo.Manage;
import com.pojo.Supplier;
import com.service.FileNameService;
import com.service.ManagerService;
import com.service.SupplierService;
import com.service.VersionsService;
import com.vo.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Description: controller类
 * @Auther: logo丶西亚卡姆
 * @Date: 2020/12/21 14:57
 */

@RestController
@RequestMapping("/fileManagement")//根路径
public class ManageController {

    //注入文件夹service
    @Resource
    private ManagerService managerService;
    //注入供应商service
    @Resource
    private SupplierService supplierService;
    //注入文件service
    //注入文件信息
    @Resource
    private FileNameService fileNameService;
    //版本号注入
    @Resource
    private VersionsService versionsService;

    /**
     * 查询所有
     * 文件夹列表
     *
     * @return
     */
    @GetMapping("/list")
    public Result findAll() {
        try {
            //获取所有数据
            List<Manage> list = managerService.list(null);
            return Result.ok().result("list", list);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }

    }

    /**
     * 根据文件夹id handleId 获取对应文件信息
     *
     * @param handleId
     * @return
     */
    @GetMapping("/fileName/{handleId}")
    public Result findAllFileName(@PathVariable("handleId") Integer handleId) {
        try {
            //根据gid 调用findAID方法 获取数据
            List<FileName> list = fileNameService.findAID(handleId);
            //成功,返回数据
            return Result.ok().result("list", list);
        } catch (Exception e) {
            e.printStackTrace();
            //失败,返回错误信息
            return Result.error();
        }
    }
    /**
     * 根据供应商id 获取对应供应商信息
     *
     * @param gid
     * @return
     */
    @GetMapping("/supper/{gid}")
    public Result findAll(@PathVariable("gid") Integer gid) {
        try {
            //根据gid 调用findAID方法 获取数据
            List<Supplier> list = supplierService.findAID(gid);
            //成功,返回数据
            return Result.ok().result("list", list);
        } catch (Exception e) {
            e.printStackTrace();
            //失败,返回错误信息
            return Result.error();
        }
    }
    /**
     * 文件夹创建
     *
     * @param manage
     * @return
     */
    @PostMapping("/created")
    public Result created(@RequestBody Manage manage) {
        try {
            //添加数据
            managerService.save(manage);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }

    /**
     * 更新文件夹
     *
     * @param manage
     * @return
     */
    @PutMapping("/update")
    public Result update(@RequestBody Manage manage) {
        //更新文件信息并更新
        boolean flag = managerService.updateById(manage);
        //判断 flag为true 成功, 否则失败
        if (flag) {
            return Result.ok().message("更新成功");
        }
        return Result.error();
    }

    /**
     * 单个文件夹删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Integer id) {
        //根据id单个删除
        boolean flag = managerService.removeById(id);
        //判断 flag为true 成功, 否则失败
        if (flag) {
            return Result.ok().message("删除成功");
        }
        return Result.error();
    }

    /**
     * 多个文件夹删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/delMany/{ids}")
    public Result delMany(@PathVariable("ids") String ids) {
        String[] split = ids.split(",");
        //根据多个id删除
        try {
            //调用自定义的多个删除方法
            managerService.delMany(split);
            return Result.ok().message("批量删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error().message("批量删除失败");
        }

    }

//    /**
//     * 文件上传
//     *
//     * @param uploadFile
//     * @return
//     */
//    @PostMapping("/uploadFile")
//    public Result upload(MultipartFile uploadFile) {
//
//        Result upload = managerService.upload(uploadFile);
//        return upload;
//    }
//
//    /***
//     * 文件下载   目前仅限于下载pdf格式
//     * @param response
//     * @throws Exception
//     */
//    @GetMapping("/downFile")
//    public Result download(HttpServletResponse response) throws Exception {
//        return managerService.download(response);
//    }
}