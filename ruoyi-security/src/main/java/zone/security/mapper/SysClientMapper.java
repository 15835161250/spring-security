package zone.security.mapper;

import org.apache.ibatis.annotations.Mapper;
import zone.security.core.mapper.BaseMapperPlus;
import zone.security.domain.SysClient;
import zone.security.domain.vo.SysClientVo;

@Mapper
public interface SysClientMapper extends BaseMapperPlus<SysClient, SysClientVo> {

}
