package com.retail.controller;

import com.retail.entity.CustomerGroupCriteria;
import com.retail.entity.CustomerGroupEntity;
import com.retail.service.CustomerGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




@RestController
@RequestMapping({"/customergroup"})
public class CustomerGroupController
{
  @Autowired
  private CustomerGroupService customergroupservice;
  
  public CustomerGroupController() {}
  
  @RequestMapping(value={"/cm02displayall"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
  public Iterable<CustomerGroupEntity> CM02displayall()
  {
    return customergroupservice.manipulateCM02(null, "", null);
  }
  
  @RequestMapping(value={"/cm02/{action}"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, consumes={"application/json"})
  public Iterable<CustomerGroupEntity> CM02create(@RequestBody CustomerGroupEntity cm02, @PathVariable("action") String flag) {
    return customergroupservice.manipulateCM02(cm02, flag, null);
  }
  

  @RequestMapping(value={"/cm03/{groupName}/{action}"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, consumes={"application/json"})
  public Iterable<CustomerGroupCriteria> manipulateCM03(@RequestBody Iterable<CustomerGroupCriteria> cm03, @PathVariable("groupName") String groupname, @PathVariable("action") String action)
  {
    return customergroupservice.manipulateCM03(cm03, groupname, action);
  }
  

  @RequestMapping(value={"/cm03displayall/{groupName}/{action}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
  public Iterable<CustomerGroupCriteria> CM03displayall(@PathVariable("groupName") String groupname, @PathVariable("action") String action)
  {
    return customergroupservice.manipulateCM03(null, groupname, action);
  }
}
