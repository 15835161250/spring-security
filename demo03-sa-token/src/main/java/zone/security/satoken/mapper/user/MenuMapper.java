package zone.security.satoken.mapper.user;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MenuMapper {

    List<String> getMenuByUserId(@Param("userId") Long loginId);

}
