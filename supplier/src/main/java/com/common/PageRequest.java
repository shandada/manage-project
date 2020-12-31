package com.common;

/**
 * 工具类
 */
public class PageRequest {
    // 页码
    private Integer page;
    // 每页条数
    private Integer rows;

    private String key;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "PageRequest{" +
                "page=" + page +
                ", rows=" + rows +
                ", key='" + key + '\'' +
                '}';
    }
}
