package group.bridge.web.controller;

import group.bridge.web.annotation.GuidLog;
import group.bridge.web.entity.Permission;
import group.bridge.web.entity.Person;
import group.bridge.web.model.PageViewModel;
import group.bridge.web.service.PermissionService;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    public PageViewModel<Permission> getPermission(int page){
        Pageable pageable = PageRequest.of(page,5);
        PageViewModel<Permission> pageViewModel = new PageViewModel<>();

        Page<Permission> permissionPage = permissionService.getAll(pageable);

        List<Permission> permissions = permissionPage.getContent();
        pageViewModel.setList(permissions);
        pageViewModel.setCount(permissionPage.getSize());
        return pageViewModel;
    }


    @RequestMapping(value = "add",method = RequestMethod.POST)
    @GuidLog(table = "permission",operationType = "add")
    public Permission addPermission(Permission permission){

        permissionService.add(permission);

        return permission;
    }
    @RequestMapping(value = "update",method = RequestMethod.POST)
    @GuidLog(table = "permission",operationType = "update")
    public Permission updatePermission(Permission permission){

        permissionService.update(permission);

        return permission;
    }
    @GuidLog(table = "permission",operationType = "delete")
    @RequestMapping(value ="delete",method = RequestMethod.POST)
    public boolean deletePermission(int id){
        boolean successful = false;
        try {
            permissionService.deleteById(id);
            successful = true;

        }catch (Exception e){
            successful = false;
            throw  e;
        }
        finally {
            return successful;
        }
    }
}
