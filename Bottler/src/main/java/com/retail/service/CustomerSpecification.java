package com.retail.service;

import com.retail.entity.CustomerCriteriaElements;
import com.retail.entity.CustomerEntity;
import com.retail.utils.helperclass;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;











public class CustomerSpecification
{
  private final List<CustomerCriteriaElements> params;
  
  public CustomerSpecification(List<CustomerCriteriaElements> params)
  {
    this.params = params;
  }
  




  public Specification<CustomerEntity> build()
  {
    if (params.size() == 0) {
      return null;
    }
    

    List<helperclass> specs = new ArrayList();
    for (CustomerCriteriaElements param : params)
    {
      specs.add(new helperclass(new UserSpecification(param), param.getAppender()));
    }
    
    Specification<CustomerEntity> result = ((helperclass)specs.get(0)).getSpecification();
    for (int i = 1; i < specs.size(); i++) {
      if (((helperclass)specs.get(i)).getAppender().equals("OR")) {
        result = Specifications.where(result).or(((helperclass)specs.get(i)).getSpecification());
      }
      else if (((helperclass)specs.get(i)).getAppender().equals("AND")) {
        result = Specifications.where(result).and(((helperclass)specs.get(i)).getSpecification());
      }
    }
    return result;
  }
}
