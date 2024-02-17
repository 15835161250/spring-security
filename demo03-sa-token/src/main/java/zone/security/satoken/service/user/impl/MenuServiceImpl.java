package zone.security.satoken.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zone.security.satoken.mapper.user.MenuMapper;
import zone.security.satoken.service.user.MenuService;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<String> getMenuByUserId(Long loginId) {
        return menuMapper.getMenuByUserId(loginId);
    }

}
