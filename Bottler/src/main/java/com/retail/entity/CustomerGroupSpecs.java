/**
 * Author Sooriya
 * Email sooriya.v@outlook.com
 */
package com.retail.entity;

public class CustomerGroupSpecs
{
  private Iterable<CustomerCriteriaElements> customercriteriaelements;
  
  public CustomerGroupSpecs() {}
  
  public Iterable<CustomerCriteriaElements> getCustomercriteriaelements()
  {
    return customercriteriaelements;
  }
  
  public void setCustomercriteriaelements(Iterable<CustomerCriteriaElements> customercriteriaelements)
  {
    this.customercriteriaelements = customercriteriaelements;
  }
}
