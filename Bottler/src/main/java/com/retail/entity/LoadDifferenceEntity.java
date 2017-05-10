/**
 * Author Sooriya
 * Email sooriya.v@outlook.com
 */
package com.retail.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;






@Entity
@Table(name="LD01")
public class LoadDifferenceEntity
{
  @Id
  @Column(name="KEYLD01")
  private String keyLD01;
  @Column(name="DOCNUM")
  private String orderNumber;
  @Column(name="STATUS")
  private String status;
  @Column(name="ITEM")
  private int item;
  @Column(name="OUTLOAD")
  private int outloadUnits;
  @Column(name="INLOAD")
  private int inloadUnits;
  @Column(name="OLDIFF")
  private int outloadDiff;
  @Column(name="INLDIFF")
  private int inloadDiff;
  @Column(name="ACTUNITS")
  private int actualUnits;
  
  public LoadDifferenceEntity() {}
  
  public int getActualUnits()
  {
    return actualUnits;
  }
  
  public void setActualUnits(int actualUnits) {
    this.actualUnits = actualUnits;
  }
  
  public String getKeyLD01() {
    return keyLD01;
  }
  
  public void setKeyLD01(String keyLD01) {
    this.keyLD01 = keyLD01;
  }
  
  public String getOrderNumber() {
    return orderNumber;
  }
  
  public void setOrderNumber(String orderNumber) {
    this.orderNumber = orderNumber;
  }
  
  public String getStatus() {
    return status;
  }
  
  public void setStatus(String status) {
    this.status = status;
  }
  
  public int getItem() {
    return item;
  }
  
  public void setItem(int item) {
    this.item = item;
  }
  
  public int getOutloadUnits() {
    return outloadUnits;
  }
  
  public void setOutloadUnits(int outloadUnits) {
    this.outloadUnits = outloadUnits;
  }
  
  public int getInloadUnits() {
    return inloadUnits;
  }
  
  public void setInloadUnits(int inloadUnits) {
    this.inloadUnits = inloadUnits;
  }
  
  public int getOutloadDiff() {
    return outloadDiff;
  }
  
  public void setOutloadDiff(int outloadDiff) {
    this.outloadDiff = outloadDiff;
  }
  
  public int getInloadDiff() {
    return inloadDiff;
  }
  
  public void setInloadDiff(int inloadDiff) {
    this.inloadDiff = inloadDiff;
  }
}
