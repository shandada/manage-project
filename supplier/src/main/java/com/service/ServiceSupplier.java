package com.service;

import com.common.*;
import com.mapper.MapperSupplier;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pojo.Supplier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;
import java.util.UUID;

@Service
public class ServiceSupplier {
    @Resource
    private HttpSession session;
    @Resource
    private MapperSupplier mapperSupplier;

    @Resource
    private ServiceManage serviceManage;


    //分页查询和模糊查询
    public PageResult findAll(PageRequest pageRequest) {
        //缕空判断
        if (pageRequest == null) {
            PageResult pageResult = new PageResult();
            pageResult.setCode(ResultCode.ERROR);
            pageResult.setSuccess(false);
            return pageResult;
        }
        //实体化Supplier类
        Example example = new Example(Supplier.class);

        //判断根据条件进行模糊查询
        if (pageRequest.getKey() != null && (!"".equals(pageRequest.getKey()))) {
            example.createCriteria().andLike("name", "%" + pageRequest.getKey() + "%");
        }

        //用pageHelper分页助手将，当前页和当前条储存
        PageHelper.startPage(pageRequest.getPageNo(), pageRequest.getPageSize());
        //使用Mybatis中的selectByExample返回List集合的值
        List<Supplier> suppliers = mapperSupplier.selectByExample(example);
        for (Supplier supplier : suppliers) {

        }

        //将List的值储存到PageInfo分页助手里，进行分页查询
        PageInfo<Supplier> info = new PageInfo<>(suppliers);
        //封装pageresult
        PageResult pr = new PageResult();
        pr.setCode(ResultCode.OK);
        Data resultData = new Data();
        resultData.setResults(info.getList());
        resultData.setTotal(info.getTotal());
        pr.setData(resultData);
        pr.setSuccess(true);
        return pr;
    }

    //创建
    public void add(Supplier supplier) {

        mapperSupplier.insert(supplier);

    }

    //更新
    public void update(Supplier supplier) {
        mapperSupplier.updateByPrimaryKey(supplier);
    }

    //删除
    public void delete(String gid) {
        mapperSupplier.deleteByPrimaryKey(gid);
    }

    //批量删除
    public void deleteP(String[] idp) {

        for (String gid : idp) {
            delete(gid);
        }

    }

    //文件上传下载
    /**
     * 文件上传
     *
     * @param uploadFile
     * @return
     */
    public void upload(MultipartFile uploadFile) throws IOException {

        //根据添加获取文件name url
        String name = (String) session.getAttribute("name");
        String url = (String) session.getAttribute("url");
        System.out.println(name);
        System.out.println(url);

        //file 这里是上传到服务器的路径
        String file = "D:\\work\\2020-12-09\\manage\\src\\main\\resources\\static\\manage\\";

        //TODO  文件上传
//        byte[] readFile(String fileName); ceph上传
//        cephUtils.readFile(name);
        //file文件
        File folder = new File(file);

//        //文件大小
        String length = folder.length() + "KB";
        System.out.println("文件" + file + "的大小是：" + length + "\n");

        //判断文件目录是否存在
        if (!folder.isDirectory()) {
            //不存在则创建
            folder.mkdirs();
        }
        //获取文件名操作
        String oldName = uploadFile.getOriginalFilename();
        String newName = UUID.randomUUID().toString() +
                oldName.substring(oldName.lastIndexOf("."), oldName.length());
        try {
            // 文件保存操作
            uploadFile.transferTo(new File(folder, newName));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /***
     * 文件下载 2.0
     * @param
     * @throws Exception
     */

//    public ResponseEntity<BaseResult> download2(@PathVariable("handleId") Integer handleId, HttpServletRequest request) throws IOException {
//        //根据id获取服务器文件url
//        FileName fileName1 = serviceManage.(handleId);
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
//
//        // 设置扩展头，当Content-Type 的类型为要下载的类型时 , 这个信息头会告诉浏览器这个文件的名字和类型。
//        response.setHeader("Content-Disposition", "attachment;filename=" + hehe);
//        com.util.FileUtil fileUtil = new com.util.FileUtil();
//        fileUtil.download(handleUrl, response, request);
//    }
}
