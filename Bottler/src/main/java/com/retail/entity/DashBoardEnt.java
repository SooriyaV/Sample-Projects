package com.retail.entity;

public class DashBoardEnt
{
  private double salestoday;
  private int orders;
  private int routesout;
  
  public DashBoardEnt() {}
  
  public double getSalestoday() {
    return salestoday;
  }
  
  public void setSalestoday(double salestoday) {
    this.salestoday = salestoday;
  }
  
  public int getOrders() {
    return orders;
  }
  
  public void setOrders(int orders) {
    this.orders = orders;
  }
  
  public int getRoutesout() {
    return routesout;
  }
  
  public void setRoutesout(int routesout) {
    this.routesout = routesout;
  }
}
