package com.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @Description:
 * @Auther: logo丶西亚卡姆
 * @Date: 2020/12/22 14:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
//供应商实体类
@Table(name = "supplier")
public class Supplier {
    /**
     * 供应商id
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private static final long serialVersionUID = 1L;
    @TableId(value = "gid", type = IdType.ASSIGN_UUID)
    private String gid;
    /**
     * 供应商名称
     */
    private String name;


    //客户名称
    private String customName;
    //客户手机号
    private String customPhone;
    //状态，1在状态，0不在
    private String state;

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
     * 文件夹ID
     */
    private String fid;
    /**
     * 文件名称
     */
    private String folordName;


}