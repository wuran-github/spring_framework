package group.bridge.web.democontroller;

import group.bridge.web.entity.Permission;
import group.bridge.web.entity.Role;
import group.bridge.web.entity.User;
import group.bridge.web.service.PermissionService;
import group.bridge.web.service.RoleService;
import group.bridge.web.service.UserService;
import group.bridge.web.util.ShiroUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试用controller
 * @author wuran
 * @Created on 2019/3/11
 */
@RestController
@RequestMapping("/test/api/")
public class TestDemoRestController {
    @Autowired
    PermissionService permissionService;
    @Autowired
    RoleService roleService;
    @Autowired
    UserService userService;

    @RequestMapping(value = "permission/add",method = RequestMethod.POST)
    public Boolean addPermission(Permission permission) {
        permissionService.add(permission);
        return true;
    }

    @RequestMapping(value = "permission/update")
    public Boolean updatePermission() {
        Permission permission = permissionService.get(1);
        Role role = new Role();
        role.setId(4);
        permission.addRoles(role);
        permissionService.update(permission);
        return true;
    }
    @RequestMapping(value = "permission/get/{id}")
    public Permission addPermission(@PathVariable("id")Integer id) {

        return permissionService.get(id);
    }

    @RequestMapping(value = "role/update")
    public Boolean updateRole(){
        Role role = roleService.get2(1);
        Permission p1 = new Permission();
        p1.setId(2);
        role.addPermission(p1);
        roleService.update(role);
        return true;
    }

    @RequestMapping("role/get")
    public void getRole(){
        Role role = roleService.get2(1);
        System.out.println( role.getPermissions().size());
    }
    @RequestMapping("permission/get")
    public void getPermission(){
        Permission P = permissionService.get2(1);
        System.out.println(P.getRoles().size());

    }

    @RequestMapping("user/add")
    public void addUser(){
        List<User> userList = new ArrayList<>(0);
        for(int i=0;i<10;i++){
            User user = new User();
            user.setUsername("test"+i);
            user.setPassword(ShiroUtil.userHash(user.getUsername(),"test"+i));
            user.setSalt(user.getUsername());
            user.setAdmin(false);
            userList.add(user);
        }
        userService.addAll(userList);

    }
    @RequestMapping("user/update")
    public void updateUser(){
        User user2 = userService.get("test1");
        User user = userService.get2(1);
        Role role =new Role();
        role.setId(5);
        user.addRole(role);
        userService.update(user);
    }
    @RequestMapping("user/get")
    public void getUser(){
        List<User> users = userService.getAll();
        System.out.println(users.size());
    }
}
