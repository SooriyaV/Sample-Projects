/**
 * Author Sooriya
 * Email sooriya.v@outlook.com
 */
package com.retail.dao;

import com.retail.entity.ArticleEntity;
import com.retail.repository.ArticleRepo;
import org.springframework.stereotype.Component;

@Component
public class ArticleDAO
{
  @org.springframework.beans.factory.annotation.Autowired
  private ArticleRepo articlerepo;
  
  public ArticleDAO() {}
  
  public Iterable<ArticleEntity> manipulateAM01(ArticleEntity am01, String flag, Long id)
  {
    if ((flag.equals("CREATE")) || (flag.equals("UPDATE")))
    {
      articlerepo.save(am01);

    }
    else if (flag.equals("DELETE"))
    {
      articlerepo.delete(am01);
    }
    
    return articlerepo.findAll();
  }
}
