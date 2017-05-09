package com.retail.dao;

import com.retail.entity.LoadDifferenceEntity;
import com.retail.entity.OrderEnt;
import com.retail.entity.OrderedArticle;
import com.retail.entity.RouteEntity;
import com.retail.repository.BaseRepository;
import com.retail.repository.LoadDiffRepo;
import com.retail.repository.OrderedArticleRepo;
import com.retail.repository.RouteRepo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;





@Component
public class RouteDAO
{
  @Autowired
  private RouteRepo routeRepo;
  @Autowired
  private BaseRepository orderRepo;
  @Autowired
  private OrderedArticleRepo ordArticleRepo;
  @Autowired
  private LoadDiffRepo loadDiffRepo;
  
  public RouteDAO() {}
  
  public Iterable<RouteEntity> manipulateRT01(RouteEntity rt01, String flag, Long id)
  {
    List<RouteEntity> list = new ArrayList();
    
    if ((flag.equals("CREATE")) || (flag.equals("UPDATE")))
    {
      routeRepo.save(rt01);

    }
    else if (flag.equals("DELETE"))
    {
      routeRepo.delete(rt01);
    }
    else if (flag.equals("AVAILABLE"))
    {
      return routeRepo.findBystatus("NEW");
    }
    list.addAll((Collection)routeRepo.findBystatus("NEW"));
    list.addAll((Collection)routeRepo.findBystatus("CHKOUT"));
    list.addAll((Collection)routeRepo.findBystatus("CHKIN"));
    
    return list;
  }
  
  public Iterable<OrderEnt> manipulateOrders(String routeName, String status)
  {
    RouteEntity rte = routeRepo.findByrouteName(routeName);
    rte.setStatus(status);
    routeRepo.save(rte);
    
    if (!status.equals("NEW"))
    {




      List<LoadDifferenceEntity> list = new ArrayList();
      Iterable<OrderEnt> obj = orderRepo.findByrouteName(routeName);
      Iterator<OrderEnt> itr = obj.iterator();
      while (itr.hasNext())
      {
        OrderEnt order = (OrderEnt)itr.next();
        order.setStatus(status);
        if (status.equals("CHKOUT")) {
          Iterator<OrderedArticle> ordart = ordArticleRepo.findByordernumber(order.getOrdernumber()).iterator();
          while (ordart.hasNext()) {
            LoadDifferenceEntity ld01 = new LoadDifferenceEntity();
            OrderedArticle objOrdArt = (OrderedArticle)ordart.next();
            ld01.setKeyLD01(objOrdArt.getOrdernumber() + objOrdArt.getArticlenumber());
            ld01.setStatus(status);
            ld01.setItem(objOrdArt.getArticlenumber());
            ld01.setOrderNumber(objOrdArt.getOrdernumber());
            ld01.setActualUnits(objOrdArt.getQuantity());
            list.add(ld01);
          }
        }
        
        orderRepo.save(order);
      }
      loadDiffRepo.save(list);
    }
    

    return orderRepo.findByrouteName(routeName);
  }
  
  public List<String> fetchOrdersinRoute(String routeName) {
    List<String> orders = new ArrayList();
    Iterable<OrderEnt> obj = orderRepo.findByrouteName(routeName);
    Iterator<OrderEnt> itr = obj.iterator();
    
    while (itr.hasNext())
    {
      orders.add(((OrderEnt)itr.next()).getOrdernumber());
    }
    
    return orders;
  }
  
  public Iterable<RouteEntity> getRoutebystatus(String status)
  {
    return routeRepo.findBystatus(status);
  }
}
