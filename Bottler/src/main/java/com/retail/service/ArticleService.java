package com.retail.service;

import com.retail.dao.ArticleDAO;
import com.retail.entity.ArticleEntity;

@org.springframework.stereotype.Service
public class ArticleService
{
  @org.springframework.beans.factory.annotation.Autowired
  private ArticleDAO articledao;
  
  public ArticleService() {}
  
  public Iterable<ArticleEntity> manipulateAM01(ArticleEntity am01, String flag, Long id)
  {
    return articledao.manipulateAM01(am01, flag, id);
  }
}
