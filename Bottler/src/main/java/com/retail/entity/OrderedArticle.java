/**
 * Author Sooriya
 * Email sooriya.v@outlook.com
 */
package com.retail.entity;

import javax.persistence.Column;

@javax.persistence.Entity
@javax.persistence.Table(name="TX02")
public class OrderedArticle implements java.io.Serializable
{
  @javax.persistence.Id
  @Column(name="TX02KEY")
  private String TX02KEY;
  @Column(name="DOCNUM")
  private String ordernumber;
  @Column(name="ARTNUM")
  private int articlenumber;
  @Column(name="QTY")
  private int quantity;
  @Column(name="AMT1")
  private double amount;
  
  public void setTX02KEY(String tX02KEY)
  {
    TX02KEY = tX02KEY;
  }
  










  public double getAmount()
  {
    return amount;
  }
  
  public void setAmount(double amount) {
    this.amount = amount;
  }
  
  public OrderedArticle() {}
  
  public OrderedArticle(int nu, int quantity, String ordernumber) {
    articlenumber = nu;
    this.quantity = quantity;
    this.ordernumber = ordernumber;
    TX02KEY = (ordernumber + nu);
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
