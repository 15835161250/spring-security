package zone.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zone.security.domain.SysClient;
import zone.security.mapper.SysClientMapper;
import zone.security.service.IClientService;

@Service
public class IClientServiceImpl implements IClientService {

    @Autowired
    private SysClientMapper sysClientMapper;

    /**
     * 查询客户端管理
     */
    @Override
    public SysClient queryByClientId(String clientId) {
        return sysClientMapper.selectOne(new LambdaQueryWrapper<SysClient>().eq(SysClient::getClientId, clientId));
    }
}
