package zone.security.satoken.service.user.impl;

import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zone.security.satoken.body.CreateUserBody;
import zone.security.satoken.body.LoginBody;
import zone.security.satoken.domain.SysUser;
import zone.security.satoken.domain.result.AjaxResult;
import zone.security.satoken.exception.base.BaseException;
import zone.security.satoken.mapper.user.UserMapper;
import zone.security.satoken.service.user.MenuService;
import zone.security.satoken.service.user.RoleService;
import zone.security.satoken.service.user.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @Override
    public List<SysUser> getAll() {
        return userMapper.getAll();
    }

    @Override
    public int add(CreateUserBody createUserBody) {
        int rows = userMapper.insertUser(createUserBody);
        roleService.saveUserAndRole(createUserBody.getUserId(), createUserBody.getRoleIds());
        return rows;
    }

    @Override
    public boolean checkUsername(String username) {
        return userMapper.checkUserByUsername(username) <= 0;
    }

    @Override
    public int upload(CreateUserBody createUserBody) {
        //删除用户角色绑定
        roleService.delUserAndRole(createUserBody.getUserId());
        //保存
        roleService.saveUserAndRole(createUserBody.getUserId(), createUserBody.getRoleIds());
        createUserBody.setPassword(BCrypt.hashpw(createUserBody.getPassword()));
        return userMapper.updateUser(createUserBody);
    }

    @Override
    public AjaxResult login(LoginBody loginBody) {
        //用户名密码校验
        SysUser user = userMapper.getUserByUsername(loginBody.getUsername());
        if (!checkPassword(loginBody.getPassword(), user.getPassword())) {
            return AjaxResult.error(500, "用户名或密码错误");
        }
        StpUtil.login(user.getId());
        // 第2步，获取 Token  相关参数
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        return AjaxResult.success(tokenInfo);
    }

    @Override
    public String del(Long userId) {
        return userMapper.delByUserId(userId);
    }

    private boolean checkPassword(String password, String pwd) {
        return BCrypt.checkpw(password, pwd);
    }

    public static void main(String[] args) {
        String admin = BCrypt.hashpw("123");
        System.out.println("123 = " + admin);
    }
}
