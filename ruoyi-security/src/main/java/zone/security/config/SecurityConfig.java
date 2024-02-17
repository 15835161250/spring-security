package zone.security.config;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import zone.security.config.properties.SecurityProperties;
import zone.security.handler.AllUrlHandler;
import zone.security.utils.ServletUtils;
import zone.security.utils.SpringUtils;
import zone.security.utils.StringUtils;
import zone.security.utils.satoken.LoginHelper;

@Slf4j
@Configuration
@EnableWebMvc
public class SecurityConfig implements WebMvcConfigurer {

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 注册sa-token的拦截器
     **/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册路由眼界器，自定义验证规则
        registry.addInterceptor(new SaInterceptor(handler -> {
                    AllUrlHandler allUrlHandler = SpringUtils.getBean(AllUrlHandler.class);
                    // 登录验证 -- 排除多个路径
                    SaRouter
                            // 获取所有的
                            .match(allUrlHandler.getUrls())
                            .check(() -> {
                                // 检查是否登录 是否有token
                                StpUtil.checkLogin();
                                // 检查 header 与 param 里的 clientId 与 token 里的是否一致
                                String headerCid = ServletUtils.getRequest().getHeader(LoginHelper.CLIENT_KEY);
                                String paramCid = ServletUtils.getParameter(LoginHelper.CLIENT_KEY);
                                String clientId = StpUtil.getExtra(LoginHelper.CLIENT_KEY).toString();
                                if (!StringUtils.equalsAny(clientId, headerCid, paramCid)) {
                                    // token 无效
                                    throw NotLoginException.newInstance(StpUtil.getLoginType(),
                                            "-100", "客户端ID与Token不匹配",
                                            StpUtil.getTokenValue());
                                }
                            });
                })).addPathPatterns("/**")
                // 排除不需要拦截的路径
                .excludePathPatterns(securityProperties.getExcludes());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");

        /* 配置knife4j 显示文档 */
        registry.addResourceHandler("doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        /*
          配置swagger-ui显示文档
         */
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        /* 公共部分内容 */
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedOriginPatterns("*")
                .allowedHeaders("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .maxAge(3600);
    }

}
