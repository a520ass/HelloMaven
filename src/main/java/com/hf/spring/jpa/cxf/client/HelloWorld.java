
package com.hf.spring.jpa.cxf.client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "HelloWorld", targetNamespace = "http://cxf.jpa.spring.hf.com/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface HelloWorld {


    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "sayHi", targetNamespace = "http://cxf.jpa.spring.hf.com/", className = "com.hf.spring.jpa.cxf.SayHi")
    @ResponseWrapper(localName = "sayHiResponse", targetNamespace = "http://cxf.jpa.spring.hf.com/", className = "com.hf.spring.jpa.cxf.SayHiResponse")
    public String sayHi(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

}
