package com.pojo;


import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "supplier")
public class Supplier {
    @Id
    @Column(name = "gid")
    private Integer gid;
    private String name;
    @Column(name = "create_time")
    private java.sql.Timestamp createTime;
    @Column(name = "update_time")
    private java.sql.Timestamp updateTime;
    private String type;
    @Column(name = "folord_name")
    private String folordName;
    @Column(name = "fid")
    private Integer fid;
    @Column(name = "tid")
    private Integer tid;

    private Object tags;
    private Object manage;

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public Object getManage() {
        return manage;
    }

    public void setManage(Object manage) {
        this.manage = manage;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public Object getTags() {
        return tags;
    }

    public void setTags(Object tags) {
        this.tags = tags;
    }

    public String getFolordName() {
        return folordName;
    }

    public void setFolordName(String folordName) {
        this.folordName = folordName;
    }
}
