package zone.security.satoken.mapper.user;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

@Mapper
public interface RoleMapper {
    List<String> getRoleByUserId(@Param("loginId") Long loginId);

    void saveUserAndRole(@Param("userId") Long userId, @Param("list") List<Long> roleIds);

    void delUserAndRole(@Param("userId") Long userId);
}
