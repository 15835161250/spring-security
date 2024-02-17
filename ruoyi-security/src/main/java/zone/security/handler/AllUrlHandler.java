package zone.security.handler;

import cn.hutool.core.util.ReUtil;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import zone.security.utils.SpringUtils;

import java.util.*;
import java.util.regex.Pattern;

@Data
@Component
public class AllUrlHandler implements InitializingBean {

    private static final Pattern PATTERN = Pattern.compile("\\{(.*?)\\}");

    private List<String> urls = new ArrayList<>();

    @Override
    public void afterPropertiesSet() {
        Set<String> set = new HashSet<>();
        RequestMappingHandlerMapping mapping = SpringUtils.getBean("requestMappingHandlerMapping", RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();
        map.keySet().forEach(info -> {
            // 获取注解上边的 path 替代 path variable 为 *
            Objects.requireNonNull(info.getPathPatternsCondition().getPatterns())
                    .forEach(url -> set.add(ReUtil.replaceAll(url.getPatternString(), PATTERN, "*")));
        });
        urls.addAll(set);
    }
}