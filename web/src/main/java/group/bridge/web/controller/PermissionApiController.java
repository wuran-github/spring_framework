package group.bridge.web.controller;

import group.bridge.web.annotation.GuidLog;
import group.bridge.web.entity.Permission;
import group.bridge.web.entity.Person;
import group.bridge.web.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

/**
 * @author wuran
 * @Created on 2019/3/14
 */
@RestController
@RequestMapping("api/permission")
public class PermissionApiController {
    @Autowired
    PermissionService permissionService;
    @RequestMapping("getList")
    public Page<Permission> getPermission(int page){
        Pageable pageable = PageRequest.of(page,5);

        Page<Permission> permissionPage = permissionService.getAll(pageable);
//        List<Permission> permissions = permissionPage.getContent();
        return permissionPage;
    }

    @RequestMapping("add")
    @GuidLog(table = "permission",operationType = "add")
    public String addPermission(){

        System.out.println("add");
        return null;
    }
}
