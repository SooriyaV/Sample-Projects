package com.retail.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@javax.persistence.Table(name="IM02")
public class InventoryTransaction
{
  @Id
  @Column(name="IM02KEY")
  private String IM02KEY;
  @Column(name="invnum")
  private String inventoryNumber;
  @Column(name="Item1")
  private int Items;
  @Column(name="InStock")
  private int InStockQty;
  @Column(name="QtyinTran")
  private int QuantityinTransit;
  @Column(name="Status")
  private String TransitStatus;
  
  public InventoryTransaction() {}
  
  public String getIM02KEY()
  {
    return IM02KEY;
  }
  
  public void setIM02KEY(String iM02KEY) { IM02KEY = iM02KEY; }
  
  public int getItems() {
    return Items;
  }
  
  public void setItems(int items) { Items = items; }
  
  public int getInStockQty() {
    return InStockQty;
  }
  
  public void setInStockQty(int inStockQty) { InStockQty = inStockQty; }
  
  public int getQuantityinTransit() {
    return QuantityinTransit;
  }
  
  public void setQuantityinTransit(int quantityinTransit) { QuantityinTransit = quantityinTransit; }
  
  public String getTransitStatus() {
    return TransitStatus;
  }
  
  public void setTransitStatus(String transitStatus) { TransitStatus = transitStatus; }
  
  public String getInventoryNumber() {
    return inventoryNumber;
  }
  
  public void setInventoryNumber(String inventoryNumber) { this.inventoryNumber = inventoryNumber; }
}
