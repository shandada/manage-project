package com.controller;

import com.pojo.Versions;
import com.service.FileNameService;
import com.service.ManagerService;
import com.service.SupplierService;
import com.service.VersionsService;
import com.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 版本号controller
 * @Auther: logo丶西亚卡姆
 * @Date: 2020/12/29 17:58
 */
@RestController
@RequestMapping("/versions")
public class VersionsController {
    //注入文件夹service
    @Resource
    private ManagerService managerService;
    //注入供应商service
    @Resource
    private SupplierService supplierService;
    //注入文件信息
    @Resource
    private FileNameService fileNameService;
    //版本号注入
    @Resource
    private VersionsService versionsService;


    /**
     * 查询所有版本号
     */
    @GetMapping("/find")
    public Result findVersionID2() {
        List<Versions> versions = versionsService.list(null);
        return Result.ok().result("versions", versions);
    }

    @PostMapping("/add")
    public boolean created(@RequestBody Versions versions) {
        return versionsService.save(versions);

    }


    /**
     * 版本号查询
     *
     * @param vid
     * @return
     */
    @GetMapping("/{vid}")
    public Result findVersionID(@PathVariable("vid") Integer vid) {
        try {
            //根据versionId 调用findVersionID方法 获取数据
            List<Versions> versions = versionsService.findVersionID(vid);
            return Result.ok().result("versions", versions);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error().message("获取失败");
        }
    }


}
