package com.hf.spring.jpa.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


//当userservice执行时，切入
@Aspect
@Component
public class UserAspect {
	
	/*
	 * execution()：表示拦截方法，括号中可定义需要匹配的规则。

		第一个“*”：表示方法的返回值是任意的。

		第二个“*”：表示匹配该类中所有的方法。

		(..)：表示方法的参数是任意的。
	 */
	//@Around("execution(* com.hf.spring.jpa.service.impl.UserServiceImpl.*(..))")
	public Object around(ProceedingJoinPoint pjp) throws Throwable{
		//beforeAdvice();
		Object[] args=pjp.getArgs();
		if(args.length>0){
			for(int i=0;i<args.length;i++){
				if(args[i]==null){
				}
			}
		}else{
			
		}
		Object result=null;
		try {
			result=pjp.proceed();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		if(result==null){
			result="";
		}else{
			
		}
		//afterFinallyAdvice();
		return result;
	}
	
	public void beforeAdvice(){
		System.out.println("beforeAdvice............UserServiceImpl");
	}
	
	public void afterFinallyAdvice(){
		System.out.println("afterFinallyAdvice..............UserServiceImpl");
	}
}
