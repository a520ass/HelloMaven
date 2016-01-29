package com.hf.freemarker;

public class Product {

    private String url;
    private String name;
    
    // As per the JavaBeans spec., this defines the "url" bean property
    public String getUrl() {
        return url;
    }
    
    // As per the JavaBean spec., this defines the "name" bean property
    public String getName() {
        return name;
    }
    
}
