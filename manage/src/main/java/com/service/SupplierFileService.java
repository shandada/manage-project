package com.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ceph.MyCeph;
import com.ceph.utils.CephUtils;
import com.mapper.TFileInfoMapper;
import com.pojo.TFileInfo;
import com.util.FileUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Service;

import com.pojo.Supplier;
import com.mapper.SupplierMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.MimetypesFileTypeMap;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Description:
 * @Auther: logo丶西亚卡姆
 * @Date: 2020/12/22 14:07
 */
//供应商service类
@Service
@Transactional
public class SupplierFileService extends ServiceImpl<SupplierMapper, Supplier> {

    @Resource
    private TFileInfoService tFileInfoService;

    /**
     * 上传文件。
     *
     * @param uploadFile 上传的文件
     * @return 文件实体对象
     */
    public void upload(MultipartFile uploadFile, String gid, String writeFile1) throws IOException {
        byte[] file = uploadFile.getBytes();
        MyCeph myCeph = new CephUtils("admin", "192.168.1.13", "AQArI9hfvp36IRAAFhB7U6t6ltcLSfHciZiy0A==");
        //拼接 文件 key
        myCeph.writeFile(writeFile1, file);
    }


    //根据供应商gid,查询相关信息
    public List<Supplier> findAID(String gid) {
        //使用QueryWrapper 构造器
        QueryWrapper<Supplier> wrapper = new QueryWrapper<>();
        //eq 等于("数据库id","传的gid")

        wrapper.eq("gid", gid);
        //返回根据 QueryWrapper条件 查询的全部记录
        return baseMapper.selectList(wrapper);
    }
}
