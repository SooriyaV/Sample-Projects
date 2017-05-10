/**
 * Author Sooriya
 * Email sooriya.v@outlook.com
 */
package com.retail.repository;

import com.retail.entity.ArchiveOrders;
import java.util.Date;
import org.springframework.data.repository.CrudRepository;

public abstract interface ArchiveOrderRepo
  extends CrudRepository<ArchiveOrders, String>
{
  public abstract ArchiveOrders findByordernumber(String paramString);
  
  public abstract Iterable<ArchiveOrders> findByrouteName(String paramString);
  
  public abstract Iterable<ArchiveOrders> findByordereddate(Date paramDate);
}
