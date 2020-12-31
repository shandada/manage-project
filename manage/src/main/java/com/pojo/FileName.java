package com.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import java.beans.Transient;
import java.util.Date;


/**
 * @Description:
 * @Auther: logo丶西亚卡姆
 * @Date: 2020/12/29 16:41
 */
//文件实体类
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileName {
    /**
     * 自增id
     */
    @Id
    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 文件id
     */
    private Integer handleId;

    /**
     * 文件名称
     */
    private String handleName;

    /**
     * 文件ip
     */
    private String handleIp;
    /**
     * 文件版本号
     */
    private Integer vid;
    /**
     * 创建时间
     */
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

    //版本号对象
    @TableField(exist = false)
    private Versions versions;

    //接收 版本号 id字符串
    @TableField(exist = false)
    //["1.1.1","1.1.2","1.1.3"];
        private String versionsIDs;
}