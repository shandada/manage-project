package com.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

//第一级目录
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OneChapter {

    //id

    private String supplierId;
    //供应商
    private String supplierName;

    private List<TwoFile> children = new ArrayList<>();
}