package com.retail.service;

import com.retail.dao.OrderDAO;
import com.retail.entity.ArchiveOrders;
import com.retail.entity.DashboardSalesEntity;
import com.retail.entity.OrderEnt;
import com.retail.entity.PricingTotals;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class OrderService
{
  @Autowired
  private OrderDAO orderDAO;
  
  public OrderService() {}
  
  public void addOrder(OrderEnt order)
  {
    orderDAO.addOrder(order);
  }
  

  public Collection<OrderEnt> displayOrder()
  {
    return orderDAO.displayOrder();
  }
  
  public OrderEnt displayOrderbyID(String id)
  {
    return orderDAO.displayOrderbyID(id);
  }
  
  public PricingTotals calculate(OrderEnt order)
  {
    return orderDAO.calculate(order);
  }
  
  public Iterable<OrderEnt> deleteOrderEntry(OrderEnt tx) {
    return orderDAO.deleteOrderEntry(tx);
  }
  
  public Iterable<DashboardSalesEntity> fetchData(int totalDays) {
    return orderDAO.fetchData(totalDays);
  }
  
  public com.retail.entity.DashBoardEnt fetchDashBoard() {
    return orderDAO.fetchDashBoard();
  }
  
  public Iterable<OrderEnt> displayOrderbyRouteId(@PathVariable String route) {
    return orderDAO.displayOrderbyRouteId(route);
  }
  
  public Collection<ArchiveOrders> displayArchiveOrder() {
    return orderDAO.displayArchiveOrder();
  }
  
  public ArchiveOrders displayAXOrderbyID(String ordernumber) {
    return orderDAO.displayAXOrderbyID(ordernumber);
  }
}
