package com.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;


/**
 * @Description:
 * @Auther: logo丶西亚卡姆
 * @Date: 2020/12/29 17:49
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
//版本号实体类
public class Versions {

    /**
     * 文件id
     */
    @Id
    @GeneratedValue(generator = "JDBC")
//    @TableId(value = "vid", type = IdType.AUTO)
    private Integer vid;

    /**
     * 版本号
     */
    private String vName;


}