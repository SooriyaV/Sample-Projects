package com.retail.service;

import com.retail.dao.RouteDAO;
import com.retail.entity.OrderEnt;
import com.retail.entity.RouteEntity;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteService
{
  @Autowired
  private RouteDAO routeDAO;
  
  public RouteService() {}
  
  public Iterable<RouteEntity> manipulateRT01(RouteEntity rt01, String flag, Long id)
  {
    return routeDAO.manipulateRT01(rt01, flag, id);
  }
  
  public Iterable<OrderEnt> manipulateOrders(String routeName, String status) {
    return routeDAO.manipulateOrders(routeName, status);
  }
  
  public List<String> fetchOrdersinRoute(String routeName) {
    return routeDAO.fetchOrdersinRoute(routeName);
  }
  
  public Iterable<RouteEntity> getRoutebystatus(String status) {
    return routeDAO.getRoutebystatus(status);
  }
}
