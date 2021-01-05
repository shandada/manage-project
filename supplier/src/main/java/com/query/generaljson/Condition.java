package com.query.generaljson;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
//import net.sf.jsqlparser.expression.ValueListExpression;

/**
 * Create on 2020/11/12 11:11
 *
 * @author Yang Shuolin
 */
@Data
@NoArgsConstructor
@ApiModel(value = "通用JSON查询条件对象", description = "详情见《CRUD的API接口设计》文档")
public class Condition {
    @ApiModelProperty(value = "查询字段名")
    private String field;

    @ApiModelProperty(value = "查询操作名称")
    private String action;

    @ApiModelProperty(value = "查询操作的条件，根据action的不同而不同，不一定是字符串")
    private Object param;
}