package zone.security.satoken.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zone.security.satoken.mapper.user.RoleMapper;
import zone.security.satoken.service.user.RoleService;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<String> getRoleByUserId(Long loginId) {
        return roleMapper.getRoleByUserId(loginId);
    }

    @Override
    public void saveUserAndRole(Long userId, List<Long> roleIds) {
        roleMapper.saveUserAndRole(userId,roleIds);
    }

    @Override
    public void delUserAndRole(Long userId) {
        roleMapper.delUserAndRole(userId);
    }
}
