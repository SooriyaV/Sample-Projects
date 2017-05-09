package com.retail.controller;

import com.retail.entity.InventoryDetails;
import com.retail.entity.InventoryTransaction;
import com.retail.service.InventoryService;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping({"/inventory"})
public class InventoryController
{
  @Autowired
  private InventoryService inventoryservice;
  
  public InventoryController() {}
  
  @RequestMapping(value={"/im01displayall"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
  public Collection<InventoryDetails> IM01displayall()
  {
    return (Collection)inventoryservice.manipulateIM01Entry(null, "", null);
  }
  
  @RequestMapping(value={"/im01/{action}"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, consumes={"application/json"})
  public Iterable<InventoryDetails> IM01create(@RequestBody InventoryDetails im01, @PathVariable("action") String flag) {
    return inventoryservice.manipulateIM01Entry(im01, flag, null);
  }
  
  @RequestMapping(value={"/im02displayall/{invnumber}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
  public Collection<InventoryTransaction> IM02displayall(@PathVariable("invnumber") String invnumber)
  {
    return (Collection)inventoryservice.manipulateIM02Entry(null, "", invnumber);
  }
  
  @RequestMapping(value={"/im02/{action}"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, consumes={"application/json"})
  public Iterable<InventoryTransaction> IM02create(@RequestBody InventoryTransaction im02E, @PathVariable("action") String flag) {
    return inventoryservice.manipulateIM02Entry(im02E, flag, null);
  }
  
  @RequestMapping(value={"/im02displayavailable/{invnumber}/{item}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
  public InventoryTransaction IM02displayavailable(@PathVariable("invnumber") String invnumber, @PathVariable("item") int item)
  {
    return inventoryservice.IM02displayavailable(invnumber, item);
  }
}
