package com.retail.repository;

import com.retail.entity.CustomerGroupEntity;
import java.io.Serializable;
import org.springframework.data.repository.CrudRepository;

public abstract interface CustomerGroupRepo
  extends CrudRepository<CustomerGroupEntity, Serializable>
{}
