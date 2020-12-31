package com.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @Description:
 * @Auther: logo丶西亚卡姆
 * @Date: 2020/12/22 14:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
//供应商实体类
public class Supplier {
    /**
     * 供应商id
     */
    private static final long serialVersionUID = 1L;
    @TableId(value = "gid", type = IdType.AUTO)
    private Integer gid;

    /**
    * 供应商名称
    */
    private String name;

    /**
    * 创建时间
    */
    private String createTime;

    /**
    * 更新时间
    */
    private String updateTime;

    /**
    * 供应商类型
    */
    private String type;

    /**
    * 文件夹名称
    */
    private String folord_name;

    /**
     * 文件夹ID
     */
    private Integer fid;

    private Integer tid;



}