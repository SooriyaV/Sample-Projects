package com.retail.repository;

import com.retail.entity.LoadDifferenceEntity;
import org.springframework.data.repository.CrudRepository;

public abstract interface LoadDiffRepo
  extends CrudRepository<LoadDifferenceEntity, String>
{
  public abstract Iterable<LoadDifferenceEntity> findByorderNumber(String paramString);
}
