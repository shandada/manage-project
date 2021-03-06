//package com.service;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
//import com.pojo.FileName;
//import com.util.FileUtil;
//import com.vo.OneChapter;
//import com.vo.Result;
//import com.vo.TwoFile;
//import org.springframework.beans.BeanUtils;
//import org.springframework.stereotype.Service;
//
//import javax.activation.MimetypesFileTypeMap;
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import com.mapper.FileNameMapper;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
///**
// * @Description:
// * @Auther: logo丶西亚卡姆
// * @Date: 2020/12/29  16:43
// */
//@Service
//@Transactional
//public class FileNameService extends ServiceImpl<FileNameMapper, FileName> {
//    /**
//     * 客户端,ip,秘钥
//     */
//    //TODO  ceph
////    public MyCeph cephUtils = new CephUtils("admin", "192.168.1.13:6789", "198.162.1.1.2");
//
////注入session
//    @Resource
//    private HttpSession session;
//
//    //注入文件mapper
//    @Resource
//    private FileNameMapper fileNameMapper;
//    //注入版本号mapper
//
//
//
//    @Resource
//    private FileNameService fileNameService;
//

//
//    //QueryWrapper 查询所有,并关联查询版本号
////    public List<FileName> findAll() {
////        QueryWrapper<FileName> wrapper = new QueryWrapper<>();
////        //拿到所有文件
////        List<FileName> list = fileNameMapper.selectList(wrapper);
////        //遍历文件
////        for (FileName fileName : list) {
////            //根据文件版本号id,查询所有版本号
////            fileName.setVersions(versionsMapper.selectById(fileName.getVid()));
////        }
////        return list;
////    }
//
//
//    /**
//     * 根据id单个文件夹删除
//     */
//    public void del(String id) {
//
//
//        //调用baseMapper 删除方法
//        baseMapper.deleteById(id);
//    }
//
//    /**
//     * 多个文件夹删除
//     *
//     * @param ids
//     */
//
//    public void delMany(String[] ids) {
//        //遍历字符串,
//        for (String id : ids) {
//            //转换integer类型
////            int i = Integer.parseInt(id);
//            //调用单个删除方法
//            del(id);
//        }
//    }
//
//    //文件id查询
//    public List<FileName> findAID(String handleId) {
//        QueryWrapper<FileName> wrapper = new QueryWrapper<>();
//        wrapper.eq("handle_id", handleId);
//        return baseMapper.selectList(wrapper);
//    }
//
//
//    /**
//     * 文件上传
//     *
//     * @param uploadFile
//     * @return
//     */
//    public void upload(MultipartFile uploadFile) throws IOException {
//
//        //根据添加获取文件name url
//        String name = (String) session.getAttribute("name");
//        String url = (String) session.getAttribute("url");
//        System.out.println(name);
//        System.out.println(url);
//
//        //file 这里是上传到服务器的路径
//        String file = "D:\\work\\2020-12-09\\manage\\src\\main\\resources\\static\\manage\\";
//
//        //TODO  文件上传
////        byte[] readFile(String fileName); ceph上传
////        cephUtils.readFile(name);
//        //file文件
//        File folder = new File(file);
//
////        //文件大小
//        String length = folder.length() + "KB";
//        System.out.println("文件" + file + "的大小是：" + length + "\n");
//
//        //判断文件目录是否存在
//        if (!folder.isDirectory()) {
//            //不存在则创建
//            folder.mkdirs();
//        }
//        //获取文件名操作
//        String oldName = uploadFile.getOriginalFilename();
//        String newName = UUID.randomUUID().toString() +
//                oldName.substring(oldName.lastIndexOf("."), oldName.length());
//        try {
//            // 文件保存操作
//            uploadFile.transferTo(new File(folder, newName));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    /***
//     * 文件下载 1.0  目前仅限于下载pdf格式
//     * @param response
//     * @throws Exception
//     */
//    public Result download(HttpServletResponse response, String handleId) throws Exception {
//
//        //根据id获取服务器文件url
//        FileName fileName1 = fileNameMapper.selectById(handleId);
//        String handleUrl = fileName1.getHandleIp();
//        String handleName = fileName1.getHandleName();
//
//        // 文件地址，真实环境是存放在服务器中的
////        File file = new File("D:\\file\\a.pdf");
//        File file = new File(handleUrl);
//        // 穿件输入对象
//        FileInputStream fis = new FileInputStream(file);
//        // 设置相关格式
//        response.setContentType("application/force-download");
//        // 设置下载后的文件名以及header
//        OutputStream os = response.getOutputStream();
//        //读取信息
//        byte[] buf = new byte[1024];
//        int len = 0;
//        while ((len = fis.read(buf)) != -1) {
//            //TODO  文件下载 // void writeFile(String fileName, byte[] file);
//            cephUtils.writeFile(file.getName(), buf);
//            //写入
//            os.write(buf, 0, len);
//        }
//        //关闭
//        fis.close();
//        Result result = new Result();
//        result.error();
//        return result;
//    }
//
//
//    /***
//     * 文件下载 2.0
//     * @param
//     * @throws Exception
//     */
//
//    public void download2(@PathVariable("handleId") Integer handleId, HttpServletRequest request) throws IOException {
//        //根据id获取服务器文件url
//        FileName fileName1 = fileNameMapper.selectById(handleId);
//        String handleUrl = fileName1.getHandleIp();
//
//        //name存储到session,下载时获取文件名称
//        request.getSession().setAttribute("handleName", fileName1.getHandleName());
//        //url存储到session,下载时获取文件后缀名
//        request.getSession().setAttribute("handleUrl", fileName1.getHandleIp());
//        System.out.println("handleName: " + fileName1.getHandleName());
//        System.out.println("handleUrl: " + fileName1.getHandleIp());
//
//        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletResponse response = requestAttributes.getResponse();
//        // 设置信息给客户端不解析
//        String type = new MimetypesFileTypeMap().getContentType(handleUrl);
//        // 设置contenttype，即告诉客户端所发送的数据属于什么类型
//        response.setHeader("Content-type", type);
//        // 设置编码
//        String hehe = new String(handleUrl.getBytes("utf-8"), "iso-8859-1");
//            //TODO  文件下载 // void writeFile(String fileName, byte[] file);
//            cephUtils.writeFile(file.getName(), buf);
//        // 设置扩展头，当Content-Type 的类型为要下载的类型时 , 这个信息头会告诉浏览器这个文件的名字和类型。
//        response.setHeader("Content-Disposition", "attachment;filename=" + hehe);
//        FileUtil.download(handleUrl, response, request);
//    }
//
//
//}
