package com.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

//第二级目录

@Data
public class TwoFile {

    /**
     * 文件id
     */
    private String handleId;

    /**
     * 文件名称
     */
    private String handleName;

    /**
     * 文件ip
     */
    private String handleIp;
    /**
     * 文件大小
     */
    private String size;
    /**
     * 文件版本号
     */
    private String vid;
    /**
     * 类型
     */
    private String type;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyy-MM-dd")
    private Date createTime;
    /**
     * 更新时间
     */

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyy-MM-dd")
    private Date updateTime;

}