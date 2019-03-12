package group.bridge.web.shiro.configration;

import group.bridge.web.shiro.realm.AdminRealm;
import group.bridge.web.shiro.realm.UserRealm;
import group.bridge.web.shiro.token.UserToken;
import org.apache.shiro.authc.AbstractAuthenticator;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;

import java.util.Collection;

/**
 * @author wuran
 * @Created on 2019/3/5
 */
public class MyAuthenticator extends ModularRealmAuthenticator {
    /**
     *
     * @param authenticationToken 其实就是token本身会传递进来
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken) throws AuthenticationException {

        assertRealmsConfigured();

        Collection<Realm> realms = getRealms();
        UserRealm userRealm = null;
        AdminRealm adminRealm = null;
        for(Realm realm : realms){
            if(realm.getName().equals("Admin")){
                adminRealm =(AdminRealm)realm;
            }
            else if(realm.getName().equals("User")){
                userRealm =(UserRealm)realm;
            }
        }
        UserToken token = null;
        if(authenticationToken instanceof UserToken){
             token =(UserToken)authenticationToken;
        }
        //通过doSingleRealmAuthentication来决定传递给哪个realm
        if(token != null){
            if(token.isAdmin()){
                return doSingleRealmAuthentication(adminRealm,authenticationToken);
            }
            else{
                return doSingleRealmAuthentication(userRealm,authenticationToken);
            }
        }

        return doMultiRealmAuthentication(realms,authenticationToken);
    }
}
