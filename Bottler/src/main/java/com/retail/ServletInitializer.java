package com.retail;

import org.springframework.boot.builder.SpringApplicationBuilder;

public class ServletInitializer extends org.springframework.boot.web.support.SpringBootServletInitializer
{
  public ServletInitializer() {}
  
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(new Class[] { Main.class });
  }
}
