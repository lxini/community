package life.majiang.community.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

import javax.annotation.Resource;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Resource
    SessionInterceptor sessionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(sessionInterceptor);

        addInterceptor.excludePathPatterns("/static/**");
        addInterceptor.addPathPatterns("/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
