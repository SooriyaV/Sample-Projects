package com.retail.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CM04")
public class CustomerGroupCM04Ent
{
  @Id
  @Column(name="CM04Key")
  private String cm04Key;
  @Column(name="CustLoc")
  private long customerLocation;
  @Column(name="CustName")
  private String customerName;
  @Column(name="groupName")
  private String group;
  
  public CustomerGroupCM04Ent() {}
  
  public String getCm04Key()
  {
    return cm04Key;
  }
  
  public void setCm04Key(String cm04Key) {
    this.cm04Key = cm04Key;
  }
  
  public long getCustomerLocation() {
    return customerLocation;
  }
  
  public void setCustomerLocation(long customerLocation) {
    this.customerLocation = customerLocation;
  }
  
  public String getCustomerName() {
    return customerName;
  }
  
  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }
  
  public String getGroup() {
    return group;
  }
  
  public void setGroup(String group) {
    this.group = group;
  }
}
