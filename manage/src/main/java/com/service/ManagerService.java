package com.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ceph.MyCeph;
import com.ceph.utils.CephUtils;
import com.pojo.Manage;
import com.mapper.ManageMapper;
import com.sun.xml.internal.bind.v2.TODO;
import com.vo.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

/**
 * @Description:
 * @Auther: logo丶西亚卡姆
 * @Date: 2020/12/21 15:00
 */
//文件管理service类
@Service
@Transactional
//继承ServiceImpl ,ServiceImpl中继承了BaseMapper  BaseMapper自带crud方法.后续直接调用即可
public class ManagerService extends ServiceImpl<ManageMapper, Manage> {

    /**
     * 根据id单个文件夹删除
     */
    public void del(Integer id) {
        //调用baseMapper 删除方法
        baseMapper.deleteById(id);
    }

    /**
     * 多个文件夹删除
     *
     * @param ids
     */

    public void delMany(String[] ids) {
        //遍历字符串,
        for (String id : ids) {
            //转换integer类型
            int i = Integer.parseInt(id);
            //调用单个删除方法
            del(i);
        }
    }
}
