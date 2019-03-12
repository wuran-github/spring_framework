package group.bridge.web.shiro.realm;

import group.bridge.web.entity.User;
import group.bridge.web.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author wuran
 * @Created on 2019/3/5
 */

public class AdminRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    /**
     * mock
     */
//    public AdminRealm(){
//        super();
//        userService = new UserServiceMock();
//    }
    @Override
    public String getName(){
        return "Admin";
    }
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("admin realm 授权");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        info.addStringPermission("admin:add");

        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("admin realm 认证");

        /**
         * 编写shiro判断逻辑
         */
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


        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo("", credentials, salt, realmName);
        return info;
    }
}
