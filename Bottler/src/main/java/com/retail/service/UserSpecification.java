package com.retail.service;

import com.retail.entity.CustomerCriteriaElements;
import com.retail.entity.CustomerEntity;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification implements Specification<CustomerEntity>
{
  private CustomerCriteriaElements criteria;
  private String appender;
  
  public String getAppender()
  {
    return appender;
  }
  
  public void setAppender(String appender) {
    this.appender = appender;
  }
  
  public UserSpecification(CustomerCriteriaElements param) {
    appender = param.getAppender();
    criteria = param;
  }
  

  public Predicate toPredicate(Root<CustomerEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder)
  {
    if (criteria.getOperator().equalsIgnoreCase("equals")) {
      return root.get(criteria.getKey()).in(criteria.getValues());
    }
    
    return builder.equal(root.get(criteria.getKey()), criteria.getValues());
  }
}
