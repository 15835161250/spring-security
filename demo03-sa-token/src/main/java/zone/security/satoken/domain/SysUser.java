package zone.security.satoken.domain;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class SysUser implements Serializable {


    @Serial
    private static final long serialVersionUID = 4029756143194360408L;

    private Long id;

    private String username;

    private String password;

    private Boolean enable;

}
