package com.retail.service;

import com.retail.dao.CustomerGroupDAO;
import com.retail.entity.CustomerCriteriaElements;
import com.retail.entity.CustomerEntity;
import com.retail.entity.CustomerGroupCriteria;
import com.retail.entity.CustomerGroupEntity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerGroupService
{
  @Autowired
  private CustomerGroupDAO customerGroupDao;
  @Autowired
  private CustomerService customerService;
  
  public CustomerGroupService() {}
  
  public Iterable<CustomerGroupEntity> manipulateCM02(CustomerGroupEntity cm02, String flag, Long id)
  {
    return customerGroupDao.manipulateCM02(cm02, flag, id);
  }
  
  public Iterable<CustomerGroupCriteria> manipulateCM03(Iterable<CustomerGroupCriteria> cm03, String groupname, String action) {
    if (cm03 != null) {
      List<CustomerCriteriaElements> params = new ArrayList();
      
      List<CustomerGroupCriteria> finalcm03 = new ArrayList();
      Iterator<CustomerGroupCriteria> criteriaElements = cm03.iterator();
      
      while (criteriaElements.hasNext())
      {

        String actualValue = "";
        CustomerGroupCriteria fromJson = (CustomerGroupCriteria)criteriaElements.next();
        CustomerGroupCriteria criterion = new CustomerGroupCriteria();
        criterion.setKey(fromJson.getKey());
        criterion.setOperator("equals");
        actualValue = fromJson.getValues();
        criterion.setValues(actualValue);
        criterion.setAppender(fromJson.getAppender());
        criterion.setGroup(groupname);
        finalcm03.add(criterion);
      }
      
      Iterable<CustomerEntity> cm04 = customerService.criteriaSearch(cm03);
      return customerGroupDao.manipulateCM03(finalcm03, cm04, groupname, action);
    }
    
    return customerGroupDao.manipulateCM03(null, null, groupname, action);
  }
}
