/**
 * Author Sooriya
 * Email sooriya.v@outlook.com
 */
package com.retail.entity;

import java.util.List;

public class CustomerCriteriaElements
{
  private String key;
  private List<String> values;
  private String operator;
  private String appender;
  
  public CustomerCriteriaElements() {}
  
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
  
  public List<String> getValues() {
    return values;
  }
  
  public void setValues(List<String> values) {
    this.values = values;
  }
  
  public String getOperator() {
    return operator;
  }
  
  public void setOperator(String operator) {
    this.operator = operator;
  }
}
