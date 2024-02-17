package zone.security.mapper;

import org.apache.ibatis.annotations.Mapper;
import zone.security.core.mapper.BaseMapperPlus;
import zone.security.domain.SysDictType;
import zone.security.domain.vo.SysDictTypeVo;

@Mapper
public interface SysDictTypeMapper extends BaseMapperPlus<SysDictType, SysDictTypeVo> {

}
