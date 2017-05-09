package com.retail.utils;

import com.retail.entity.OrderEnt;
import com.retail.entity.PricingTotals;

public class PricingEngine
{
  public PricingEngine() {}
  
  public PricingTotals calculate(OrderEnt order)
  {
    PricingTotals price = new PricingTotals();
    
    double prdamt = 0.0D;
    double discount = 0.0D;
    double taxes = 0.0D;
    
    if (order != null)
    {




      discount = prdamt * 0.05D;
      taxes = prdamt * 0.1D;
      price.setDiscount(discount);
      price.setTaxes(taxes);
      price.setTotamt(prdamt - discount + taxes);
      
      return price;
    }
    

    return null;
  }
}
