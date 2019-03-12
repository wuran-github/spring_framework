package group.bridge.web.shiro.configration;

import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @author wuran
 * @Created on 2019/3/7
 */
public class MyAuthorizer extends ModularRealmAuthorizer {
    @Override
    public boolean isPermitted(PrincipalCollection principals, String permission) {
        return super.isPermitted(principals, permission);
    }
}
