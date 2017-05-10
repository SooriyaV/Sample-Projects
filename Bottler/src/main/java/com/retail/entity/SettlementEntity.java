/**
 * Author Sooriya
 * Email sooriya.v@outlook.com
 */
package com.retail.entity;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;




@Entity
@Table(name="SM01")
public class SettlementEntity
{
  @Id
  @Column(name="KEYSM01")
  private String keySM01;
  @Column(name="TotalRoutes")
  private int routes;
  @Column(name="TotalOrders")
  private int orders;
  @Column(name="BatchDate")
  private Date batchDate;
  @Column(name="status")
  private String status;
  
  public SettlementEntity() {}
  
  public String getKeySM01()
  {
    return keySM01;
  }
  
  public void setKeySM01(String keySM01) {
    this.keySM01 = keySM01;
  }
  
  public int getRoutes() {
    return routes;
  }
  
  public void setRoutes(int routes) {
    this.routes = routes;
  }
  
  public int getOrders() {
    return orders;
  }
  
  public void setOrders(int orders) {
    this.orders = orders;
  }
  
  public Date getBatchDate() {
    return batchDate;
  }
  
  public void setBatchDate(Date batchDate) {
    this.batchDate = batchDate;
  }
  
  public String getStatus() {
    return status;
  }
  
  public void setStatus(String status) {
    this.status = status;
  }
}
