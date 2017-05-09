package com.retail.controller;

import com.retail.entity.LoadDifferenceEntity;
import com.retail.service.LoadDiffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping({"/LoadDiff"})
public class LoadDiffController
{
  @Autowired
  private LoadDiffService loadDiffService;
  
  public LoadDiffController() {}
  
  @RequestMapping(value={"/ld01/{action}/{order}"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, consumes={"application/json"})
  public Iterable<LoadDifferenceEntity> manipulateLD01(@RequestBody Iterable<LoadDifferenceEntity> ld01, @PathVariable("order") String orderNumber, @PathVariable("action") String flag)
  {
    if (flag.equals("DISPLAY")) {
      ld01 = null;
    }
    
    return loadDiffService.manipulateLD01(ld01, orderNumber, flag);
  }
}
