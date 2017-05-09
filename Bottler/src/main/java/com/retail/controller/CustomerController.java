package com.retail.controller;

import com.retail.entity.CustomerEntity;
import com.retail.entity.CustomerGroupCriteria;
import com.retail.service.CustomerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;






@RestController
@RequestMapping({"/customer"})
public class CustomerController
{
  @Autowired
  private CustomerService customerService;
  
  public CustomerController() {}
  
  @RequestMapping(value={"/cm01displayall"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
  public Iterable<CustomerEntity> IM01displayall()
  {
    return customerService.manipulateCM01(null, "", null);
  }
  
  @RequestMapping(value={"/cm01/{action}"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, consumes={"application/json"})
  public Iterable<CustomerEntity> IM01create(@RequestBody CustomerEntity cm01, @PathVariable("action") String flag) {
    return customerService.manipulateCM01(cm01, flag, null);
  }
  
  @RequestMapping(value={"/criteria"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, consumes={"application/json"})
  public List<CustomerEntity> criteriaSearch(@RequestBody Iterable<CustomerGroupCriteria> criteria) { return customerService.criteriaSearch(criteria); }
}
