package com.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ceph.MyCeph;
import com.ceph.utils.CephUtils;
import com.pojo.TFileInfo;
import com.pojo.query.FileQuery;
import com.util.FileUtil;
import com.vo.OneChapter;
import com.vo.TwoFile;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.activation.MimetypesFileTypeMap;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mapper.TFileInfoMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Auther: logo丶西亚卡姆
 * @Date: 2021/1/6 15:31
 */
@Service
@Transactional
public class TFileInfoService extends ServiceImpl<TFileInfoMapper, TFileInfo> {

    @Resource
    private TFileInfoMapper tFileInfoMapper;


    //树状方法
    public List<OneChapter> queryChapterAndVideoList() {

        //定义一个目录集合
        List<OneChapter> oneChapterList = new ArrayList<>();
        //先查询目录列表集合
        QueryWrapper<TFileInfo> queryWrapper = new QueryWrapper<>();
        //根据供应商id+name 去重 结果作为父节点
        queryWrapper.select("distinct supplier_id,supplier_name");
        List<TFileInfo> fileNames = baseMapper.selectList(queryWrapper);
        System.out.println("文件: "+fileNames);
        for (TFileInfo fileName : fileNames) {
            String supplierId = fileName.getSupplierId();
            System.out.println("supplierName:supplierId " + fileName.getSupplierName()+supplierId);
            queryWrapper
                    .eq("supplier_id", supplierId);
        }
        //再遍历章节集合，获取每个节点ID(版本号)
        for (TFileInfo eduChapter : fileNames) {
            OneChapter oneChapter = new OneChapter();
            BeanUtils.copyProperties(eduChapter, oneChapter);
            //再根据每个目录的ID查询节点的列表
            QueryWrapper<TFileInfo> videoWrapper = new QueryWrapper<>();
            videoWrapper.eq("supplier_id", oneChapter.getSupplierId());
            List<TFileInfo> eduVideoList = tFileInfoMapper.selectList(videoWrapper);
            //把小节的列表添加目录中
            for (TFileInfo thisFile : eduVideoList) {
                TwoFile twoFile = new TwoFile();
                BeanUtils.copyProperties(thisFile, twoFile);
                System.out.println("二级目录数据: "+twoFile);
                //数据添加到二级目录
                oneChapter.getChildren().add(twoFile);
            }
            //所有数据添加自定义list集合中,返回
            oneChapterList.add(oneChapter);
        }
        return oneChapterList;
    }

    /**
     * 分页
     *
     * @param pageParam
     * @param fileQuery
     */
    public void pageQuery(Page<TFileInfo> pageParam, FileQuery fileQuery) {
        //封装条件QueryWrapper
        QueryWrapper<TFileInfo> queryWrapper = new QueryWrapper<>();

        //为空,组装条件
        if (fileQuery == null) {
            //baseMapper是父类ServiceImpl中的实例化,子继承父,使用父类属性方法
            baseMapper.selectPage(pageParam, queryWrapper);
        }
        // 不为空,则获取条件
        //文件名模糊查询
        String fileName = fileQuery.getFileName();
        //供应商名称模糊查询
        String supplierName = fileQuery.getSupplierName();
        //版本号查询
        String tag = fileQuery.getTag();
        //创建修改时间
        String createTime = fileQuery.getCreateTime();
        String updateTime = fileQuery.getUpdateTime();

        // 姓名模糊查询  name 判断  isNoneBlank stu_name isnot null
        if (StringUtils.isNoneBlank(fileName)) {
            queryWrapper.like("file_name", fileName);//like 如:%张% ,name中带张的字段
        }
        //供应商名称模糊查询
        if (StringUtils.isNoneBlank(supplierName)) {
            queryWrapper.like("supplier_name", supplierName);
        }
        //房间号精确查询 stu_number
        if (tag != null) {
            queryWrapper.eq("tag", tag);
        }

        //性别精确模糊查询
//        if (StringUtils.isNoneBlank(stu_sex)) {
//            System.out.println(studentQuery);
//            if (stu_sex.equals("0")) {
//                studentQuery.setStu_sex("男");
//            }
//            if (stu_sex.equals("1")) {
//                studentQuery.setStu_sex("女");
//            }
//            queryWrapper.eq("stu_sex", studentQuery.getStu_sex());
//        }
        //创建时间 eq=  ge 大于等于 >=  le 小于等于<=  gt大于 >  lt小于<
        if (StringUtils.isNoneBlank(createTime)) {
            queryWrapper.ge("create_time", createTime);
        }
        //退宿时间
        if (StringUtils.isNoneBlank(updateTime)) {
            queryWrapper.le("update_time", updateTime);
        }

        //执行搜索
        baseMapper.selectPage(pageParam, queryWrapper);

    }

    /**
     * 根据id单个文件删除
     */
    public void del(String uid) {
        //调用baseMapper 删除方法
        baseMapper.deleteById(uid);
    }

    /**
     * 多个文件删除
     *
     * @param ids
     */
    public void delMany(String[] ids) {
        //遍历字符串,
        for (String id : ids) {
            //调用单个删除方法
            del(id);
        }
    }

    /**
     * 文件下载
     *
     * @param uid
     * @param
     * @throws IOException
     */
    public void download(String uid) throws IOException {

        //根据id获取服务器文件url
        TFileInfo tFileInfo = tFileInfoMapper.selectById(uid);
        String fileName = tFileInfo.getFileName();
        //拼接的文件名
        String fileKey = tFileInfo.getFileKey();
        System.out.println("service下载的文件名1: " + fileName);

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = requestAttributes.getResponse();
        // 设置contenttype，即告诉客户端所发送的数据属于什么类型
        String type = new MimetypesFileTypeMap().getContentType(fileName);
        // 设置contenttype，即告诉客户端所发送的数据属于什么类型
        response.setHeader("Content-type", type);
        // 设置编码
        String hehe = new String(fileName.getBytes("utf-8"), "iso-8859-1");
        // 设置扩展头，当Content-Type 的类型为要下载的类型时 , 这个信息头会告诉浏览器这个文件的名字和类型。
        response.setHeader("Content-Disposition", "attachment;filename=" + hehe);
        FileUtil.download(fileName, response, fileKey);
    }
}

