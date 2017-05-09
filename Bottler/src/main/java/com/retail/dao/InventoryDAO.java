package com.retail.dao;

import com.retail.entity.InventoryDetails;
import com.retail.entity.InventoryTransaction;
import com.retail.repository.InventoryRepo;
import com.retail.repository.InventoryTransactionRepo;
import java.util.Iterator;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Component
public class InventoryDAO
{
  InventoryRepo im01;
  InventoryTransactionRepo im02;
  
  public InventoryDAO() {}
  
  @Autowired
  public void InventoryRepo(InventoryRepo im01)
  {
    this.im01 = im01;
  }
  
  @Autowired
  public void InventoryTransactionRepo(InventoryTransactionRepo im02) {
    this.im02 = im02;
  }
  
  public Iterable<InventoryDetails> manipulateIM01Entry(InventoryDetails im01C, String flag, Long keyim01)
  {
    if ((flag.equals("CREATE")) || (flag.equals("UPDATE")))
    {
      im01.save(im01C);

    }
    else if (flag.equals("DELETE"))
    {
      im01.delete(im01C);
    }
    
    return im01.findAll();
  }
  



  public Iterable<InventoryTransaction> manipulateIM02Entry(InventoryTransaction im02E, String flag, String invnumber)
  {
    if ((flag.equals("CREATE")) || (flag.equals("UPDATE"))) {
      im02E.setIM02KEY(im02E.getInventoryNumber() + im02E.getItems());
      im02.save(im02E);

    }
    else if (flag.equals("DELETE")) {
      im02E.setIM02KEY(im02E.getInventoryNumber() + im02E.getItems());
      im02.delete(im02E);
    }
    
    return im02.findByinventoryNumber(invnumber);
  }
  
  public InventoryTransaction IM02displayavailable(String invnumber, int item)
  {
    InventoryTransaction im02Item = null;
    Iterator<InventoryTransaction> im02Values = im02.findByinventoryNumber(invnumber).iterator();
    while (im02Values.hasNext()) {
      im02Item = (InventoryTransaction)im02Values.next();
      if (im02Item.getItems() == item) {
        return im02Item;
      }
    }
    

    return null;
  }
}
