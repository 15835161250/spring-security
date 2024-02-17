package zone.security.satoken.service.user;

import zone.security.satoken.body.CreateUserBody;
import zone.security.satoken.body.LoginBody;
import zone.security.satoken.domain.SysUser;
import zone.security.satoken.domain.result.AjaxResult;

import java.util.List;

public interface UserService {

    List<SysUser> getAll();

    int add(CreateUserBody createUserBody);

    boolean checkUsername(String username);

    int upload(CreateUserBody createUserBody);

    AjaxResult login(LoginBody loginBody);

    String del(Long userId);
}
