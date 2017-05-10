/**
 * Author Sooriya
 * Email sooriya.v@outlook.com
 */
package com.retail.entity;

public class DashboardSalesEntity {
  private String orddate;
  private double totalSales;
  
  public DashboardSalesEntity() {}
  
  public String getOrddate() {
    return orddate;
  }
  
  public void setOrddate(String orddate) {
    this.orddate = orddate;
  }
  
  public double getTotalSales() {
    return totalSales;
  }
  
  public void setTotalSales(double totalSales) {
    this.totalSales = totalSales;
  }
}
