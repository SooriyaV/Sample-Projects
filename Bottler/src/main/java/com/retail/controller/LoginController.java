package com.retail.controller;

import com.retail.entity.LoginEnt;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping({"/doLogin"})
public class LoginController
{
  public LoginController() {}
  
  @RequestMapping(value={"/check"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, consumes={"application/json"})
  public LoginEnt checkLogin(@RequestBody LoginEnt bean)
  {
    LoginEnt login = new LoginEnt();
    if ((bean.getUserId().equals("user")) && (bean.getPassWord().equals("password1")))
    {
      login.setUserId(bean.getUserId());
      login.setAuthenticated(true);
    }
    else {
      login.setUserId(bean.getUserId());
      login.setAuthenticated(false);
    }
    return login;
  }
}
