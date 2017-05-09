package com.retail.dao;

import com.retail.entity.CustomerEntity;
import com.retail.entity.CustomerGroupCM04Ent;
import com.retail.entity.CustomerGroupCriteria;
import com.retail.entity.CustomerGroupEntity;
import com.retail.repository.CustomerGroupCM04Repo;
import com.retail.repository.CustomerGroupCriteriaRepo;
import com.retail.repository.CustomerGroupRepo;
import java.util.Iterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;




@Component
public class CustomerGroupDAO
{
  @Autowired
  CustomerGroupRepo customerGroupRepo;
  @Autowired
  private CustomerGroupCriteriaRepo criteriaRepo;
  @Autowired
  private CustomerGroupCM04Repo cm04repo;
  
  public CustomerGroupDAO() {}
  
  public Iterable<CustomerGroupEntity> manipulateCM02(CustomerGroupEntity cm02, String flag, Long id)
  {
    if ((flag.equals("CREATE")) || (flag.equals("UPDATE"))) {
      cm02.setCM02key(cm02.getGroup());
      customerGroupRepo.save(cm02);

    }
    else if (flag.equals("DELETE")) {
      cm02.setCM02key(cm02.getGroup());
      customerGroupRepo.delete(cm02);
    }
    
    return customerGroupRepo.findAll();
  }
  
  public Iterable<CustomerGroupCriteria> manipulateCM03(Iterable<CustomerGroupCriteria> cm03, Iterable<CustomerEntity> cm04, String groupname, String action)
  {
    if (action.equals("SAVE"))
    {
      criteriaRepo.delete(criteriaRepo.findBygroup(groupname));
      Iterator<CustomerGroupCriteria> itr = cm03.iterator();
      int count = 0;
      while (itr.hasNext()) {
        CustomerGroupCriteria obj = new CustomerGroupCriteria();
        
        obj = (CustomerGroupCriteria)itr.next();
        obj.setCM03Key(groupname + count++);
        obj.setGroup(groupname);
        criteriaRepo.save(obj);
      }
      count = 0;
      
      cm04repo.delete(cm04repo.findBygroup(groupname));
      Iterator<CustomerEntity> itrcm04 = cm04.iterator();
      while (itrcm04.hasNext()) {
        CustomerEntity cm01 = (CustomerEntity)itrcm04.next();
        CustomerGroupCM04Ent obj2 = new CustomerGroupCM04Ent();
        obj2.setCm04Key(groupname + cm01.getCustloc());
        obj2.setCustomerLocation(cm01.getCustloc());
        obj2.setCustomerName(cm01.getCustname());
        obj2.setGroup(groupname);
        cm04repo.save(obj2);
      }
    }
    



    return criteriaRepo.findBygroup(groupname);
  }
}
