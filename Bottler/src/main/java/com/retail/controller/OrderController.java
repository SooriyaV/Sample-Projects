package com.retail.controller;

import com.retail.entity.ArchiveOrders;
import com.retail.entity.DashBoardEnt;
import com.retail.entity.DashboardSalesEntity;
import com.retail.entity.OrderEnt;
import com.retail.entity.PricingTotals;
import com.retail.service.OrderService;
import java.io.PrintStream;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;






@RestController
@RequestMapping({"/"})
public class OrderController
{
  @Autowired
  private OrderService orderService;
  
  public OrderController() {}
  
  @RequestMapping(value={"/fetch"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
  public Collection<OrderEnt> displayOrder()
  {
    return orderService.displayOrder();
  }
  



  @RequestMapping(value={"/fetch"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, consumes={"application/json"})
  public void addOrder(@RequestBody OrderEnt order) { orderService.addOrder(order); }
  
  @RequestMapping(value={"/fetchbyId/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
  @ResponseBody
  public OrderEnt displayOrderbyID(@PathVariable String id) {
    System.out.println("the ctrl is" + orderService.displayOrderbyID(id).getOrdernumber());
    return orderService.displayOrderbyID(id);
  }
  
  @RequestMapping(value={"/order/delete"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, consumes={"application/json"})
  public Iterable<OrderEnt> deleteOrderEntry(@RequestBody OrderEnt tx) {
    return orderService.deleteOrderEntry(tx);
  }
  
  @RequestMapping(value={"/calculateorder"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, consumes={"application/json"})
  public PricingTotals calculate(@RequestBody OrderEnt order) {
    return orderService.calculate(order);
  }
  
  @RequestMapping(value={"/salesBar/{totalDays}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
  @ResponseBody
  public Iterable<DashboardSalesEntity> fetchData(@PathVariable int totalDays) {
    return orderService.fetchData(totalDays);
  }
  
  @RequestMapping(value={"/DashBoardCall"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
  @ResponseBody
  public DashBoardEnt fetchDashBoard()
  {
    return orderService.fetchDashBoard();
  }
  
  @RequestMapping(value={"/ordersinroute/{route}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
  @ResponseBody
  public Iterable<OrderEnt> displayOrderbyRouteId(@PathVariable String route)
  {
    return orderService.displayOrderbyRouteId(route);
  }
  

  @RequestMapping(value={"/ax01/displayall"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
  public Collection<ArchiveOrders> displayArchiveOrder()
  {
    return orderService.displayArchiveOrder();
  }
  

  @RequestMapping(value={"/ax01/{ordernumber}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
  public ArchiveOrders displayAXOrderbyID(@PathVariable("ordernumber") String ordernumber)
  {
    return orderService.displayAXOrderbyID(ordernumber);
  }
}
