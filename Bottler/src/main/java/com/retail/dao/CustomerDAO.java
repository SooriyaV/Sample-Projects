package com.retail.dao;

import com.retail.entity.CustomerCriteriaElements;
import com.retail.entity.CustomerEntity;
import com.retail.repository.CustomerRepo;
import com.retail.service.CustomerSpecification;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;


@Component
public class CustomerDAO
{
  @Autowired
  private CustomerRepo customerrepo;
  
  public CustomerDAO() {}
  
  public Iterable<CustomerEntity> manipulateCM01(CustomerEntity cm01, String flag, Long id)
  {
    if ((flag.equals("CREATE")) || (flag.equals("UPDATE")))
    {
      customerrepo.save(cm01);

    }
    else if (flag.equals("DELETE"))
    {
      customerrepo.delete(cm01);
    }
    
    return customerrepo.findAll();
  }
  

  public List<CustomerEntity> criteriaSearch(List<CustomerCriteriaElements> params)
  {
    CustomerSpecification builder = new CustomerSpecification(params);
    Specification<CustomerEntity> specs = builder.build();
    return customerrepo.findAll(specs);
  }
}
