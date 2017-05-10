/**
 * Author Sooriya
 * Email sooriya.v@outlook.com
 */
package com.retail.repository;

import com.retail.entity.CustomerGroupCriteria;
import org.springframework.data.repository.CrudRepository;

public abstract interface CustomerGroupCriteriaRepo
  extends CrudRepository<CustomerGroupCriteria, String>
{
  public abstract Iterable<CustomerGroupCriteria> findBygroup(String paramString);
}
