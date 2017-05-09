package com.retail.entity;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;





@Entity
@Table(name="RT01")
public class RouteEntity
{
  @Id
  @Column(name="RTE")
  private String routeName;
  @Column(name="Driver")
  private String driver;
  @Column(name="Vehicle")
  private String vehicle;
  @Column(name="Status")
  private String status;
  @Column(name="BatchDate")
  private Date batchDate;
  
  public RouteEntity() {}
  
  public Date getBatchDate()
  {
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
  
  public String getRouteName() {
    return routeName;
  }
  
  public void setRouteName(String routeName) {
    this.routeName = routeName;
  }
  
  public String getDriver() {
    return driver;
  }
  
  public void setDriver(String driver) {
    this.driver = driver;
  }
  
  public String getVehicle() {
    return vehicle;
  }
  
  public void setVehicle(String vehicle) {
    this.vehicle = vehicle;
  }
}
