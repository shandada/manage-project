package com.service;

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

        //实体化Supplier类
        Example example = new Example(Supplier.class);

        //判断根据条件进行模糊查询
        if (pageRequest.getKey() != null && (!"".equals(pageRequest.getKey()))) {
            example.createCriteria().andLike("name", "%" + pageRequest.getKey() + "%");
        }

        //用pageHelper分页助手将，当前页和当前条储存
        PageHelper.startPage(pageRequest.getPage(), pageRequest.getRows());
        //使用Mybatis中的selectByExample返回List集合的值
        List<Supplier> suppliers = mapperSupplier.selectByExample(example);
        for (Supplier supplier : suppliers) {
            supplier.setManage(serviceManage.findId(supplier.getFid()));
            supplier.setTags(serviceTags.findId(supplier.getTid()));
        }

         //将List的值储存到PageInfo分页助手里，进行分页查询
        PageInfo<Supplier> info = new PageInfo<>(suppliers);
        //最后使用PageResult进行统计数据
        PageResult pr = new PageResult();
        pr.setCode(1);
        pr.setMsg("成功");
        pr.setTotal(info.getTotal());
        pr.setData(info.getList());

        return pr;
    }
   //创建
    public void add(Supplier supplier){

        mapperSupplier.insert(supplier);

    }
   //更新
   public void update(Supplier supplier){
        mapperSupplier.updateByPrimaryKey(supplier);
   }
   //删除
    public void delete(Integer gid){
        mapperSupplier.deleteByPrimaryKey(gid);
    }

    //批量删除
    public void deleteP(String [] idp){

        for (String gid : idp) {
          delete(Integer.parseInt(gid));
        }

    }
}
