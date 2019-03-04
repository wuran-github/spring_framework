package group.bridge.web.configurer;

import group.bridge.web.realm.UserRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.naming.Name;
import java.util.HashMap;
import java.util.Map;

/**
 * Shiro配置类
 * @author wuran
 *
 */
@Configuration
public class ShiroConfig {
    /**
     * 创建ShiroFilterFactoryBean
     */
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //添加Shiro内置过滤器
        /**
         * Shiro内置过滤器:可以实现权限相关的拦截器
         * 常用的过滤器:
         * anon:无需认证
         * authc:必须认证才可以访问
         * user:如果使用rememberMe的功能可以直接访问
         * perms:该资源必须得到资源权限才可以访问
         * role:该资源必须得到角色权限才可以访问
         */
        Map<String,String> filterMap = new HashMap<>();
        //key是路由 value是过滤器名称
        //路由可以使用*之类的通配符，详细看路由相关知识
        filterMap.put("/index","authc");

        //授权过滤器
        //在perms的中括号里加上权限字符串
        //字符串可以任意设定
        //在授权拦截后，会自动跳转到未授权页面
        filterMap.put("/add","perms[user:add]");


        //设置无权限跳转的login路由
        shiroFilterFactoryBean.setLoginUrl("/login");
        //设置未授权页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/unAuth");





        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        return shiroFilterFactoryBean;
    }
    /**
     * 创建DefaultWebSecurityManager
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm")UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }
    /**
     * 创建Realm对象
     *
     */
    @Bean("userRealm")
    public UserRealm getRealm(@Qualifier("matcher")HashedCredentialsMatcher matcher){

        UserRealm realm = new UserRealm();
        //把密码匹配器注册到realm中
        realm.setCredentialsMatcher(matcher);
        return realm;
    }

    /**
     * 创建密码匹配器
     */
    @Bean("matcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //设置加密算法名称
        //一般用MD5和SHA-512，SHA-512安全级别更高
        credentialsMatcher.setHashAlgorithmName("SHA-512");
        //设置加密次数
        credentialsMatcher.setHashIterations(3);
        //采用hash散列算法加密
        credentialsMatcher.setStoredCredentialsHexEncoded(true);


        return credentialsMatcher;

    }

    /**
     * 配置ShiroDialect，用于thymeleaf和shiro标签配合使用
     */
//    @Bean
//    public ShiroDialect getShiroDialect(){
//        return new ShiroDialect();
//    }
}
