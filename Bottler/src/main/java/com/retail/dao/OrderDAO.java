/**
 * Author Sooriya
 * Email sooriya.v@outlook.com
 */
package com.retail.dao;

import com.retail.entity.ArchiveOrders;
import com.retail.entity.DashBoardEnt;
import com.retail.entity.DashboardSalesEntity;
import com.retail.entity.OrderEnt;
import com.retail.entity.OrderedArticle;
import com.retail.entity.PricingTotals;
import com.retail.entity.RouteEntity;
import com.retail.repository.ArchiveOrderRepo;
import com.retail.repository.ArchiveOrderedArtRepo;
import com.retail.repository.BaseRepository;
import com.retail.repository.OrderedArticleRepo;
import com.retail.repository.RouteRepo;
import com.retail.utils.PricingEngine;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;













@Component
public class OrderDAO
{
  private BaseRepository baserepository;
  @Autowired
  private OrderedArticleRepo orderedarticlerepo;
  @Autowired
  private RouteRepo routeRepo;
  @Autowired
  private ArchiveOrderRepo ax01;
  @Autowired
  private ArchiveOrderedArtRepo ax02;
  
  public OrderDAO() {}
  
  @Autowired
  public void BaseRepository(BaseRepository baserepository) { this.baserepository = baserepository; }
  
  public void addOrder(OrderEnt ordernumbergen) {
    OrderEnt order = new OrderEnt();
    System.out.println("order is " + ordernumbergen.getOrdernumber());
    if ((ordernumbergen.getOrdernumber() == null) || (ordernumbergen.getOrdernumber().equals("NEW"))) {
      ordernumbergen.setOrdernumber("ORD000");
      baserepository.save(ordernumbergen);
      order = baserepository.findByordernumber("ORD000");
      order.setOrdernumber("ORD000" + order.getTX01KEY());
    }
    else {
      order = ordernumbergen;
    }
    
    List<OrderedArticle> deletetx02 = new ArrayList();
    deletetx02 = orderedarticlerepo.findByordernumber(order.getOrdernumber());
    Iterator<OrderedArticle> itr2 = deletetx02.iterator();
    while (itr2.hasNext()) {
      OrderedArticle ordart2 = (OrderedArticle)itr2.next();
      ordart2.setOrdernumber(order.getOrdernumber());
      ordart2.setTX02KEY(order.getOrdernumber() + ordart2.getArticlenumber());
      orderedarticlerepo.delete(ordart2);
    }
    

    Iterator itr = order.getOrderedarticle().iterator();
    while (itr.hasNext()) {
      OrderedArticle ordart = (OrderedArticle)itr.next();
      ordart.setOrdernumber(order.getOrdernumber());
      ordart.setTX02KEY(order.getOrdernumber() + ordart.getArticlenumber());
      orderedarticlerepo.save(ordart);
    }
    baserepository.save(order);
  }
  




  public Collection<OrderEnt> displayOrder()
  {
    Collection<OrderEnt> orders = new ArrayList();
    Iterator itr = baserepository.findAll().iterator();
    while (itr.hasNext())
    {
      OrderEnt order = (OrderEnt)itr.next();
      order.setOrderedarticle(orderedarticlerepo.findByordernumber(order.getOrdernumber()));
      orders.add(order);
    }
    



    return orders;
  }
  

  public OrderEnt displayOrderbyID(String ordernumber)
  {
    OrderEnt order = baserepository.findByordernumber(ordernumber);
    order.setOrderedarticle(orderedarticlerepo.findByordernumber(order.getOrdernumber()));
    return order;
  }
  

  public PricingTotals calculate(OrderEnt order)
  {
    PricingEngine price = new PricingEngine();
    PricingTotals tot = price.calculate(order);
    System.out.println("value is" + tot.getTaxes() + "  " + tot.getDiscount() + " " + tot.getTotamt());
    return tot;
  }
  
  public Iterable<OrderEnt> deleteOrderEntry(OrderEnt tx)
  {
    Iterator itr = tx.getOrderedarticle().iterator();
    while (itr.hasNext()) {
      OrderedArticle ordart = (OrderedArticle)itr.next();
      ordart.setOrdernumber(tx.getOrdernumber());
      ordart.setTX02KEY(tx.getOrdernumber() + ordart.getArticlenumber());
      orderedarticlerepo.delete(ordart);
    }
    baserepository.delete(tx);
    
    return baserepository.findAll();
  }
  

  public Iterable<DashboardSalesEntity> fetchData(int totalDays)
  {
    List<DashboardSalesEntity> list = new ArrayList();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    Date orderDate = new Date();
    
    for (int i = 0; i < totalDays; i++) {
      DashboardSalesEntity obj = new DashboardSalesEntity();
      Double amount = Double.valueOf(0.0D);
      Date actualDate = new Date();
      actualDate.setDate(orderDate.getDate() - i);
      System.out.println(df.format(actualDate));
      obj.setOrddate(df.format(actualDate));
      Iterator<OrderEnt> itr = baserepository.findByordereddate(actualDate).iterator();
      while (itr.hasNext()) {
        amount = Double.valueOf(amount.doubleValue() + ((OrderEnt)itr.next()).getTotalamount());
        System.out.println(amount);
      }
      
      obj.setTotalSales(amount.doubleValue());
      list.add(obj);
    }
    
    return list;
  }
  


  public DashBoardEnt fetchDashBoard()
  {
    DashBoardEnt obj = new DashBoardEnt();
    
    double difference = 0.0D;
    List<DashboardSalesEntity> salesfortwodays = (List)fetchData(2);
    if ((((DashboardSalesEntity)salesfortwodays.get(1)).getTotalSales() == 0.0D) || (((DashboardSalesEntity)salesfortwodays.get(0)).getTotalSales() == 0.0D)) {
      difference = ((DashboardSalesEntity)salesfortwodays.get(0)).getTotalSales() - ((DashboardSalesEntity)salesfortwodays.get(1)).getTotalSales();
    }
    else {
      difference = (((DashboardSalesEntity)salesfortwodays.get(0)).getTotalSales() - ((DashboardSalesEntity)salesfortwodays.get(1)).getTotalSales()) / ((DashboardSalesEntity)salesfortwodays.get(1)).getTotalSales();
      difference *= 100.0D;
    }
    
    obj.setSalestoday(difference);
    System.out.println(difference);
    Date actualDate = new Date();
    int ordercount = 0;
    Iterator<OrderEnt> itr = baserepository.findByordereddate(actualDate).iterator();
    while (itr.hasNext()) {
      itr.next();
      ordercount++;
    }
    obj.setOrders(ordercount);
    
    int routeChkout = 0;
    Iterator<RouteEntity> itr1 = routeRepo.findBystatus("CHKOUT").iterator();
    while (itr1.hasNext()) {
      itr1.next();
      routeChkout++;
    }
    obj.setRoutesout(routeChkout);
    
    return obj;
  }
  

  public Iterable<OrderEnt> displayOrderbyRouteId(@PathVariable String route)
  {
    return baserepository.findByrouteName(route);
  }
  
  public Collection<ArchiveOrders> displayArchiveOrder()
  {
    Collection<ArchiveOrders> orders = new ArrayList();
    Iterator itr = ax01.findAll().iterator();
    while (itr.hasNext())
    {
      ArchiveOrders order = (ArchiveOrders)itr.next();
      order.setOrderedarticle(ax02.findByordernumber(order.getOrdernumber()));
      orders.add(order);
    }
    



    return orders;
  }
  

  public ArchiveOrders displayAXOrderbyID(String ordernumber)
  {
    ArchiveOrders order = ax01.findByordernumber(ordernumber);
    order.setOrderedarticle(ax02.findByordernumber(order.getOrdernumber()));
    return order;
  }
}
