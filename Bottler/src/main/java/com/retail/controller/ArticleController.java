/**
 * Author Sooriya
 * Email sooriya.v@outlook.com
 */
package com.retail.controller;

import com.retail.entity.ArticleEntity;
import com.retail.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;





@RestController
@RequestMapping({"/article"})
public class ArticleController
{
  @Autowired
  private ArticleService articleService;
  
  public ArticleController() {}
  
  @RequestMapping(value={"/am01displayall"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
  public Iterable<ArticleEntity> IM01displayall()
  {
    return articleService.manipulateAM01(null, "", null);
  }
  
  @RequestMapping(value={"/am01/{action}"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, consumes={"application/json"})
  public Iterable<ArticleEntity> IM01create(@RequestBody ArticleEntity am01, @PathVariable("action") String flag) {
    return articleService.manipulateAM01(am01, flag, null);
  }
}
