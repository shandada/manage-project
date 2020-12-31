package com.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
@Table(name = "manage")
public class Manage {

  @Id
  @Column(name = "id")
  private Integer id;
  @Column(name = "handle_id")
  private String handleId;
  @Column(name = "folord_name")
  private String folordName;
  @Column(name = "size")
  private double size;
  @Column(name = "gid")
  private Integer gid;
  @Column(name = "create_time")
  private java.sql.Timestamp createTime;
  @Column(name = "update_time")
  private java.sql.Timestamp updateTime;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getHandleId() {
    return handleId;
  }

  public void setHandleId(String handleId) {
    this.handleId = handleId;
  }


  public String getFolordName() {
    return folordName;
  }

  public void setFolordName(String folordName) {
    this.folordName = folordName;
  }

  public double getSize() {
    return size;
  }

  public void setSize(double size) {
    this.size = size;
  }

  public Integer getGid() {
    return gid;
  }

  public void setGid(Integer gid) {
    this.gid = gid;
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
