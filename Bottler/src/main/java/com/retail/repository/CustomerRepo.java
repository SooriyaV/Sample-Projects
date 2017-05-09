package com.retail.repository;

import com.retail.entity.CustomerEntity;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public abstract interface CustomerRepo
  extends JpaRepository<CustomerEntity, Serializable>, JpaSpecificationExecutor<CustomerEntity>
{}
