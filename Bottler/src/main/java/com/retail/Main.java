package com.retail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@org.springframework.boot.autoconfigure.EnableAutoConfiguration
@ComponentScan
@EnableJpaRepositories
public class Main extends org.springframework.boot.web.support.SpringBootServletInitializer
{
  public Main() {}
  
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
  {
    return application.sources(new Class[] { Main.class });
  }
  
  public static void main(String[] args) {
    SpringApplication.run(Main.class, args);
  }
}
