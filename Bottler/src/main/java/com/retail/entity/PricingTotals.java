/**
 * Author Sooriya
 * Email sooriya.v@outlook.com
 */
package com.retail.entity;

public class PricingTotals {
  private double totamt;
  private double discount;
  private double taxes;
  
  public PricingTotals() {}
  
  public double getTotamt() {
    return totamt;
  }
  
  public void setTotamt(double totamt) { this.totamt = totamt; }
  
  public double getDiscount() {
    return discount;
  }
  
  public void setDiscount(double discount) { this.discount = discount; }
  
  public double getTaxes() {
    return taxes;
  }
  
  public void setTaxes(double taxes) { this.taxes = taxes; }
}
