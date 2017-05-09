package com.retail.repository;

import com.retail.entity.RouteEntity;
import java.util.Date;
import org.springframework.data.repository.CrudRepository;

public abstract interface RouteRepo
  extends CrudRepository<RouteEntity, String>
{
  public abstract RouteEntity findByrouteName(String paramString);
  
  public abstract Iterable<RouteEntity> findBystatus(String paramString);
  
  public abstract Iterable<RouteEntity> findBybatchDate(Date paramDate);
}
