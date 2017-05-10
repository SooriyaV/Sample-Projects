/**
 * Author Sooriya
 * Email sooriya.v@outlook.com
 */
package com.retail.controller;

import com.retail.entity.RouteEntity;
import com.retail.entity.RouteinSMEnt;
import com.retail.entity.SettlementEntity;
import com.retail.service.SettlementService;
import java.sql.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;









@RestController
@RequestMapping({"/settlement"})
public class SettlementController
{
  @Autowired
  private SettlementService setttlementService;
  
  public SettlementController() {}
  
  @RequestMapping(value={"/sm01/{batchDate}/{flag}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
  public Iterable<SettlementEntity> manipulateSM01(@PathVariable("batchDate") Date batchDate, @PathVariable("flag") String flag)
  {
    return setttlementService.manipulateSM01(batchDate, flag);
  }
  
  @RequestMapping(value={"/displayroutes/{batchDate}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
  public Iterable<RouteinSMEnt> displayroutesbybatch(@PathVariable("batchDate") Date batchDate) { return setttlementService.displayroutesbybatch(batchDate); }
  
  @RequestMapping(value={"/addroutes/{batchDate}"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, consumes={"application/json"})
  public Iterable<RouteinSMEnt> addRoutes(@RequestBody Iterable<RouteEntity> routes, @PathVariable("batchDate") Date batchDate) {
    return setttlementService.addRoutes(routes, batchDate);
  }
  
  @RequestMapping(value={"/settle/{batchDate}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
  public Iterable<SettlementEntity> settleDay(@PathVariable("batchDate") Date batchDate) {
    return setttlementService.settleDay(batchDate);
  }
}
