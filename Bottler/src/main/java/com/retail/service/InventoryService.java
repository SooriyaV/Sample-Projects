package com.retail.service;

import com.retail.dao.InventoryDAO;
import com.retail.entity.InventoryDetails;
import com.retail.entity.InventoryTransaction;
import org.springframework.stereotype.Service;

@Service
public class InventoryService
{
  @org.springframework.beans.factory.annotation.Autowired
  private InventoryDAO inventoryDAO;
  
  public InventoryService() {}
  
  public Iterable<InventoryDetails> manipulateIM01Entry(InventoryDetails im01C, String flag, Long keyim01)
  {
    return inventoryDAO.manipulateIM01Entry(im01C, flag, keyim01);
  }
  
  public Iterable<InventoryTransaction> manipulateIM02Entry(InventoryTransaction im02E, String flag, String invnumber)
  {
    return inventoryDAO.manipulateIM02Entry(im02E, flag, invnumber);
  }
  
  public InventoryTransaction IM02displayavailable(String invnumber, int item)
  {
    return inventoryDAO.IM02displayavailable(invnumber, item);
  }
}
