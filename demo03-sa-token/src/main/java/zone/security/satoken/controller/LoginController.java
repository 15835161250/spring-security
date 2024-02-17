package zone.security.satoken.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import zone.security.satoken.body.LoginBody;
import zone.security.satoken.core.controller.BaseController;
import zone.security.satoken.domain.SysUser;
import zone.security.satoken.domain.result.AjaxResult;
import zone.security.satoken.service.user.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("auth")
public class LoginController extends BaseController {

    @Autowired
    private UserService userService;

    @PostMapping("login")
    public AjaxResult login(@Validated @RequestBody LoginBody loginBody) {
        return userService.login(loginBody);
    }

    // 查询登录状态  ---- http://localhost:8081/acc/isLogin
    @GetMapping("isLogin")
    public SaResult isLogin() {
        return SaResult.ok("是否登录：" + StpUtil.isLogin());
    }

    // 查询 Token 信息  ---- http://localhost:8081/acc/tokenInfo
    @GetMapping("tokenInfo")
    public SaResult tokenInfo() {
        return SaResult.data(StpUtil.getTokenInfo());
    }

    // 测试注销  ---- http://localhost:8081/acc/logout
    @GetMapping("logout")
    public SaResult logout() {
        StpUtil.logout();
        return SaResult.ok();
    }

}
