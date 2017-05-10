/**
 * Author Sooriya
 * Email sooriya.v@outlook.com
 */
package com.retail.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="IM01")
public class InventoryDetails
{
  @Id
  @Column(name="IM01KEY")
  @GeneratedValue(strategy=javax.persistence.GenerationType.IDENTITY)
  private long IM01KEY;
  @Column(name="INVNUM")
  private long inventoryNumber;
  @Column(name="INVNAM")
  private String inventoryName;
  
  public InventoryDetails() {}
  
  public long getIM01KEY()
  {
    return IM01KEY;
  }
  
  public void setIM01KEY(long iM01KEY) { IM01KEY = iM01KEY; }
  
  public long getInventoryNumber() {
    return inventoryNumber;
  }
  
  public void setInventoryNumber(long inventoryNumber) { this.inventoryNumber = inventoryNumber; }
  
  public String getInventoryName() {
    return inventoryName;
  }
  
  public void setInventoryName(String inventoryName) { this.inventoryName = inventoryName; }
}
