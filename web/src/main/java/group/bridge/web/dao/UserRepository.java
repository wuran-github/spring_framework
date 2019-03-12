package group.bridge.web.dao;

import group.bridge.web.entity.User;

/**
 * @author wuran
 * @Created on 2019/3/11
 */
public interface UserRepository extends BaseRepository<User,Integer> {
    User getByUsername(String username);
    User findByUsername(String username);
}
