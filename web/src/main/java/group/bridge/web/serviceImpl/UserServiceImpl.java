package group.bridge.web.serviceImpl;

import group.bridge.web.dao.UserRepository;
import group.bridge.web.entity.User;
import group.bridge.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wuran
 * @Created on 2019/3/11
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User, Integer> implements UserService {
    @Autowired
    UserRepository userRepository;
    @Override
    protected void setRepository() {
        this.repository = userRepository;
    }

    @Override
    public User get(String username) {
        return userRepository.getByUsername(username);
    }

    @Override
    public User find(String username) {
        return userRepository.findByUsername(username);
    }
}
