package group.bridge.web.serviceImpl;

import group.bridge.web.dao.PermissionRepository;
import group.bridge.web.entity.Permission;
import group.bridge.web.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wuran
 * @Created on 2019/3/11
 */
@Service
public class PermissionServiceImpl extends BaseServiceImpl<Permission, Integer> implements PermissionService {
    @Autowired
    PermissionRepository permissionRepository;

    @Override
    protected void setRepository() {
        this.repository = permissionRepository;
    }

}
