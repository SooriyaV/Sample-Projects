package com.retail.utils;

import com.retail.service.UserSpecification;
import org.springframework.data.jpa.domain.Specification;

public class helperclass
{
  private UserSpecification specification;
  private String appender;
  
  public helperclass(UserSpecification userSpecification, String appender2)
  {
    specification = userSpecification;
    appender = appender2;
  }
  
  public Specification<com.retail.entity.CustomerEntity> getSpecification() { return specification; }
  
  public void setSpecification(UserSpecification specification) {
    this.specification = specification;
  }
  
  public String getAppender() { return appender; }
  
  public void setAppender(String appender) {
    this.appender = appender;
  }
}
