package com.retail.repository;

import com.retail.entity.OrderedArticle;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public abstract interface OrderedArticleRepo
  extends CrudRepository<OrderedArticle, Integer>
{
  public abstract List<OrderedArticle> findByordernumber(String paramString);
}
