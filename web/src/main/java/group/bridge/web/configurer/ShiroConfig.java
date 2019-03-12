package group.bridge.web.configurer;

import group.bridge.web.shiro.configration.MyAuthenticator;
import group.bridge.web.shiro.configration.MyExceptionResolver;
import group.bridge.web.shiro.realm.AdminRealm;
import group.bridge.web.shiro.realm.UserRealm;
import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.*;

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
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);


        //设置无权限跳转的login路由
        shiroFilterFactoryBean.setLoginUrl("/login");
        //设置未授权页面
        //无效，只能用拦截异常方法
        //shiroFilterFactoryBean.setUnauthorizedUrl("/permission/403");
        //设置登陆成功后的url
//        shiroFilterFactoryBean.setSuccessUrl();



        //添加Shiro内置过滤器
        /**
         * Shiro内置过滤器:可以实现权限相关的拦截器
         * 常用的过滤器:
         * anon:无需认证
         * authc:必须认证才可以访问
         * user:如果使用rememberMe的功能可以直接访问
         * perms:该资源必须得到资源权限才可以访问
         * role:该资源必须得到角色权限才可以访问
         * 路由按照遇到的第一个拦截链进行拦截
         */
        Map<String,String> filterMap = new HashMap<>();
        //key是路由 value是过滤器名称
        //路由可以使用*之类的通配符，详细看路由相关知识
        //把login放到例外里
        filterMap.put("/login","anon");
//        filterMap.put("/index","anon");

        //静态资源过滤
        filterMap.put("/**/*.css", "anon");
        filterMap.put("/**/*.jpg", "anon");
        filterMap.put("/**/*.js", "anon");

        //shiro中/*代表子目录
        // /**代表所有
        //测试先不拦截
        //filterMap.put("/**","authc");
        //这个拦/index之类的
//        filterMap.put("/*","authc");
        //授权过滤器
        //在perms的中括号里加上权限字符串
        //字符串可以任意设定
        //在授权拦截后，会自动跳转到未授权页面
        filterMap.put("/add","perms[user:add]");
//        filterMap.put("/shiro/login","anon");


        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        return shiroFilterFactoryBean;
    }
    /**
     * 创建DefaultWebSecurityManager
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("userRealm")UserRealm userRealm,
                                                                  @Qualifier("adminRealm")AdminRealm adminRealm,
                                                                  @Qualifier("authenticator")Authenticator authenticator){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setAuthenticator(authenticator);

        List<Realm> realms = new ArrayList<>();
//        securityManager.setRealm(userRealm);
        realms.add(userRealm);
        realms.add(adminRealm);

        securityManager.setRealms(realms);

        return securityManager;
    }
    /**
     * 创建Realm对象
     *
     */
    @Bean("userRealm")
    public UserRealm userRealm(@Qualifier("userMatcher")HashedCredentialsMatcher matcher){

        UserRealm realm = new UserRealm();
        //把密码匹配器注册到realm中
        realm.setCredentialsMatcher(matcher);
        return realm;
    }

    /**
     * admin realm
     * @param matcher
     * @return
     */
    @Bean("adminRealm")
    public AdminRealm adminRealm(@Qualifier("adminMatcher")HashedCredentialsMatcher matcher){

        AdminRealm realm = new AdminRealm();
        //把密码匹配器注册到realm中
        realm.setCredentialsMatcher(matcher);
        return realm;
    }
    /**
     * 创建密码匹配器
     */
    @Bean("userMatcher")
    public HashedCredentialsMatcher userHashedCredentialsMatcher(){
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
    @Bean("adminMatcher")
    public HashedCredentialsMatcher adminHashedCredentialsMatcher(){
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //设置加密算法名称
        //一般用MD5和SHA-512，SHA-512安全级别更高
        credentialsMatcher.setHashAlgorithmName("SHA-256");
        //设置加密次数
        credentialsMatcher.setHashIterations(3);
        //采用hash散列算法加密
        credentialsMatcher.setStoredCredentialsHexEncoded(true);

        return credentialsMatcher;

    }

    /**
     * 创建认证器bean
     * @return
     */
    @Bean("authenticator")
    public MyAuthenticator myAuthenticator(){
        MyAuthenticator authenticator = new MyAuthenticator();
        return authenticator;
    }

    /**
     *
     */
    //开启shiro aop注解支持
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager securityManager){
        System.out.println("开启了Shiro注解支持");
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
//    @Bean
//    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
//        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
//        advisorAutoProxyCreator.setProxyTargetClass(true);
//        return advisorAutoProxyCreator;
//    }
    @Bean
    public HandlerExceptionResolver resolver(){
        HandlerExceptionResolver resolver = new MyExceptionResolver();
        return resolver;
    }
//@Bean
//public SimpleMappingExceptionResolver getSimpleMappingExceptionResolver() {
//    SimpleMappingExceptionResolver simpleMappingExceptionResolver = new SimpleMappingExceptionResolver();
//    Properties mappings = new Properties();
//    mappings.setProperty("org.apache.shiro.authz.UnauthorizedException", "403.html");
//    simpleMappingExceptionResolver.setExceptionMappings(mappings);
//    return simpleMappingExceptionResolver;
//}
    /**
     * 配置ShiroDialect，用于thymeleaf和shiro标签配合使用
     */
//    @Bean
//    public ShiroDialect getShiroDialect(){
//        return new ShiroDialect();
//    }
}
