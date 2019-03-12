package group.bridge.web.service;

import group.bridge.web.entity.User;

/**
 * @author wuran
 * @Created on 2019/3/5
 */
public interface UserService extends BaseService<User,Integer> {
    User get(String username);
    User find(String username);
}
