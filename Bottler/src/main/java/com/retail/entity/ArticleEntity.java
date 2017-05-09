package com.retail.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;

@javax.persistence.Entity
@javax.persistence.Table(name="AM01")
public class ArticleEntity
{
  @javax.persistence.Id
  @Column(name="AM01KEY")
  @GeneratedValue(strategy=javax.persistence.GenerationType.IDENTITY)
  private long AM01KEY;
  @Column(name="ARTNUM")
  private int articlenumber;
  @Column(name="ARTNAM")
  private String articlename;
  @Column(name="price")
  private int price;
  
  public ArticleEntity() {}
  
  public long getAM01KEY()
  {
    return AM01KEY;
  }
  
  public void setAM01KEY(long aM01KEY) { AM01KEY = aM01KEY; }
  
  public int getArticlenumber() {
    return articlenumber;
  }
  
  public void setArticlenumber(int articlenumber) { this.articlenumber = articlenumber; }
  
  public String getArticlename() {
    return articlename;
  }
  
  public void setArticlename(String articlename) { this.articlename = articlename; }
  
  public int getPrice() {
    return price;
  }
  
  public void setPrice(int price) { this.price = price; }
}
