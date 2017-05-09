package com.retail.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;









@Entity
@Table(name="CM01")
public class CustomerEntity
{
  @Id
  @Column(name="CM01KEY")
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private long CM01key;
  @Column(name="CUSTLOC")
  private long custloc;
  @Column(name="CUSTNAM")
  private String custname;
  @Column(name="ADDRESS")
  private String address;
  @Column(name="EMLADR")
  private String email;
  @Column(name="PHN1")
  private long phone1;
  @Column(name="PHN2")
  private long phone2;
  @Column(name="GPS")
  private String gps;
  @Column(name="CRDLMT")
  private double creditlimit;
  @Column(name="PAYMODE")
  private String paymentmode;
  
  public CustomerEntity() {}
  
  public long getCM01key()
  {
    return CM01key;
  }
  
  public void setCM01key(long cM01key) {
    CM01key = cM01key;
  }
  
  public long getCustloc() {
    return custloc;
  }
  
  public void setCustloc(long custloc) {
    this.custloc = custloc;
  }
  
  public String getCustname() {
    return custname;
  }
  
  public void setCustname(String custname) {
    this.custname = custname;
  }
  
  public String getAddress() {
    return address;
  }
  
  public void setAddress(String address) {
    this.address = address;
  }
  
  public String getEmail() {
    return email;
  }
  
  public void setEmail(String email) {
    this.email = email;
  }
  
  public long getPhone1() {
    return phone1;
  }
  
  public void setPhone1(long phone1) {
    this.phone1 = phone1;
  }
  
  public long getPhone2() {
    return phone2;
  }
  
  public void setPhone2(long phone2) {
    this.phone2 = phone2;
  }
  
  public String getGps() {
    return gps;
  }
  
  public void setGps(String gps) {
    this.gps = gps;
  }
  
  public double getCreditlimit() {
    return creditlimit;
  }
  
  public void setCreditlimit(double creditlimit) {
    this.creditlimit = creditlimit;
  }
  
  public String getPaymentmode() {
    return paymentmode;
  }
  
  public void setPaymentmode(String paymentmode) {
    this.paymentmode = paymentmode;
  }
}
