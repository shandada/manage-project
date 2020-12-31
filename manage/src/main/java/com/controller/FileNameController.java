package com.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pojo.*;
import com.pojo.FileName;
import com.service.FileNameService;
import com.service.ManagerService;
import com.service.SupplierService;
import com.service.VersionsService;
import com.vo.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @Description:
 * @Auther: logo丶西亚卡姆
 * @Date: 2020/12/29 17:32
 */
//文件controller
@RestController
@RequestMapping("/fileName")
public class FileNameController {

    //注入session
//    @Resource
    HttpSession session;

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
     * 查询所有
     * 文件列表
     *
     * @return
     */
    @GetMapping("/list")
    public Result findAll() {
        try {
            //获取所有数据
            List<FileName> list = fileNameService.findAll();
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
    @GetMapping("/list/{gid}")
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
     * 文件创建,文件上传
     *
     * @param fileName
     * @return
     */
    @PostMapping("/created")
    public Result created(@RequestBody FileName fileName, MultipartFile uploadFile) {
        try {
            // 创建文件信息时,存储信息到session
//            session.setAttribute("files", fileName);

            //添加文件信息
            fileNameService.save(fileName);
            //上传文件
            fileNameService.upload(uploadFile);


            //添加版本号,判断接收的版本号是否为空 不为空则执行if
            if (fileName.getVersionsIDs() != null) {
                //获取版本号id
                Integer vid = fileName.getVid();
                // 获取版本号字符串
                String[] versions = fileName.getVersionsIDs().split(",");
                // 遍历版本号
                for (String version : versions) {
                    //依次添加
                    Versions versions1 = new Versions(vid, version);
                    versionsService.save(versions1);
                }
            }
            return Result.ok().message("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }


    /**
     * 更新文件
     *
     * @param fileName
     * @return
     */
    @PutMapping("/update")
    public Result update(@RequestBody FileName fileName) {
        //更新文件信息并更新
        boolean flag = fileNameService.updateById(fileName);
        //判断 flag为true 成功, 否则失败
        if (flag) {

            //更新版本号
            //判断接收的版本号是否为空 不为空则执行if
            if (fileName.getVersionsIDs() != null) {
                //获取版本号id
                Integer vid = fileName.getVid();
                // 获取版本号字符串
                String[] versions = fileName.getVersionsIDs().split(",");
                //根据先版本号vid 删除之前的版本号信息
                QueryWrapper<Versions> wrapper = new QueryWrapper<>();
                //ID查询
                wrapper.eq("vid", vid);
                //删除
                versionsService.remove(wrapper);

                // 遍历版本号
                for (String version : versions) {
                    //依次添加最新修改的版本号
                    Versions versions1 = new Versions(vid, version);
                    versionsService.save(versions1);
                }
            }
            return Result.ok().message("更新成功");
        }
        return Result.error();
    }

    /**
     * 单个文件删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Integer id) {
        //**先删除版本号信息
        //先获取版本号 vid  删除版本号
        FileName byId = fileNameService.getById(id);
        Integer vid = byId.getVid();
        // 版本号删除
        //根据版本号vid 删除版本号信息
        QueryWrapper<Versions> wrapper = new QueryWrapper<>();
        //ID查询
        wrapper.eq("vid", vid);
        //删除
        versionsService.remove(wrapper);

        //根据id删除文件
        boolean flag = fileNameService.removeById(id);
        //判断 flag为true 成功, 否则失败
        if (flag) {

            return Result.ok().message("删除成功");
        }
        return Result.error();

    }


    /**
     * 多个文件删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/delMany/{ids}")
    public Result delMany(@PathVariable("ids") String ids) {

        //根据多个id删除
        try {
            //先根据多个id 删除多个版本号
            String[] split = ids.split(",");
            for (String thiId : split) {
                //转换integer类型
                int id = Integer.parseInt(thiId);
                System.out.println(id);
                //**删除版本号信息
                //获取版本号 vid  删除版本号
                FileName byId = fileNameService.getById(id);
                Integer vid = byId.getVid();
                // 版本号删除
                //根据版本号vid 删除版本号信息
                QueryWrapper<Versions> wrapper = new QueryWrapper<>();
                //ID查询
                wrapper.eq("vid", vid);
                //删除
                versionsService.remove(wrapper);
            }

            //删除多个文件夹
            //调用自定义的多个删除方法
            fileNameService.delMany(split);
            return Result.ok().message("批量删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error().message("批量删除失败");
        }
    }

    /**
     * 文件上传
     *
     * @param uploadFile
     * @return
     */
    @PostMapping("/uploadFile")
    public void upload(MultipartFile uploadFile) throws IOException {
        fileNameService.upload(uploadFile);
    }

    /***
     * 文件下载   目前仅限于下载pdf格式
     * @param response
     * @throws Exception
     */
    @GetMapping("/downFile")
    public Result download(HttpServletResponse response) throws Exception {
        return fileNameService.download(response);
    }
}
