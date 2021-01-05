package com.service;

import com.common.CommonUtils;
import com.common.Data;
import com.common.PageRequest;
import com.common.PageResult;
import com.mapper.MapperSupplier;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pojo.Supplier;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ServiceSupplier {

    @Resource
    private MapperSupplier mapperSupplier;

    @Resource
    private ServiceManage serviceManage;

    @Resource
    private ServiceTags serviceTags;

    //分页查询和模糊查询
    public PageResult findAll(PageRequest pageRequest) {
        //缕空判断
        if (pageRequest == null) {
            PageResult pageResult = new PageResult();
            pageResult.setCode(CommonUtils.FILE_CODE);
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
            supplier.setManage(serviceManage.findId(supplier.getFid()));

        }

        //将List的值储存到PageInfo分页助手里，进行分页查询
        PageInfo<Supplier> info = new PageInfo<>(suppliers);
        //封装pageresult
        PageResult pr = new PageResult();
        pr.setCode(CommonUtils.SUCCESS_CODE);
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
}
