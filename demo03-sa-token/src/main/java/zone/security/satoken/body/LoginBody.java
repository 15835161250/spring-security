package zone.security.satoken.body;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginBody {

    @NotBlank(message = "登录用户名不能为空")
    private String username;

    @NotBlank(message = "登录密码不能为空")
    private String password;

}
