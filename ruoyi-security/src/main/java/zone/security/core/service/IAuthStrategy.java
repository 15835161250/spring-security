package zone.security.core.service;


import zone.security.domain.SysClient;
import zone.security.domain.bo.LoginBo;
import zone.security.domain.vo.LoginVo;
import zone.security.exception.ServiceException;
import zone.security.utils.SpringUtils;

/**
 * 授权策略
 *
 */
public interface IAuthStrategy {

    String BASE_NAME = "AuthStrategy";

    /**
     * 登录
     */
    static LoginVo login(LoginBo body, SysClient client, String grantType) {
        // 授权类型和客户端id
        String beanName = grantType + BASE_NAME;
        if (!SpringUtils.containsBean(beanName)) {
            throw new ServiceException("授权类型不正确!");
        }
        IAuthStrategy instance = SpringUtils.getBean(beanName);
        return instance.login(body, client);
    }

    /**
     * 登录
     */
    LoginVo login(LoginBo body,SysClient client);

}
