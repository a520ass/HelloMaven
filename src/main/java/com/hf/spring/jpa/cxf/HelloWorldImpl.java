package com.hf.spring.jpa.cxf;

import javax.jws.WebService;

import org.springframework.stereotype.Component;

@Component  
@WebService(endpointInterface = "com.hf.spring.jpa.cxf.HelloWorld")  
public class HelloWorldImpl implements HelloWorld {  
  
    @Override  
    public String sayHi(String text) {  
        return "hello " + text;  
    }  
  
}
