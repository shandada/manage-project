package com.pojo.query;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Description:
 * @Auther: logo丶西亚卡姆
 * @Date: 2021/1/6 17:00
 */
@Data
@ApiModel(value = "查询对象", description = "文件查询对象封装")

public class FileQuery {
    /**
     * 文件id
     */
    @ApiModelProperty(value = "文件id")
    private String uid;

    /**
     * 文件名称
     */
    @ApiModelProperty(value = "文件名称")

    private String fileName;

    /**
     * 供应商id
     */
    @ApiModelProperty(value = "供应商id")

    private String supplierId;

    /**
     * 供应商
     */
    @ApiModelProperty(value = "供应商")

    private String supplierName;

    /**
     * 版本号
     */
    @ApiModelProperty(value = "版本号")

    private String tag;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")

    private String remark;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyy-MM-dd")

    @ApiModelProperty(value = "创建时间", example = "2020-02-01 10:10:10")
    private String createTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyy-MM-dd")

    @ApiModelProperty(value = "修改时间", example = "2020-02-01 10:10:10")
    private String updateTime;

    /**
     * 类型
     */
    @ApiModelProperty(value = "类型")

    private String size;

    /**
     * ceph读取key
     */
    @ApiModelProperty(value = "ceph读取key")
    private String fileKey;
}
