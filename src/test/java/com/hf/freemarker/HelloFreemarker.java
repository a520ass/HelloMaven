package com.hf.freemarker;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

public class HelloFreemarker {
	
	@Test
	public void test1() throws IOException{
		Configuration cfg=new Configuration();
		cfg.setDirectoryForTemplateLoading(new File("/templates"));
		cfg.setDefaultEncoding("UTF-8");
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		
		// Create the root hash
		Map<String, Object> root = new HashMap<>();
		// Put string ``user'' into the root
		root.put("user", "Big Joe");
		// Create the hash for ``latestProduct''
		Map<String, Object> latest = new HashMap<>();
		// and put it into the root
		root.put("latestProduct", latest);
		// put ``url'' and ``name'' into latest
		latest.put("url", "products/greenmouse.html");
		latest.put("name", "green mouse");
		
	}
}
