/**
 * Author Sooriya
 * Email sooriya.v@outlook.com
 */
package com.retail.entity;

import javax.persistence.Column;

@javax.persistence.Entity
@javax.persistence.Table(name="AX02")
public class ArchiveOrderedArticle
{
  @javax.persistence.Id
  @Column(name="AX02KEY")
  private String AX02KEY;
  @Column(name="DOCNUM")
  private String ordernumber;
  
  public void setAX02KEY(String AX02KEY)
  {
    this.AX02KEY = AX02KEY;
  }
  

  @Column(name="ARTNUM")
  private int articlenumber;
  
  @Column(name="QTY")
  private int quantity;
  
  @Column(name="AMT1")
  private double amount;
  
  public double getAmount()
  {
    return amount;
  }
  
  public void setAmount(double amount) {
    this.amount = amount;
  }
  

  public ArchiveOrderedArticle() {}
  
  public ArchiveOrderedArticle(int nu, int quantity, String ordernumber)
  {
    articlenumber = nu;
    this.quantity = quantity;
    this.ordernumber = ordernumber;
    AX02KEY = (ordernumber + nu);
  }
  
  public String getOrdernumber() { return ordernumber; }
  
  public void setOrdernumber(String ordernumber)
  {
    this.ordernumber = ordernumber;
  }
  
  public int getArticlenumber() { return articlenumber; }
  
  public void setArticlenumber(int articlenumber) {
    this.articlenumber = articlenumber;
  }
  
  public int getQuantity() { return quantity; }
  
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
}
