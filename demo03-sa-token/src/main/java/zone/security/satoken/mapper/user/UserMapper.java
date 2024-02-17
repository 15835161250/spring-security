package zone.security.satoken.mapper.user;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import zone.security.satoken.body.CreateUserBody;
import zone.security.satoken.domain.SysUser;

import java.util.List;

@Mapper
public interface UserMapper {

    List<SysUser> getAll();

    int checkUserByUsername(@Param("username") String username);

    int insertUser(CreateUserBody createUserBody);

    int updateUser(CreateUserBody createUserBody);

    SysUser getUserByUsername(@Param("username") String username);

    String delByUserId(@Param("userId") Long userId);
}
