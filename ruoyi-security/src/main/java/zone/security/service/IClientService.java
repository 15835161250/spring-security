package zone.security.service;

import zone.security.domain.SysClient;

public interface IClientService {

    /**
     * 查询客户端管理
     **/
    SysClient queryByClientId(String clientId);
}
