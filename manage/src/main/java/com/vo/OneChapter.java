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

    private String id;

    private String title;



    private List<TwoFile> children = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        this.setTitle("目录: ");
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<TwoFile> getChildren() {
        return children;
    }

    public void setChildren(List<TwoFile> children) {
        this.children = children;
    }
}