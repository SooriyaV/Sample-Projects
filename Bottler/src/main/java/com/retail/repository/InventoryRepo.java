package com.retail.repository;

import com.retail.entity.InventoryDetails;
import org.springframework.data.repository.CrudRepository;

public abstract interface InventoryRepo
  extends CrudRepository<InventoryDetails, Long>
{}
