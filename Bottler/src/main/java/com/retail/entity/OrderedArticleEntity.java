package com.retail.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class OrderedArticleEntity
  implements Serializable
{
  @Column(name="ARTNUM")
  private int articlenumber;
  @Column(name="QTY")
  private int quantity;
  
  public OrderedArticleEntity() {}
  
  public int getArticlenumber()
  {
    return articlenumber;
  }
  
  public void setArticlenumber(int articlenumber) { this.articlenumber = articlenumber; }
  
  public int getQuantity() {
    return quantity;
  }
  
  public void setQuantity(int quantity) { this.quantity = quantity; }
}
