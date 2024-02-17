package zone.security.satoken.domain;


import lombok.Data;

@Data
public class SysMenu {

    private Long id;

    private String menuName;

    private String menuValue;

    private Long parentId;

    private String perms;
}
