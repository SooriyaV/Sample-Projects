package com.retail.repository;

import com.retail.entity.OrderEnt;
import java.util.Date;
import org.springframework.data.repository.CrudRepository;

public abstract interface BaseRepository
  extends CrudRepository<OrderEnt, Long>
{
  public abstract OrderEnt findByordernumber(String paramString);
  
  public abstract Iterable<OrderEnt> findByrouteName(String paramString);
  
  public abstract Iterable<OrderEnt> findByordereddate(Date paramDate);
}
