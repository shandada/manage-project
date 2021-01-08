package com.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 工具类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequest {
    // 页码
    private Integer pageNo;
    // 每页条数
    private Integer pageSize;
    private String key;

}
