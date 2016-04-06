package com.hf.spring.jpa.common.realm;

import java.util.Set;

import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.google.common.collect.Sets;

public class MyCasRealm extends CasRealm{
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = (String)principals.getPrimaryPrincipal();  
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Set<String> roles=Sets.newHashSet();
        roles.add("user");
        roles.add("admin");
		authorizationInfo.setRoles(roles);
		Set<String> permissions=Sets.newHashSet();
		permissions.add("sys:insert");
		authorizationInfo.setStringPermissions(permissions);
        return authorizationInfo;
	}
}
