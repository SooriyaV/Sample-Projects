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
@Table(name="AX01")
public class ArchiveOrders
{
  @Id
  @Column(name="AX01KEY")
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private long AX01KEY;
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
  private List<ArchiveOrderedArticle> orderedarticle;
  @Column(name="RTE")
  private String routeName;
  @Column(name="status")
  private String status;
  private double discount;
  
  public ArchiveOrders() {}
  
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
  
  public List<ArchiveOrderedArticle> getOrderedarticle() {
    return orderedarticle;
  }
  
  public void setOrderedarticle(List<ArchiveOrderedArticle> orderedarticle) {
    this.orderedarticle = orderedarticle;
  }
  


  public long getAX01KEY()
  {
    return AX01KEY;
  }
  
  public void setAX01KEY(long AX01KEY) {
    this.AX01KEY = AX01KEY;
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
