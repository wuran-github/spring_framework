package group.bridge.web.mock;

import group.bridge.web.entity.User;
import group.bridge.web.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wuran
 * @Created on 2019/3/5
 */
//public class UserServiceMock  {
//    private Map<String,User> map = new HashMap<>();
//
//    public UserServiceMock(){
//        User admin = new User("admin","095144c3d1c1bb6b2711cb6e894db8748471a0ff9f2415879da5b36240cb14a9",true);
//        User user = new User("user",
//                "a95c5639c0d47c34d8eb3039e8c77f94b4a460422254721a1186687e79d2124759ac9b12d30e2a1134f82bb569fd502255dd33d2ca12795821797aeb829ca515");
//        map.put("user",user);
//        map.put("admin",admin);
//
//    }
//
//    @Override
//    public boolean add(User user) {
//        map.put(user.getUsername(),user);
//        return true;
//    }
//
//    @Override
//    public void addAll(List<User> list) {
//
//    }
//
//    @Override
//    public boolean update(User user) {
//        return false;
//    }
//
//    @Override
//    public void delete(User user) {
//
//    }
//
//    @Override
//    public void deleteById(Integer s) {
//
//    }
//
//    @Override
//    public List<User> getAll() {
//        return new ArrayList<>(map.values());
//    }
//
//    @Override
//    public Page<User> getAll(Pageable pageable) {
//        return null;
//    }
//
//    @Override
//    public User get(Integer s) {
//        return map.get(s);
//    }
//
//    @Override
//    public User get2(Integer s) {
//        return null;
//    }
//
//    @Override
//    public List<User> getAllNotCascade() {
//        return null;
//    }
//
//    @Override
//    public List<User> getByPredicate(Specification<User> specification) {
//        return null;
//    }
//
//    @Override
//    public Page<User> getByPredicate(Specification<User> specification, Pageable pageable) {
//        return null;
//    }
//
//    @Override
//    public Long count() {
//        return  (long)map.size();
//    }
//
//    @Override
//    public User get(String username) {
//        return null;
//    }
//}
