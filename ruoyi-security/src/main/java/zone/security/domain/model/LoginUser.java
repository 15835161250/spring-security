package zone.security.domain.model;

import cn.hutool.log.Log;
import lombok.Data;
import lombok.NoArgsConstructor;
import zone.security.domain.entity.Role;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class LoginUser implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户唯一标识
     */
    private String token;

    /**
     * 菜单权限
     */
    private Set<String> menuPermission;

    /**
     * 角色权限
     */
    private Set<String> rolePermission;

    /**
     * 账号
     */
    private String account;

    /**
     * 用户名
     **/
    private String name;

    /**
     * 角色对象
     */
    private List<Role> roles;

    public String getLoginId(){
        if (userId == null) {
            throw new IllegalArgumentException("用户ID不能为空");
        }
        return "user:" + userId;
    }

}
