package com.hf.freemarker;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.hf.spring.jpa.entity.User;

public class TestFreemarker {
	FreemarkerUtil fu;
	Map<String, Object> root = null;

	@Before
	public void setUp() {
		fu = new FreemarkerUtil();
		root = new HashMap<String, Object>();
	}

	@Test
	public void test01() {
		// 1、创建数据模型
		Map<String, Object> root = new HashMap<String, Object>();
		// 2、为数据模型添加值
		//root.put("username", "张三");
		// 3、将数据模型和模板组合的数据输出到控制台
		//fu.print("f1.ftl", root);
		//fu.fprint("f1.ftl", root, "01.html");
		
		
		User user=new User();
		user.setId(12);
		user.setUsername("何锋");
		user.setPassword("1234");
		root.put("user", user);
		fu.print("03.ftl", root);
	}
}
