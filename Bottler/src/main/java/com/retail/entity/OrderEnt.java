/**
 * Author Sooriya
 * Email sooriya.v@outlook.com
 */
package com.retail.entity;

import java.sql.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


















@Entity
@Table(name="TX01")
public class OrderEnt
{
  @Id
  @Column(name="TX01KEY")
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private long TX01KEY;
  @Column(name="DOCNUM")
  private String ordernumber;
  @Column(name="INVNUM")
  private String invnumber;
  @Column(name="OUTLOC")
  private String customernumber;
  @Column(name="ORDDAT")
  private Date ordereddate;
  @Column(name="QTY")
  private double totalquantity;
  @Column(name="AMT")
  private double totalamount;
  @Transient
  private List<OrderedArticle> orderedarticle;
  @Column(name="RTE")
  private String routeName;
  @Column(name="status")
  private String status;
  private double discount;
  
  public OrderEnt() {}
  
  public String getStatus()
  {
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
  
  public List<OrderedArticle> getOrderedarticle() {
    return orderedarticle;
  }
  
  public void setOrderedarticle(List<OrderedArticle> orderedarticle) {
    this.orderedarticle = orderedarticle;
  }
  


  public long getTX01KEY()
  {
    return TX01KEY;
  }
  
  public void setTX01KEY(long tX01KEY) {
    TX01KEY = tX01KEY;
  }
  
  public String getInvnumber() {
    return invnumber;
  }
  
  public void setInvnumber(String invnumber) {
    this.invnumber = invnumber;
  }
  
  public String getCustomernumber() {
    return customernumber;
  }
  
  public void setCustomernumber(String customernumber) {
    this.customernumber = customernumber;
  }
  
  public Date getOrdereddate() {
    return ordereddate;
  }
  
  public void setOrdereddate(Date ordereddate) {
    this.ordereddate = ordereddate;
  }
  
  public String getOrdernumber() {
    return ordernumber;
  }
  






  public double getTotalquantity()
  {
    return totalquantity;
  }
  
  public void setTotalquantity(double totalquantity) { this.totalquantity = totalquantity; }
  
  public double getTotalamount()
  {
    return totalamount;
  }
  
  public void setTotalamount(double totalamount) { this.totalamount = totalamount; }
  
  public double getDiscount()
  {
    return discount;
  }
  
  public void setDiscount(double discount) { this.discount = discount; }
  
  public void setOrdernumber(String ordernumber)
  {
    this.ordernumber = ordernumber;
  }
}
