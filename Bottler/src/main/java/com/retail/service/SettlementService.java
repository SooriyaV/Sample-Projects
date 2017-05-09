package com.retail.service;

import com.retail.dao.SettlementDAO;
import com.retail.entity.RouteEntity;
import com.retail.entity.RouteinSMEnt;
import com.retail.entity.SettlementEntity;
import java.sql.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettlementService
{
  @Autowired
  private SettlementDAO settlementDAO;
  
  public SettlementService() {}
  
  public Iterable<SettlementEntity> manipulateSM01(Date batchDate, String flag)
  {
    return settlementDAO.manipulateSM01(batchDate, flag);
  }
  
  public Iterable<RouteinSMEnt> displayroutesbybatch(Date batchDate) {
    return settlementDAO.displayroutesbybatch(batchDate);
  }
  
  public Iterable<RouteinSMEnt> addRoutes(Iterable<RouteEntity> routes, Date batchDate) {
    return settlementDAO.addRoutes(routes, batchDate);
  }
  
  public Iterable<SettlementEntity> settleDay(Date batchDate) {
    return settlementDAO.settleDay(batchDate);
  }
}
