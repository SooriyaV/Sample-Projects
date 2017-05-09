package com.retail.service;

import com.retail.dao.CustomerDAO;
import com.retail.entity.CustomerCriteriaElements;
import com.retail.entity.CustomerEntity;
import com.retail.entity.CustomerGroupCriteria;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class CustomerService
{
  @Autowired
  private CustomerDAO customerDAO;
  
  public CustomerService() {}
  
  public Iterable<CustomerEntity> manipulateCM01(CustomerEntity cm01, String flag, Long id)
  {
    return customerDAO.manipulateCM01(cm01, flag, id);
  }
  


  public List<CustomerEntity> criteriaSearch(Iterable<CustomerGroupCriteria> criteria)
  {
    List<CustomerCriteriaElements> params = new ArrayList();
    
    Iterator<CustomerGroupCriteria> criteriaElements = criteria.iterator();
    
    while (criteriaElements.hasNext()) {
      List<String> valueList = new ArrayList();
      String[] valuearray = null;
      String actualValue = "";
      CustomerGroupCriteria fromJson = (CustomerGroupCriteria)criteriaElements.next();
      
      CustomerCriteriaElements criterion = new CustomerCriteriaElements();
      criterion.setKey(fromJson.getKey());
      criterion.setOperator("equals");
      actualValue = fromJson.getValues();
      valuearray = actualValue.split(";");
      System.out.println("the first value is  " + valuearray[0]);
      for (int i = 0; i < valuearray.length; i++) {
        valueList.add(valuearray[i]);
      }
      criterion.setValues(valueList);
      criterion.setAppender(fromJson.getAppender());
      params.add(criterion);
    }
    





















    return customerDAO.criteriaSearch(params);
  }
}
