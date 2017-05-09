package com.retail.security;

import java.security.Principal;

@org.springframework.web.bind.annotation.RestController
public class UiApplication
{
  public UiApplication() {}
  
  @org.springframework.web.bind.annotation.RequestMapping({"/user"})
  public Principal user(Principal user) {
    System.out.println("The header is : " + user.toString());
    return user;
  }
}
