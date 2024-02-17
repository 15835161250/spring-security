package zone.security.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import zone.security.constant.UserConstants;
import zone.security.core.service.IAuthStrategy;
import zone.security.domain.SysClient;
import zone.security.domain.result.R;
import zone.security.domain.bo.LoginBo;
import zone.security.domain.vo.LoginVo;
import zone.security.service.IClientService;
import zone.security.service.SysLoginService;
import zone.security.utils.MessageUtils;
import zone.security.utils.StringUtils;

import java.util.Map;

@Slf4j
@SaIgnore
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private IClientService IClientService;

    @Autowired
    private SysLoginService sysLoginService;

    @GetMapping("index")
    public R<String> index() {
        return R.ok("index");
    }

    @PostMapping("/login")
    public R<Map<String, Object>> login(@Validated @RequestBody LoginBo loginBo) {
        // 授权类型和客户端id
        String clientId = loginBo.getClientId();
        String grantType = loginBo.getGrantType();
        SysClient client = IClientService.queryByClientId(clientId);
        if (ObjectUtil.isNull(client) || !StringUtils.contains(client.getClientId(), clientId)) {
            log.info("客户端id: {} 认证类型：{} 异常!.", clientId, grantType);
            return R.fail(MessageUtils.message("auth.grant.type.error"));
        } else if (!UserConstants.NORMAL.equals(client.getStatus())) {
            return R.fail(MessageUtils.message("auth.grant.type.blocked"));
        }
        LoginVo loginVo = IAuthStrategy.login(loginBo, client, grantType);

        return null;
    }

    /**
     * 退出登录
     */
    @PostMapping("/logout")
    public R<Void> logout() {
        sysLoginService.logout();
        return R.ok("退出成功");
    }


}
