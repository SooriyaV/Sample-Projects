package com.retail.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="CM02")
public class CustomerGroupEntity
{
  @Id
  @Column(name="CM02KEY")
  private String CM02key;
  @Column(name="GROUPNAME")
  private String group;
  @Column(name="DESCRIPTION")
  private String description;
  
  public CustomerGroupEntity() {}
  
  public String getDescription()
  {
    return description;
  }
  
  public void setDescription(String description) {
    this.description = description;
  }
  
  public String getCM02key() {
    return CM02key;
  }
  
  public void setCM02key(String cM02key) {
    CM02key = cM02key;
  }
  
  public String getGroup() {
    return group;
  }
  
  public void setGroup(String group) {
    this.group = group;
  }
}
