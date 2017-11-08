package com.solar.security.shiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 *
 * @author hushaoge
 * @date 2016/11/3
 */
public class MyRealm2 implements Realm {
    @Override
    public String getName() {
        return "MyReal1";
    }

    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        /**仅支持UsernamePasswordToken类型的Token*/
        return authenticationToken instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        /**得到用户名*/
        String username = (String)authenticationToken.getPrincipal();
        /**得到密码*/
        String password = new String((char[])authenticationToken.getCredentials());
        if(!"li".equals(username)) {
            /**如果用户名错误*/
            throw new UnknownAccountException();
        }
        if(!"123".equals(password)) {
            /*如果密码错误*/
            throw new IncorrectCredentialsException();
        }
        /**如果身份认证验证成功，返回一个AuthenticationInfo实例；*/
        return new SimpleAuthenticationInfo(username, password, getName());
    }

}
