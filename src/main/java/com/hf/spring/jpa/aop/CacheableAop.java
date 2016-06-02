package com.hf.spring.jpa.aop;

import java.lang.annotation.Annotation;
import java.util.concurrent.TimeUnit;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import com.hf.spring.jpa.annotation.CacheKey;
import com.hf.spring.jpa.annotation.Cacheable;
import com.hf.spring.jpa.annotation.Cacheable.KeyMode;

@Aspect
@Component
public class CacheableAop {
	
	@Autowired 
	private RedisTemplate redisTemplate;
	
	@Around("@annotation(cache)")//参数中 Cacheable注解类型的参数名字为 cache。所以这样写。或者@annotation(com.hf.spring.jpa.annotation.Cacheable)
	public Object cached(final ProceedingJoinPoint pjp,Cacheable cache) throws Throwable {
		
		String key=getCacheKey(pjp, cache);
		ValueOperations<String, Object> valueOper=redisTemplate.opsForValue();
		Object value=valueOper.get(key);	//从缓存获取数据
		if(value!=null) return value;		//如果有数据,则直接返回
		
		value = pjp.proceed();		//跳过缓存,到后端查询数据
		if(cache.expire()<=0) {		//如果没有设置过期时间,则无限期缓存
			valueOper.set(key, value);
		} else {					//否则设置缓存时间
			valueOper.set(key, value,cache.expire(),TimeUnit.SECONDS);
		}
		return value;
	}
	
	/**
	 * 获取缓存的key值
	 * @param pjp
	 * @param cache
	 * @return
	 */
	private String getCacheKey(ProceedingJoinPoint pjp,Cacheable cache) {
		
		StringBuilder buf=new StringBuilder();
		buf.append(pjp.getSignature().getDeclaringTypeName()).append(".").append(pjp.getSignature().getName());
		if(cache.key().length()>0) {
			buf.append(".").append(cache.key());
		}
		
		Object[] args=pjp.getArgs();
		if(cache.keyMode()==KeyMode.DEFAULT) {
			//此处代码有问题，不能使用default keymode
			Annotation[][] pas=((MethodSignature)pjp.getSignature()).getMethod().getParameterAnnotations();
			for(int i=0;i<pas.length;i++) {
				for(Annotation an:pas[i]) {
					if(an instanceof CacheKey) {
						buf.append(".").append(args[i].toString());
						break;
					}
				}
			}
		} else if(cache.keyMode()==KeyMode.BASIC) {
			for(Object arg:args) {
				if(arg instanceof String) {
					buf.append(".").append(arg);
				} else if(arg instanceof Integer || arg instanceof Long || arg instanceof Short) {
					buf.append(".").append(arg.toString());
				} else if(arg instanceof Boolean) {
					buf.append(".").append(arg.toString());
				}
			}
		} else if(cache.keyMode()==KeyMode.ALL) {
			for(Object arg:args) {
				buf.append(".").append(arg.toString());
			}
		}
		
		return buf.toString();
	}
}
