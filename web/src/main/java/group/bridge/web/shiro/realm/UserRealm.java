package group.bridge.web.shiro.realm;

import group.bridge.web.entity.User;
import group.bridge.web.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义Realm
 *
 * @author wuran
 * @date 2019/3/4
 */
public class UserRealm extends AuthorizingRealm {
    @Override
    public String getName(){
        return "User";
    }
    @Autowired
    private UserService userService;

    /**
     * mock
     */
//    public UserRealm(){
//        super();
//        userService = new UserServiceMock();
//    }
    /**
     * 执行授权逻辑
     *
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
//        info.addStringPermission("user:add");

        //获取当前登录用户
        Subject subject = SecurityUtils.getSubject();

        //获取参数
        //principal就是在认证授权时第一个传递回来的参数
        String name = (String) subject.getPrincipal();

        return info;
    }

    /**
     * 执行认证逻辑
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {



        /**
         * 编写shiro判断逻辑
         */
        //1.判断用户名
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = userService.get(token.getUsername());
        if (user == null) {
            //用户名不存在
            return null;//shiro自动抛出unknownAccount

        }
        String realmName = getName();
        //2.判断密码
        //直接返回一个SimpleAuthenticationInfo类
        //密码不正确会自动抛出错误
        //加盐加密就不能直接调用简单的构造函数，需要更复杂的构造函数
        //credential 就是凭证，这里是密码,也就是数据库存储的密码
        String credentials = user.getPassword();
        //使用username作为盐,通过ByteSource.Util.bytes将其加密
        ByteSource salt = ByteSource.Util.bytes(token.getUsername());


        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(token.getUsername(), credentials, salt, realmName);
        return info;
    }

    /**
     * 执行认证逻辑 无加密版本
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//
//        String name = "admin";
//        String password = "admin";
//
//        /**
//         * 编写shiro判断逻辑
//         */
//        //1.判断用户名
//        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
//
//        if (token.getUsername().equals(name)) {
//            //用户名不存在
//            return null;//shiro自动抛出unknownAccount
//
//        }
//        String realmName = getName();
//        //2.判断密码
//        //直接返回一个SimpleAuthenticationInfo类
//        //构造函数
//        //第一个参数是存放到subject的一些参数，就是principal
//        //第二个参数是数据库密码
//        //第三个参数是realmName的名字
//        //密码不正确会自动抛出错误
//        return new SimpleAuthenticationInfo(name, password, "");
//    }
}
