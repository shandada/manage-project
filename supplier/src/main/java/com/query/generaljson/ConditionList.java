package com.query.generaljson;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Create on 2020/11/12 15:41
 *
 * @author Yang Shuolin
 */
@Data
@NoArgsConstructor
@ApiModel(value = "条件对象数组", description = "详情见《CRUD的API接口设计》文档")
public class ConditionList {
    private List<Condition> condition;
}
