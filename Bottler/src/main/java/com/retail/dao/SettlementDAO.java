/**
 * Author Sooriya
 * Email sooriya.v@outlook.com
 */
package com.retail.dao;

import com.retail.entity.ArchiveOrderedArticle;
import com.retail.entity.ArchiveOrders;
import com.retail.entity.OrderEnt;
import com.retail.entity.OrderedArticle;
import com.retail.entity.RouteEntity;
import com.retail.entity.RouteinSMEnt;
import com.retail.entity.SettlementEntity;
import com.retail.repository.ArchiveOrderRepo;
import com.retail.repository.ArchiveOrderedArtRepo;
import com.retail.repository.BaseRepository;
import com.retail.repository.OrderedArticleRepo;
import com.retail.repository.RouteRepo;
import com.retail.repository.SettlementRepo;
import java.io.PrintStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;




@Component
public class SettlementDAO
{
  @Autowired
  private SettlementRepo sm01Repo;
  @Autowired
  private RouteRepo routeRepo;
  @Autowired
  private BaseRepository orderrepo;
  @Autowired
  private OrderedArticleRepo orderartrepo;
  @Autowired
  private ArchiveOrderRepo archiveOrdrepo;
  @Autowired
  private ArchiveOrderedArtRepo archiveartrepo;
  
  public SettlementDAO() {}
  
  public Iterable<SettlementEntity> manipulateSM01(Date batchDate, String flag)
  {
    if (flag.equals("CREATE")) {
      SettlementEntity sm01 = new SettlementEntity();
      sm01.setKeySM01(batchDate.toString());
      sm01.setBatchDate(batchDate);
      sm01.setOrders(0);
      sm01.setRoutes(0);
      sm01.setStatus("started");
      sm01Repo.save(sm01);
    }
    return sm01Repo.findAll();
  }
  

  public Iterable<RouteinSMEnt> displayroutesbybatch(Date batchDate)
  {
    Iterator<RouteEntity> rt01Itr = routeRepo.findBybatchDate(batchDate).iterator();
    List<RouteinSMEnt> list = new ArrayList();
    while (rt01Itr.hasNext()) {
      int count = 0;
      RouteEntity rt01 = (RouteEntity)rt01Itr.next();
      Iterator<OrderEnt> itr = orderrepo.findByrouteName(rt01.getRouteName()).iterator();
      while (itr.hasNext()) {
        itr.next();
        count++;
      }
      RouteinSMEnt obj = new RouteinSMEnt();
      obj.setRoute(rt01.getRouteName());
      obj.setStatus(rt01.getStatus());
      obj.setOrders(count);
      list.add(obj);
    }
    


    return list;
  }
  
  public Iterable<RouteinSMEnt> addRoutes(Iterable<RouteEntity> routes, Date batchDate) {
    Iterator<RouteEntity> itr = routes.iterator();
    
    while (itr.hasNext()) {
      RouteEntity obj = (RouteEntity)itr.next();
      obj.setBatchDate(batchDate);
      obj.setStatus("Settlement Started");
      Iterator<OrderEnt> itr1 = orderrepo.findByrouteName(obj.getRouteName()).iterator();
      while (itr1.hasNext()) {
        OrderEnt objorder = (OrderEnt)itr1.next();
        objorder.setStatus("Settlement Started");
        orderrepo.save(objorder);
      }
      routeRepo.save(obj);
    }
    


    return displayroutesbybatch(batchDate);
  }
  
  public Iterable<SettlementEntity> settleDay(Date batchDate)
  {
    Iterator<RouteEntity> rte = routeRepo.findBybatchDate(batchDate).iterator();
    
    while (rte.hasNext()) {
      RouteEntity rteobj = (RouteEntity)rte.next();
      rteobj.setStatus("SETTLED");
      
      Iterator<OrderEnt> ord = orderrepo.findByrouteName(rteobj.getRouteName()).iterator();
      while (ord.hasNext()) {
        OrderEnt ordobj = (OrderEnt)ord.next();
        

        ArchiveOrders axord = new ArchiveOrders();
        axord.setAX01KEY(ordobj.getTX01KEY());
        axord.setCustomernumber(ordobj.getCustomernumber());
        axord.setDiscount(ordobj.getDiscount());
        axord.setInvnumber(ordobj.getInvnumber());
        axord.setOrdereddate(ordobj.getOrdereddate());
        axord.setOrdernumber(ordobj.getOrdernumber());
        axord.setRouteName(ordobj.getRouteName());
        axord.setStatus("Settled");
        axord.setTotalamount(ordobj.getTotalamount());
        axord.setTotalquantity(ordobj.getTotalquantity());
        archiveOrdrepo.save(axord);
        
        Iterator<OrderedArticle> itr1 = orderartrepo.findByordernumber(ordobj.getOrdernumber()).iterator();
        while (itr1.hasNext()) {
          OrderedArticle tx02 = (OrderedArticle)itr1.next();
          ArchiveOrderedArticle ax02art = new ArchiveOrderedArticle();
          ax02art.setAmount(tx02.getAmount());
          ax02art.setArticlenumber(tx02.getArticlenumber());
          ax02art.setQuantity(tx02.getQuantity());
          ax02art.setOrdernumber(tx02.getOrdernumber());
          ax02art.setAX02KEY(tx02.getOrdernumber() + tx02.getArticlenumber());
          archiveartrepo.save(ax02art);
        }
        
        orderartrepo.delete(orderartrepo.findByordernumber(ordobj.getOrdernumber()));
        orderrepo.delete(ordobj);
      }
      

      routeRepo.save(rteobj);
    }
    
    System.out.println("Date is " + batchDate);
    SettlementEntity sm01 = sm01Repo.findBybatchDate(batchDate);
    System.out.println(sm01.getBatchDate());
    sm01.setStatus("SETTLED");
    sm01Repo.save(sm01);
    
    return sm01Repo.findAll();
  }
}
