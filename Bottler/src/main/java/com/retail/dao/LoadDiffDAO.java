/**
 * Author Sooriya
 * Email sooriya.v@outlook.com
 */
package com.retail.dao;

import com.retail.entity.LoadDifferenceEntity;
import com.retail.repository.LoadDiffRepo;
import java.util.Iterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoadDiffDAO
{
  @Autowired
  private LoadDiffRepo loadDiffRepo;
  
  public LoadDiffDAO() {}
  
  public Iterable<LoadDifferenceEntity> manipulateLD01(Iterable<LoadDifferenceEntity> ld01, String orderNumber, String flag)
  {
    if ((flag.equals("CREATE")) || (flag.equals("UPDATE")))
    {

      Iterator<LoadDifferenceEntity> itr = ld01.iterator();
      while (itr.hasNext()) {
        LoadDifferenceEntity obj = (LoadDifferenceEntity)itr.next();
        obj.setKeyLD01(orderNumber + obj.getItem());
        loadDiffRepo.save(obj);
      }
    }
    
    return loadDiffRepo.findByorderNumber(orderNumber);
  }
}
