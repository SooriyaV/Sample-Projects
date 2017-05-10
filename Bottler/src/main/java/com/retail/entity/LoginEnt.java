/**
 * Author Sooriya
 * Email sooriya.v@outlook.com
 */
package com.retail.entity;


public class LoginEnt
{
  private String userId;
  
  private String passWord;
  private boolean authenticated;
  
  public LoginEnt() {}
  
  public String getUserId()
  {
    return userId;
  }
  
  public void setUserId(String userId) {
    this.userId = userId;
  }
  
  public String getPassWord() {
    return passWord;
  }
  
  public void setPassWord(String passWord) {
    this.passWord = passWord;
  }
  
  public boolean getAuthenticated() {
    return authenticated;
  }
  
  public void setAuthenticated(boolean authenticated) {
    this.authenticated = authenticated;
  }
}
