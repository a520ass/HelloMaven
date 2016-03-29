package com.hf.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;  
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;  
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;  
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:spring-context-jpa.xml","classpath:spring-mvc.xml","classpath:spring-context-shiro.xml","classpath:spring-context-redis.xml"})
public class MvcTest {
	
	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;
	
    @Before  
    public void setup() {   
        this.mockMvc = webAppContextSetup(this.wac).build();  
    } 
    
    @Test  
    public void testLogin() throws Exception { 
    	//mockMvc.perform((post("/sys/login").param("username", "superadmin").param("password", "admin"))).andExpect(status().isOk()).andDo(print()); 
        mockMvc.perform((post("/test/testStringJson"))).andExpect(status().isOk()).andDo(print());  
    } 


}
