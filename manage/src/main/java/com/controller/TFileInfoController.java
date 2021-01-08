package com.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pojo.Supplier;
import com.pojo.TFileInfo;
import com.pojo.query.FileQuery;
import com.service.SupplierFileService;
import com.service.TFileInfoService;
import com.vo.Data;
import com.vo.OneChapter;
import com.vo.PageRequest;
import com.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

/**
 * @Description:
 * @Auther: logo丶西亚卡姆
 * @Date: 2021/1/6 16:45
 */
//文件controller
@RestController
@RequestMapping("/file/fileName")
@Api(description = "文件管理")
public class TFileInfoController {
    /**
     * 客户端,ip,秘钥
     */
    //TODO  ceph
//    public MyCeph cephUtils = new CephUtils("admin", "192.168.1.13:6789", "198.162.1.1.2");
    //返回数据类型
    Data data = new Data();
    //返回数据信息
    Result result = new Result();
    //注入session
    @Resource
    private HttpSession session;
    //注入供应商service
    @Resource
    private SupplierFileService supplierService;
    //注入文件信息
    @Resource
    private TFileInfoService tFileInfoService;

    /**
     * 树状目录
     *
     * @return
     */
    @GetMapping("/treeAll")
    public Result getChapterList() {
        List<OneChapter> list = tFileInfoService.queryChapterAndVideoList();
//        if(list.size()>=0){
        //
        data.setResults(list);
        result.ok();
        result.setData(data);
        return result;

    }

    /**
     * 分页查询所有
     * 文件列表
     *
     * @return
     */
    @GetMapping("/findAll")
    public Result findAll(PageRequest pageRequest, FileQuery fileQuery) {
        //获取页码
        Integer pageNo = pageRequest.getPageNo();
        //每页条数
        Integer pageSize = pageRequest.getPageSize();
        System.out.println("pageNo" + pageNo);
        System.out.println("pageSize" + pageSize);
        try {
            //获取所有数据
//            分页使用map的page对象
            Page<TFileInfo> pageParam = new Page<>(pageNo, pageSize);
//            2.条件分页查询
            tFileInfoService.pageQuery(pageParam, fileQuery);
            //返回结果
            List<TFileInfo> list = pageParam.getRecords();
            long total1 = pageParam.getTotal();
            System.out.println(total1);
//            成功,返回数据
            data.setResults(list);
            data.setTotal(total1);
            result.ok();
            result.setData(data);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            //失败,返回错误信息
            result.error();
            return result;
        }
    }


    /**
     * 根据供应商id 获取对应供应商信息
     *
     * @param gid
     * @return
     */
    @GetMapping("/list/{gid}")
    public Result findAll(@PathVariable("gid") String gid) {
        try {
            //根据gid 调用findAID方法 获取数据
            List<Supplier> list = supplierService.findAID(gid);
            Supplier byId = supplierService.getById(gid);
            System.out.println(byId + "byId");
            //成功,返回数据
            data.setResult(list);
            result.ok();
            result.setData(data);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            //失败,返回错误信息
            result.error();
            return result;
        }
    }

    /**
     * 文件创建,
     *
     * @param fileName
     * @return
     */

    @PostMapping("/created")
    public Result created(@RequestBody TFileInfo fileName, HttpServletRequest request) {
        try {
//            //创建获取session对象
//            HttpSession session = request.getSession();
//            //保存session中数据
//            //供应商id
//            session.setAttribute("id", fileName.getSupplierId());
//            //供应商name
//            session.setAttribute("name", fileName.getSupplierName());

            //添加文件信息
            fileName.setUid(UUID.randomUUID().toString().replaceAll("-", ""));
            tFileInfoService.save(fileName);
            Result result = new Result();
            result.ok();
            return result;
        } catch (
                Exception e) {
            e.printStackTrace();
            Result result = new Result();
            result.error();
            return result;
        }

    }

    /**
     * 更新文件
     *
     * @param fileName
     * @return
     */
    @PutMapping("/update")
    public Result update(@RequestBody TFileInfo fileName) {
        //更新文件信息并更新
        boolean flag = tFileInfoService.updateById(fileName);
        //判断 flag为true 成功, 否则失败
        if (flag) {
            Result result = new Result();
            result.ok();
            return result;
        }
        Result result = new Result();
        result.error();
        return result;
    }
//
//    /**
//     * 单个文件删除
//     *
//     * @param uid
//     * @return
//     */
//    @DeleteMapping("/delete/{uid}")
//    public Result delete(@PathVariable("uid") String uid) {
//        //根据id删除文件
//        boolean flag = tFileInfoService.removeById(uid);
//        //判断 flag为true 成功, 否则失败
//        if (flag) {
//            Result result = new Result();
//            result.ok();
//            return result;
//        }
//        Result result = new Result();
//        result.error();
//        return result;
//
//    }
//
//    /**
//     * 多个文件删除
//     *
//     * @param ids
//     * @return
//     */
//    @DeleteMapping("/delMany/{ids}")
//    public Result delMany(@PathVariable("ids") String ids) {
//
//        //根据多个id删除
//        try {
//            //先根据多个id 删除多个版本号
//            String[] split = ids.split(",");
//            //删除多个文件夹
//            //调用自定义的多个删除方法
//            tFileInfoService.delMany(split);
//            Result result = new Result();
//
//            result.ok();
//            return result;
//        } catch (Exception e) {
//            e.printStackTrace();
//            Result result = new Result();
//            result.error();
//            return result;
//        }
//    }

    /**
     * 接收String[] 数组 单个和批量删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/delete")
    public Result delMany(@RequestBody String[] ids) {
        try {
            //删除多个文件
            //调用自定义的多个删除方法
            tFileInfoService.delMany(ids);
            Result result = new Result();
            result.ok();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            Result result = new Result();
            result.error();
            return result;
        }
    }
}
