package com.hf.spring.jpa.cxf;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.model.wadl.Description;
import org.apache.cxf.jaxrs.model.wadl.DocTarget;
import org.springframework.stereotype.Component;


@Component("restSample")  
public class RestTest1 {  
  
    @GET  
    @Path("/order")  
    @Produces({ MediaType.APPLICATION_JSON })  
    @Description(value = "订单资源详情", target = DocTarget.METHOD)
    public Order getOrder() {  
        Order o = new Order(10L, "李先生", "牛肉面", 20);  
        return o;  
    }  
} 
