package zone.security.satoken.service.user;


import java.util.List;

public interface RoleService {
    List<String> getRoleByUserId(Long loginId);

    void saveUserAndRole(Long userId, List<Long> roleIds);

    void delUserAndRole(Long userId);
}
