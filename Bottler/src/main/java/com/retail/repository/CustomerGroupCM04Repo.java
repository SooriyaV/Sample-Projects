package com.retail.repository;

import com.retail.entity.CustomerGroupCM04Ent;
import org.springframework.data.repository.CrudRepository;

public abstract interface CustomerGroupCM04Repo
  extends CrudRepository<CustomerGroupCM04Ent, String>
{
  public abstract Iterable<CustomerGroupCM04Ent> findBygroup(String paramString);
}
