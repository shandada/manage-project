package com.pojo;


import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "supplier")
public class Supplier {
    @Id
    @Column(name = "gid")
    private String gid;
    private String name;

    //客户名称
    @Column(name = "custom_name")
    private String customName;
    //客户手机号
    @Column(name = "custom_phone")
    private String customPhone;

    //状态，1在状态，0不在
    @Column(name = "custom_phone")
    private String state;

    @Column(name = "create_time")
    private java.sql.Timestamp createTime;
    @Column(name = "update_time")
    private java.sql.Timestamp updateTime;
    private String type;
    @Column(name = "folord_name")
    private String folordName;
    @Column(name = "fid")
    private String fid;



    private Object tags;
    private Object manage;


    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public String getCustomPhone() {
        return customPhone;
    }

    public void setCustomPhone(String customPhone) {
        this.customPhone = customPhone;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public String getFolordName() {
        return folordName;
    }

    public void setFolordName(String folordName) {
        this.folordName = folordName;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public Object getTags() {
        return tags;
    }

    public void setTags(Object tags) {
        this.tags = tags;
    }

    public Object getManage() {
        return manage;
    }

    public void setManage(Object manage) {
        this.manage = manage;
    }
}
