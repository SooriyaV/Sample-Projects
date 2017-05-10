/**
 * Author Sooriya
 * Email sooriya.v@outlook.com
 */
package com.retail.controller;

import com.retail.entity.OrderEnt;
import com.retail.entity.RouteEntity;
import com.retail.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;





@RestController
@RequestMapping({"/route"})
public class RouteController
{
  @Autowired
  private RouteService routeService;
  
  public RouteController() {}
  
  @RequestMapping(value={"/rt01displayall"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
  public Iterable<RouteEntity> RT01displayall()
  {
    return routeService.manipulateRT01(null, "", null);
  }
  
  @RequestMapping(value={"/rt01/{action}"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, consumes={"application/json"})
  public Iterable<RouteEntity> IM01create(@RequestBody RouteEntity rt01, @PathVariable("action") String flag) {
    return routeService.manipulateRT01(rt01, flag, null);
  }
  
  @RequestMapping(value={"/changestatus/{action}/{routename}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
  public Iterable<OrderEnt> manipulateOrders(@PathVariable("routename") String routeName, @PathVariable("action") String status) { return routeService.manipulateOrders(routeName, status); }
  
  @RequestMapping(value={"/ordersinRoute/{routename}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
  public Iterable<String> fetchOrdersinRoute(@PathVariable("routename") String routeName)
  {
    return routeService.fetchOrdersinRoute(routeName);
  }
  
  @RequestMapping(value={"/routesinstatus/{status}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
  public Iterable<RouteEntity> getRoutebystatus(@PathVariable("status") String status) {
    return routeService.getRoutebystatus(status);
  }
}
