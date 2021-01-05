package com.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Date;


/**
 * @Description:
 * @Auther: logo丶西亚卡姆
 * @Date: 2020/12/29 16:41
 */
//文件实体类
@Table(name = "file_name")
public class FileName {
    /**
     * 自增id
     */
    @Id
    @Column(name = "id")
    private String id;

    /**
     * 文件id
     */
    @Column(name = "handle_id")

    private String handleId;

    /**
     * 文件名称
     */
    @Column(name = "handle_name")

    private String handleName;


    /**
     * 文件大小
     */
    @Column(name = "size")

    private String size;

    /**
     * 文件ip
     */
    @Column(name = "handle_ip")

    private String handleIp;
    /**
     * 文件版本号
     */
    @Column(name = "vid")

    private String vid;
    /**
     * 类型
     */
    @Column(name = "type")

    private String type;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private java.sql.Timestamp createTime;
    //更新时间
    @Column(name = "update_time")
    private java.sql.Timestamp updateTime;


    public FileName() {
    }

    public FileName(String id, String handleId, String handleName, String size, String handleIp, String vid, String type, Timestamp createTime, Timestamp updateTime) {
        this.id = id;
        this.handleId = handleId;
        this.handleName = handleName;
        this.size = size;
        this.handleIp = handleIp;
        this.vid = vid;
        this.type = type;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHandleId() {
        return handleId;
    }

    public void setHandleId(String handleId) {
        this.handleId = handleId;
    }

    public String getHandleName() {
        return handleName;
    }

    public void setHandleName(String handleName) {
        this.handleName = handleName;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getHandleIp() {
        return handleIp;
    }

    public void setHandleIp(String handleIp) {
        this.handleIp = handleIp;
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
}