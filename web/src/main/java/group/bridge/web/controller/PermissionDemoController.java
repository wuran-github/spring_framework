package group.bridge.web.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wuran
 * @Created on 2019/3/6
 */
@Controller
@RequestMapping("permission")
public class PermissionDemoController {
    //需要user:add权限
    @RequestMapping("userAdd")
    @RequiresPermissions("user:add")
    public String userAdd(){
        return "common/index";
    }
    @RequestMapping("adminAdd")
    @RequiresPermissions("admin:add")
    public String adminAdd(){
        return "common/index";
    }
    @RequestMapping("403")
    public String noPermission(){
        return "common/403";
    }
}
