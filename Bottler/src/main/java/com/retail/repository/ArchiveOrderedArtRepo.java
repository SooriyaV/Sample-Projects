package com.retail.repository;

import com.retail.entity.ArchiveOrderedArticle;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public abstract interface ArchiveOrderedArtRepo
  extends CrudRepository<ArchiveOrderedArticle, String>
{
  public abstract List<ArchiveOrderedArticle> findByordernumber(String paramString);
}
