package com.retail.repository;

import com.retail.entity.InventoryTransaction;
import org.springframework.data.repository.CrudRepository;

public abstract interface InventoryTransactionRepo
  extends CrudRepository<InventoryTransaction, Long>
{
  public abstract Iterable<InventoryTransaction> findByinventoryNumber(String paramString);
}
