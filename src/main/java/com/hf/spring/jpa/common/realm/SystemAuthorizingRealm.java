package com.hf.spring.jpa.common.realm;

import javax.annotation.PostConstruct;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.hf.spring.jpa.entity.User;
import com.hf.spring.jpa.service.UserService;

@Component
public class SystemAuthorizingRealm extends AuthorizingRealm{
	
	@Autowired
	UserService userService;
	/*
	 * 授权方法
	 * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		// TODO Auto-generated method stub
		SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
		Object principal=principals.getPrimaryPrincipal();
		if("superadmin".equals(principal)){
			info.addRole("admin");
			info.addStringPermission("sys:insert");
		}else{
			
		}
		info.addRole("user");
		return info;
	}
	
	/*
	 * 认证方法
	 * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		User user=userService.findUserByUsername(token.getPrincipal().toString());
		Object principal=user.getUsername();
		//认证信息
		Object hashedCredentials=user.getPassword();//"f95abf3936f71d3f67e1cfb7f33ba238";
		//设置盐值
		String source="lovebeibei";
		ByteSource credentialsSalt=new Md5Hash(source);
		//当前realm的name
		String realmName=getName();
		SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(principal, hashedCredentials, credentialsSalt, realmName);
		return info;
	}
	
	/*
	 * 设定密码校验
	 */
	@PostConstruct
	public void initCredentialsMatcher(){
		HashedCredentialsMatcher credentialsMatcher=new HashedCredentialsMatcher();
		credentialsMatcher.setHashAlgorithmName("MD5");
		credentialsMatcher.setHashIterations(1024);
		setCredentialsMatcher(credentialsMatcher);
	}
	
	//生成测试密码
	@Test
	public void test1(){
		String algorithmName="MD5";
		String credentials ="admin";
		String saltsource="lovebeibei";
		Object salt=new Md5Hash(saltsource);
		int hashIterations=1024;
		Object result=new SimpleHash(algorithmName,credentials, salt, hashIterations);
		System.out.println(result.toString());
	}

}
