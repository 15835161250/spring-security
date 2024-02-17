package zone.security.core.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zone.security.config.properties.CaptchaProperties;
import zone.security.constant.GlobalConstants;
import zone.security.core.service.IAuthStrategy;
import zone.security.domain.SysClient;
import zone.security.domain.bo.LoginBo;
import zone.security.domain.vo.LoginVo;
import zone.security.service.SysLoginService;
import zone.security.utils.StringUtils;
import zone.security.utils.ValidatorUtils;
import zone.security.utils.redis.utils.RedisUtils;

@Slf4j
@Service("password" + IAuthStrategy.BASE_NAME)
public class PasswordAuthStrategy implements IAuthStrategy {

    @Autowired
    private SysLoginService loginService;

    @Autowired
    private CaptchaProperties captchaProperties;


    @Override
    public LoginVo login(LoginBo body, SysClient client) {
        String clientId = body.getClientId();
        String username = body.getUsername();
        String password = body.getPassword();
        String code = body.getCode();
        String uuid = body.getUuid();

        Boolean captchaEnable = captchaProperties.getEnable();
        //验证码开关
        if (captchaEnable) {
            validateCaptcha(clientId, username, code, uuid);
        }

        return null;
    }

    private void validateCaptcha(String clientId, String username, String code, String uuid) {
        String verifyKey = GlobalConstants.CAPTCHA_CODE_KEY + StringUtils.defaultString(uuid, "");
        String captcha = RedisUtils.getCacheObject(verifyKey);
    }
}
