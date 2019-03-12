package group.bridge.web.serviceImpl;

import group.bridge.web.dao.RoleRepository;
import group.bridge.web.entity.Role;
import group.bridge.web.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wuran
 * @Created on 2019/3/11
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role, Integer> implements RoleService {
    @Autowired
    RoleRepository roleRepository;
    @Override
    protected void setRepository() {
        this.repository = roleRepository;
    }
}
