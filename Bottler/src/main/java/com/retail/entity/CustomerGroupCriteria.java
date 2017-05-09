package com.retail.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;




@Entity
@Table(name="CM03")
public class CustomerGroupCriteria
{
  @Id
  @Column(name="CM03Key")
  private String CM03Key;
  @Column(name="identifier")
  private String key;
  @Column(name="value")
  private String values;
  @Column(name="operator")
  private String operator;
  @Column(name="appender")
  private String appender;
  @Column(name="GROUPNAME")
  private String group;
  
  public CustomerGroupCriteria() {}
  
  public String getGroup()
  {
    return group;
  }
  
  public void setGroup(String group) {
    this.group = group;
  }
  
  public String getAppender() {
    return appender;
  }
  
  public void setAppender(String appender) {
    this.appender = appender;
  }
  
  public String getKey() {
    return key;
  }
  
  public void setKey(String key) {
    this.key = key;
  }
  
  public String getValues() {
    return values;
  }
  
  public void setValues(String values) {
    this.values = values;
  }
  
  public String getOperator() {
    return operator;
  }
  
  public void setOperator(String operator) {
    this.operator = operator;
  }
  
  public String getCM03Key() {
    return CM03Key;
  }
  
  public void setCM03Key(String cM03Key) {
    CM03Key = cM03Key;
  }
}
