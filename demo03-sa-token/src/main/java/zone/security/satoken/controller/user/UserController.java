package zone.security.satoken.controller.user;

import cn.dev33.satoken.annotation.SaCheckPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import zone.security.satoken.body.CreateUserBody;
import zone.security.satoken.core.controller.BaseController;
import zone.security.satoken.domain.result.AjaxResult;
import zone.security.satoken.service.user.UserService;

@RestController
@RequestMapping("user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @SaCheckPermission("user:select")
    @GetMapping("getAll")
    public AjaxResult getAll(){
        return success(userService.getAll());
    }

    @SaCheckPermission("user:add")
    @PostMapping("add")
    public AjaxResult add(@Validated @RequestBody CreateUserBody createUserBody){
        if (!userService.checkUsername(createUserBody.getUsername())){
            error("新增用户'" + createUserBody.getUsername() + "'失败，登录账号已存在");
        }
        return toAjax(userService.add(createUserBody));
    }

    @SaCheckPermission("user:upload")
    @PostMapping("upload")
    public AjaxResult upload(@Validated @RequestBody CreateUserBody createUserBody){
        if (!userService.checkUsername(createUserBody.getUsername())){
            error("修改用户'" + createUserBody.getUsername() + "'失败，登录账号已存在");
        }
        return toAjax(userService.upload(createUserBody));
    }

    @SaCheckPermission("user:del")
    @GetMapping("del/{userId}")
    public AjaxResult del(@PathVariable Long userId){
        return success(userService.del(userId));
    }


}
