package zone.security.domain.bo;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class PasswordLoginBo {

    /**
     * 用户名
     */
    @NotBlank(message = "{user.username.not.blank}")
    @Length(min = 2, max = 20, message = "{user.username.length.valid}")
    private String username;

    /**
     * 用户密码
     */
    @NotBlank(message = "{user.password.not.blank}")
    @Length(min = 5, max = 20, message = "{user.password.length.valid}")
    private String password;

}
