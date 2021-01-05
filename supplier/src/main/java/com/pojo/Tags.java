package com.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tags")
public class Tags {
  @Id
  @Column(name = "tid")
  private String tid;
  @Column(name = "tname")
  private String tname;
}
