package com.hf.spring.jpa.cxf;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService  
public interface HelloWorld {  
	
	@WebMethod
    String sayHi(String text);  
  
}
