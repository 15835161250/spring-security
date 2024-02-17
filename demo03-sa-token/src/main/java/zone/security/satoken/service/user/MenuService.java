package zone.security.satoken.service.user;

import java.util.List;

public interface MenuService {

    List<String> getMenuByUserId(Long loginId);

}
