package com.retail.repository;

import com.retail.entity.ArticleEntity;
import org.springframework.data.repository.CrudRepository;

public abstract interface ArticleRepo
  extends CrudRepository<ArticleEntity, Long>
{}
