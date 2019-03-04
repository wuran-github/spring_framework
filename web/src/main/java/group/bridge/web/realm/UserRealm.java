package group.bridge.web.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

/**
 * 自定义Realm
 * @author wuran
 * @date 2019/3/4
*/
public class UserRealm extends AuthorizingRealm {

    /**
     * 执行授权逻辑
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权逻辑");
        /**
         * 编写授权逻辑
         */
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //添加授权字符串
        info.addStringPermission("user:add");

        //获取当前登录用户
        Subject subject = SecurityUtils.getSubject();

        //获取参数
        //principal就是在认证授权时第一个传递回来的参数
        String name =(String)subject.getPrincipal();

        return info;
    }

    /**
     * 执行认证逻辑
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        String name = "admin";
        String password = "admin";

        /**
         * 编写shiro判断逻辑
         */
        //1.判断用户名
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        if(token.getUsername().equals(name)){
            //用户名不存在
            return null;//shiro自动抛出unknownAccount

        }

        //2.判断密码
        //直接返回一个SimpleAuthenticationInfo类
        //构造函数
        //第一个参数是存放到subject的一些参数，就是principal
        //第二个参数是数据库密码
        //第三个参数是realmName的名字
        //密码不正确会自动抛出错误
        return new SimpleAuthenticationInfo(name,password,"");
    }
}
