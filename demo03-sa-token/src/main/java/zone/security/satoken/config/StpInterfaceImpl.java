package zone.security.satoken.config;

import cn.dev33.satoken.stp.StpInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zone.security.satoken.service.user.MenuService;
import zone.security.satoken.service.user.RoleService;

import java.util.List;

@Component
public class StpInterfaceImpl implements StpInterface {

    private static final Logger log = LoggerFactory.getLogger(StpInterfaceImpl.class);

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        log.info("loginId:{} \n loginType:{}", loginId, loginType);
        return menuService.getMenuByUserId(Long.parseLong((String) loginId));
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        return roleService.getRoleByUserId((Long) loginId);
    }
}
