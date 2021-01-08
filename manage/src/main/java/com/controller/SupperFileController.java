package com.controller;

import com.pojo.Supplier;
import com.pojo.TFileInfo;
import com.service.SupplierFileService;
import com.service.TFileInfoService;
import com.vo.Data;
import com.vo.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;


/**
 * @Description: 供应商文件上传下载
 * @Auther: Shan PengKun
 * @Date: 2021/1/7 10:03
 */

@RestController
@RequestMapping("/supperFile")
public class SupperFileController {
    //注入供应商service
    @Resource
    private SupplierFileService supplierService;
    //注入文件信息
    @Resource
    private TFileInfoService tFileInfoService;

    /**
     * 供应商列表
     *
     * @return
     * @throws IOException
     */
    @GetMapping("/list")
    public Result list() throws IOException {
        try {

            //返回数据类型
            Data data = new Data();
            //返回数据信息
            Result result = new Result();
            List<Supplier> list = supplierService.list(null);
            data.setResults(list);
            result.ok();
            result.setData(data);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            Result result = new Result();
            result.error();
            return result;
        }
    }

    /**
     * 文件上传+ceph   文件信息存入数据库
     *
     * @param uploadFile
     * @param gid
     * @return
     */
    @PostMapping(value = "/uploadFile/{gid}", headers = "content-type=multipart/form-data")
    public Result upload(
            @RequestParam("uploadFile") MultipartFile uploadFile, @PathVariable("gid") String gid) {
        try {
            //文件大小
            long size = uploadFile.getSize();
            String sizes = String.valueOf(size) + "KB";
            System.out.println("sizes: " + sizes);
            //文件全名
            String filenameAll = uploadFile.getOriginalFilename();
            // gbk转utf-8，需要确定原编码格式，不知道的话就试一下
            byte[] s = filenameAll.getBytes("utf-8");
            String filename = new String(s, "UTF-8");

            //切割
            String[] split = filename.split("\\.");
            //文件上传名
            String UploadFileName = split[0];

            //后缀名
            String suffix = split[1];
            System.out.println(" filename 文件名: " + filename);
            //接受供应商gid 获取供应商信息
            Supplier supplier1 = supplierService.getById(gid);
            System.out.println(supplier1 + "supplier1");
            //获取id
            String gid1 = supplier1.getGid();
            //获取供应商name
            String name = supplier1.getName();
            //供应商类型
            String type = supplier1.getType();

            //版本号 暂定当前时间
            SimpleDateFormat df = new SimpleDateFormat("HH.mm.ss");//设置日期格式
            //版本号
            String version = df.format(new Date());
            //传给ceph的信息   供应商id+文件名.后缀名+版本号
            String file_key = gid1 + "-" + filename + "-" + version;
            System.out.println("写入ceph的 file_key :  " + file_key);
            //向数据库文件表 添加上传文件信息
            TFileInfo tFileInfo = new TFileInfo(
                    //随机文件id
                    UUID.randomUUID().toString().replaceAll("-", ""),
                    //文件名         //版本号 供应商id 文件类型      创建时间    更新时间       文件后缀名  大小  ceph_key    标签
                    filename, version, gid, name, type, null, null, suffix, sizes, file_key, "tag");

            tFileInfo.setUid(UUID.randomUUID().toString().replaceAll("-", ""));
            //添加上传文件信息到数据库
            tFileInfoService.save(tFileInfo);
            //上传到ceph
//            supplierService.upload(uploadFile, gid, file_key);
            //返回数据信息
            Result result = new Result();
            //成功,返回数据
            result.ok();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            //返回数据信息
            Result result = new Result();
            //失败,返回错误信息
            result.error();
            return result;
        }
    }

    /**
     * 文件下载 +ceph
     * 接受文件id
     *
     * @param
     * @throws Exception
     */
    @GetMapping(value = "/downFile/{uid}")
    public Result download(@PathVariable("uid") String uid) throws IOException {
        try {
            tFileInfoService.download(uid);
            Result result = new Result();
            result.ok();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            Result result = new Result();
            result.error();
            return result;
        }
    }
}
