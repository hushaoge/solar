package com.solar.security.shiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 *
 * @author hushaoge
 * @date 2017/11/8
 */
public class SystemAuthorizingRealm extends AuthorizingRealm {

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        /**得到用户名*/
        String username = (String) token.getPrincipal();
        /**得到密码*/
        String password = new String((char[]) token.getCredentials());
        if(!"111".equals(username) || !"222".equals(password) ){
            throw new IncorrectCredentialsException();
        }
        /**如果身份认证验证成功，返回一个AuthenticationInfo实例；*/
        return new SimpleAuthenticationInfo(username, password, getName());
    }

    /**
     * 授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }
}
