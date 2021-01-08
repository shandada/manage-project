//package com.controller;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.pojo.*;
//import com.service.SupplierService;
//import com.vo.Data;
//import com.vo.OneChapter;
//import com.vo.Result;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.util.List;
//import java.util.UUID;
//
///**
// * @Description:
// * @Auther: 单朋坤
// * @Date: 2020/12/29 17:32
// */
////文件controller
//@RestController
//@RequestMapping("/fileName")
//public class FileNameController {
//
//    /**
//     * 客户端,ip,秘钥
//     */
//    //TODO  ceph
////    public MyCeph cephUtils = new CephUtils("admin", "192.168.1.13:6789", "198.162.1.1.2");
//    //返回数据类型
//    Data data = new Data();
//    //返回数据信息
//    Result result = new Result();
//    //注入session
//    @Resource
//    private HttpSession session;
//    //注入供应商service
//    @Resource
//    private SupplierService supplierService;
//    //注入文件信息
////    @Resource
////    private FileNameService fileNameService;
//
//
//
//    /**
//     * 树状列表
//     *
//     * @return
//     */
////    @GetMapping("/treeAll")
////    public Result getChapterList() {
////        List<OneChapter> list = fileNameService.queryChapterAndVideoList();
//////        if(list.size()>=0){
////        //
////        data.setResults(list);
////        result.ok();
////        result.setData(data);
////        return result;
////    }
//
//    /**
//     * 查询所有
//     * 文件列表
//     *
//     * @return
//     */
//    @GetMapping("/list")
//    public Result findAll() {
//        try {
//            //获取所有数据
//            List<FileName> list = fileNameService.list(null);
//            //成功,返回数据
//            data.setResults(list);
//            result.ok();
//            result.setData(data);
//            return result;
//        } catch (Exception e) {
//            e.printStackTrace();
//            //失败,返回错误信息
//            result.error();
//            return result;
//        }
//    }

//    /**
//     * 根据供应商id 获取对应供应商信息
//     *
//     * @param gid
//     * @return
//     */
//    @GetMapping("/list/{gid}")
//    public Result findAll(@PathVariable("gid") Integer gid) {
//        try {
//            //根据gid 调用findAID方法 获取数据
//            List<Supplier> list = supplierService.findAID(gid);
//            //成功,返回数据
//            data.setResult(list);
//            result.ok();
//            result.setData(data);
//            return result;
//        } catch (Exception e) {
//            e.printStackTrace();
//            //失败,返回错误信息
//            result.error();
//            return result;
//        }
//    }
//
//    /**
//     * 文件创建,
//     *
//     * @param fileName
//     * @return
//     */
//
//    @PostMapping("/created")
//    public Result created(@RequestBody FileName fileName, MultipartFile uploadFile, HttpServletRequest request) {
//        try {
//            //创建获取session对象
//            HttpSession session = request.getSession();
//            //保存session中数据
//            // 文件名称
//            session.setAttribute("name", fileName.getHandleName());
//            //文件路径
//            session.setAttribute("url", fileName.getHandleIp());
//
//            //添加文件信息
//            fileName.setId(UUID.randomUUID().toString().replaceAll("-",""));
//            fileNameService.save(fileName);
//
//            //TODO  文件上传
////        byte[] readFile(String fileName); ceph上传
////            cephUtils.readFile(fileName.getHandleName());
//
//            result.ok();
//            return result;
//        } catch (
//                Exception e) {
//            e.printStackTrace();
//            result.error();
//            return result;
//        }
//
//    }
//
//
//    /**
//     * 更新文件
//     *
//     * @param fileName
//     * @return
//     */
//    @PutMapping("/update")
//    public Result update(@RequestBody FileName fileName) {
//        //更新文件信息并更新
//        boolean flag = fileNameService.updateById(fileName);
//        //判断 flag为true 成功, 否则失败
//        if (flag) {
//            result.ok();
//            return result;
//        }
//        result.error();
//        return result;
//    }
//
//    /**
//     * 单个文件删除
//     *
//     * @param id
//     * @return
//     */
//    @DeleteMapping("/delete/{id}")
//    public Result delete(@PathVariable("id") String id) {
//        //根据id删除文件
//        boolean flag = fileNameService.removeById(id);
//        //判断 flag为true 成功, 否则失败
//        if (flag) {
//            result.ok();
//            return result;
//        }
//        result.error();
//        return result;
//
//    }
//
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
//            fileNameService.delMany(split);
//            result.ok();
//            return result;
//        } catch (Exception e) {
//            e.printStackTrace();
//            result.error();
//            return result;
//        }
//    }
//
//    /**
//     * 文件上传
//     *
//     * @param uploadFile
//     * @return
//     */
//    @PostMapping("/uploadFile")
//    public void upload(MultipartFile uploadFile) throws IOException {
//        fileNameService.upload(uploadFile);
//    }
//
//    /***
//     * 文件下载 1.0  目前仅限于下载pdf格式.
//     * @param response
//     * @throws Exception
//     */
////    @GetMapping("/download/{handleId}")
////    public Result download(HttpServletResponse response, @PathVariable("handleId") String handleId) throws Exception {
////        return fileNameService.download(response, handleId);
////    }
//
//    /***
//     * 文件下载 2.0  目前仅限于下载pdf格式
//     * @param
//     * @throws Exception
//     */
//    @GetMapping(value = "/downFile/{handleId}")
//    public Result download(@PathVariable("handleId") Integer handleId, HttpServletRequest request) throws IOException {
//        try {
//            fileNameService.download2(handleId, request);
//            result.ok();
//            return result;
//        } catch (IOException e) {
//            e.printStackTrace();
//            result.error();
//            return result;
//        }
//    }
//}
