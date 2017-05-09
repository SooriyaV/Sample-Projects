package com.retail.repository;

import com.retail.entity.SettlementEntity;
import java.sql.Date;
import org.springframework.data.repository.CrudRepository;

public abstract interface SettlementRepo
  extends CrudRepository<SettlementEntity, String>
{
  public abstract SettlementEntity findBybatchDate(Date paramDate);
}
