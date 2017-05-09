package com.retail.service;

import com.retail.dao.LoadDiffDAO;
import com.retail.entity.LoadDifferenceEntity;
import org.springframework.stereotype.Service;

@Service
public class LoadDiffService
{
  @org.springframework.beans.factory.annotation.Autowired
  private LoadDiffDAO loadDiffDao;
  
  public LoadDiffService() {}
  
  public Iterable<LoadDifferenceEntity> manipulateLD01(Iterable<LoadDifferenceEntity> ld01, String orderNumber, String flag)
  {
    return loadDiffDao.manipulateLD01(ld01, orderNumber, flag);
  }
}
