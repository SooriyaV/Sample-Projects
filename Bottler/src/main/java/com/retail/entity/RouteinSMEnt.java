/**
 * Author Sooriya
 * Email sooriya.v@outlook.com
 */
package com.retail.entity;

public class RouteinSMEnt {
  private String route;
  private int orders;
  private String status;
  
  public RouteinSMEnt() {}
  
  public String getRoute() { return route; }
  
  public void setRoute(String route) {
    this.route = route;
  }
  
  public int getOrders() { return orders; }
  
  public void setOrders(int orders) {
    this.orders = orders;
  }
  
  public String getStatus() { return status; }
  
  public void setStatus(String status) {
    this.status = status;
  }
}
